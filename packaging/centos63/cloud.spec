# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
# 
#   http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

%define __os_install_post %{nil}
%global debug_package %{nil}

# DISABLE the post-percentinstall java repacking and line number stripping
# we need to find a way to just disable the java repacking and line number stripping, but not the autodeps

Name:      cloudstack
Summary:   CloudStack IaaS Platform
#http://fedoraproject.org/wiki/PackageNamingGuidelines#Pre-Release_packages
%if "%{?_prerelease}" != ""
%define _maventag %{_ver}-SNAPSHOT
Release:   %{_rel}%{dist}
%else
%define _maventag %{_ver}
Release:   %{_rel}%{dist}
%endif
Version:   %{_ver}
License:   Apache License 2.0
Vendor:    Apache CloudStack <cloudstack-dev@incubator.apache.org>
Packager:  Apache CloudStack <cloudstack-dev@incubator.apache.org>
Group:     System Environment/Libraries
# FIXME do groups for every single one of the subpackages
Source0:   %{name}-%{_maventag}.tgz
BuildRoot: %{_tmppath}/%{name}-%{_maventag}-%{release}-build

BuildRequires: java-1.6.0-openjdk-devel
BuildRequires: tomcat6
BuildRequires: ws-commons-util
BuildRequires: jpackage-utils
BuildRequires: gcc
BuildRequires: glibc-devel
BuildRequires: /usr/bin/mkisofs
BuildRequires: MySQL-python
#BuildRequires: maven => 3.0.0

%description
CloudStack is a highly-scalable elastic, open source,
intelligent IaaS cloud implementation.

%package management-server
Summary:   CloudStack management server UI
Requires: tomcat6
Group:     System Environment/Libraries
%description management-server
The CloudStack management server is the central point of coordination,
management, and intelligence in CloudStack.  


%prep
echo Doing CloudStack build

%setup -q -n %{name}-%{_maventag}

%build

# this fixes the /usr/com bug on centos5
%define _localstatedir /var
%define _sharedstatedir /var/lib
cp packaging/centos63/replace.properties build/replace.properties
echo VERSION=%{_maventag} >> build/replace.properties
echo PACKAGE=%{name} >> build/replace.properties
mvn package

%install
[ ${RPM_BUILD_ROOT} != "/" ] && rm -rf ${RPM_BUILD_ROOT}
mkdir -p ${RPM_BUILD_ROOT}/usr/bin
mkdir -p ${RPM_BUILD_ROOT}/usr/share/%{name}/management/
for dir in bin conf lib logs temp webapps work ; do
  mkdir -p ${RPM_BUILD_ROOT}/usr/share/%{name}/management/${dir}
done
mkdir -p ${RPM_BUILD_ROOT}/usr/share/%{name}/management/webapps/client
mkdir -p ${RPM_BUILD_ROOT}/var/log/%{name}/management
mkdir -p ${RPM_BUILD_ROOT}/var/log/%{name}/agent
mkdir -p ${RPM_BUILD_ROOT}/var/cache/%{name}/management/work
mkdir -p ${RPM_BUILD_ROOT}/var/cache/%{name}/management/temp
mkdir -p ${RPM_BUILD_ROOT}/var/lib/%{name}/mnt
mkdir -p ${RPM_BUILD_ROOT}/var/lib/%{name}/management
mkdir -p ${RPM_BUILD_ROOT}/etc/%{name}/management
mkdir -p ${RPM_BUILD_ROOT}/etc/%{name}/management/Catalina/localhost/client
mkdir -p ${RPM_BUILD_ROOT}/etc/rc.d/init.d
mkdir -p ${RPM_BUILD_ROOT}/etc/sysconfig
mkdir -p ${RPM_BUILD_ROOT}/etc/%{name}/management/Catalina/localhost/client

cp client/bindir/cloud-setup-management.in ${RPM_BUILD_ROOT}/usr/bin/%{name}-setup-management
cp client/bindir/cloud-update-xenserver-licenses.in ${RPM_BUILD_ROOT}/usr/bin/%{name}-update-xenserver-licenses
cp packaging/centos63/cloud-management.rc ${RPM_BUILD_ROOT}/etc/rc.d/init.d/%{name}-management
cp packaging/centos63/cloud-management.sysconfig ${RPM_BUILD_ROOT}/etc/sysconfig/%{name}-management

cp -r client/target/cloud-client-ui-4.1.0-SNAPSHOT/* ${RPM_BUILD_ROOT}/usr/share/%{name}/management/webapps/client

cp ${RPM_BUILD_ROOT}/usr/share/%{name}/management/webapps/client/WEB-INF/classes/db.properties \
    ${RPM_BUILD_ROOT}/etc/%{name}/management/db.properties
cp ${RPM_BUILD_ROOT}/usr/share/%{name}/management/webapps/client/WEB-INF/classes/log4j-cloud.xml \
    ${RPM_BUILD_ROOT}/etc/%{name}/management/log4j-%{name}.xml
cp ${RPM_BUILD_ROOT}/usr/share/%{name}/management/webapps/client/WEB-INF/classes/tomcat6-nonssl.conf \
    ${RPM_BUILD_ROOT}/etc/%{name}/management/tomcat6-nonssl.conf
cp ${RPM_BUILD_ROOT}/usr/share/%{name}/management/webapps/client/WEB-INF/classes/tomcat6-ssl.conf \
    ${RPM_BUILD_ROOT}/etc/%{name}/management/tomcat6-ssl.conf
cp ${RPM_BUILD_ROOT}/usr/share/%{name}/management/webapps/client/WEB-INF/classes/context.xml \
    ${RPM_BUILD_ROOT}/etc/%{name}/management/Catalina/localhost/client


%clean

[ ${RPM_BUILD_ROOT} != "/" ] && rm -rf ${RPM_BUILD_ROOT}


%preun management-server
/sbin/service %{name}-management stop || true
if [ "$1" == "0" ] ; then
    /sbin/chkconfig --del %{name}-management  > /dev/null 2>&1 || true
    /sbin/service %{name}-management stop > /dev/null 2>&1 || true
fi

%pre management-server
id %{name} > /dev/null 2>&1 || /usr/sbin/useradd -M -c "CloudStack unprivileged user" \
     -r -s /bin/sh -d %{_sharedstatedir}/%{name}/management %{name}|| true

# set max file descriptors for cloud user to 4096
sed -i /"cloud hard nofile"/d /etc/security/limits.conf
sed -i /"cloud soft nofile"/d /etc/security/limits.conf
echo "cloud hard nofile 4096" >> /etc/security/limits.conf
echo "cloud soft nofile 4096" >> /etc/security/limits.conf
rm -rf %{_localstatedir}/cache/%{name}
# user harcoded here, also hardcoded on wscript

%post management-server
if [ "$1" == "1" ] ; then
    /usr/bin/cloudstack-setup-management
    /sbin/chkconfig --add %{name}-management > /dev/null 2>&1 || true
    /sbin/chkconfig --level 345 %{name}-management on > /dev/null 2>&1 || true
fi

%files management-server
%defattr(0644,root,root,0755)
%doc LICENSE
%doc NOTICE
%dir %attr(0770,root,%{name}) %{_sysconfdir}/%{name}/management/Catalina
%dir %attr(0770,root,%{name}) %{_sysconfdir}/%{name}/management/Catalina/localhost
%dir %attr(0770,root,%{name}) %{_sysconfdir}/%{name}/management/Catalina/localhost/client
%dir %{_datadir}/%{name}/management
%dir %attr(0770,root,%{name}) %{_sharedstatedir}/%{name}/mnt
%dir %attr(0770,%{name},%{name}) %{_sharedstatedir}/%{name}/management
%dir %attr(0770,root,%{name}) %{_localstatedir}/cache/%{name}/management
%dir %attr(0770,root,%{name}) %{_localstatedir}/cache/%{name}/management/work
%dir %attr(0770,root,%{name}) %{_localstatedir}/cache/%{name}/management/temp
%dir %attr(0770,root,%{name}) %{_localstatedir}/log/%{name}/management
%dir %attr(0770,root,%{name}) %{_localstatedir}/log/%{name}/agent
%config(noreplace) %{_sysconfdir}/sysconfig/%{name}-management
%config(noreplace) %{_sysconfdir}/%{name}/management
%config(noreplace) %attr(0640,root,%{name}) %{_sysconfdir}/%{name}/management/db.properties
%config(noreplace) %{_sysconfdir}/%{name}/management/log4j-%{name}.xml
%config(noreplace) %{_sysconfdir}/%{name}/management/tomcat6-nonssl.conf
%config(noreplace) %{_sysconfdir}/%{name}/management/tomcat6-ssl.conf
%attr(0755,root,root) %{_initrddir}/%{name}-management
%attr(0755,root,root) %{_bindir}/%{name}-setup-management
%attr(0755,root,root) %{_bindir}/%{name}-update-xenserver-licenses
%{_datadir}/%{name}/management/*


%changelog
* Fri Oct 03 2012 Hugo Trippaers <hugo@apache.org> 4.1.0
- new style spec file

