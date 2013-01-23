// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.hypervisor.vmware.util;

import java.util.HashMap;
import java.util.Map;

import com.vmware.vim25.VirtualMachineGuestOsIdentifier;

public class VmwareGuestOsMapper {
	private static Map<String, String> s_mapper = new HashMap<String, String>();
	static {
		s_mapper.put("DOS", VirtualMachineGuestOsIdentifier.dosGuest.toString());
		s_mapper.put("OS/2", VirtualMachineGuestOsIdentifier.os2Guest.toString());

		s_mapper.put("Windows 3.1", VirtualMachineGuestOsIdentifier.win31Guest.toString());
		s_mapper.put("Windows 95", VirtualMachineGuestOsIdentifier.win95Guest.toString());
		s_mapper.put("Windows 98", VirtualMachineGuestOsIdentifier.win98Guest.toString());
		s_mapper.put("Windows NT 4", VirtualMachineGuestOsIdentifier.winNTGuest.toString());
		s_mapper.put("Windows XP (32-bit)", VirtualMachineGuestOsIdentifier.winXPProGuest.toString());
		s_mapper.put("Windows XP (64-bit)", VirtualMachineGuestOsIdentifier.winXPPro64Guest.toString());
		s_mapper.put("Windows XP SP2 (32-bit)", VirtualMachineGuestOsIdentifier.winXPProGuest.toString());
		s_mapper.put("Windows XP SP3 (32-bit)", VirtualMachineGuestOsIdentifier.winXPProGuest.toString());
		s_mapper.put("Windows Vista (32-bit)", VirtualMachineGuestOsIdentifier.winVistaGuest.toString());
		s_mapper.put("Windows Vista (64-bit)", VirtualMachineGuestOsIdentifier.winVista64Guest.toString());
		s_mapper.put("Windows 7 (32-bit)", VirtualMachineGuestOsIdentifier.windows7Guest.toString());
		s_mapper.put("Windows 7 (64-bit)", VirtualMachineGuestOsIdentifier.windows7_64Guest.toString());

		s_mapper.put("Windows 2000 Professional", VirtualMachineGuestOsIdentifier.win2000ProGuest.toString());
		s_mapper.put("Windows 2000 Server", VirtualMachineGuestOsIdentifier.win2000ServGuest.toString());
		s_mapper.put("Windows 2000 Server SP4 (32-bit)", VirtualMachineGuestOsIdentifier.win2000ServGuest.toString());
		s_mapper.put("Windows 2000 Advanced Server", VirtualMachineGuestOsIdentifier.win2000AdvServGuest.toString());

		s_mapper.put("Windows Server 2003 Enterprise Edition(32-bit)", VirtualMachineGuestOsIdentifier.winNetEnterpriseGuest.toString());
		s_mapper.put("Windows Server 2003 Enterprise Edition(64-bit)", VirtualMachineGuestOsIdentifier.winNetEnterprise64Guest.toString());
		s_mapper.put("Windows Server 2008 R2 (64-bit)", VirtualMachineGuestOsIdentifier.winLonghorn64Guest.toString());
		s_mapper.put("Windows Server 2003 DataCenter Edition(32-bit)", VirtualMachineGuestOsIdentifier.winNetDatacenterGuest.toString());
		s_mapper.put("Windows Server 2003 DataCenter Edition(64-bit)", VirtualMachineGuestOsIdentifier.winNetDatacenter64Guest.toString());
		s_mapper.put("Windows Server 2003 Standard Edition(32-bit)", VirtualMachineGuestOsIdentifier.winNetStandardGuest.toString());
		s_mapper.put("Windows Server 2003 Standard Edition(64-bit)", VirtualMachineGuestOsIdentifier.winNetStandard64Guest.toString());
		s_mapper.put("Windows Server 2003 Web Edition", VirtualMachineGuestOsIdentifier.winNetWebGuest.toString());
		s_mapper.put("Microsoft Small Bussiness Server 2003", VirtualMachineGuestOsIdentifier.winNetBusinessGuest.toString());

		s_mapper.put("Windows Server 2008 (32-bit)", VirtualMachineGuestOsIdentifier.winLonghornGuest.toString());
		s_mapper.put("Windows Server 2008 (64-bit)", VirtualMachineGuestOsIdentifier.winLonghorn64Guest.toString());

		// Window 8 support hack to avoid upgrading to newer version of VMWare SDK.
	    s_mapper.put("Windows 8", "windows8Guest");
	    s_mapper.put("Windows 8 (64 bit)", "windows8_64Guest");
	    s_mapper.put("Windows 8 Server (64 bit)", "windows8Server64Guest");

		s_mapper.put("Open Enterprise Server", VirtualMachineGuestOsIdentifier.oesGuest.toString());

		s_mapper.put("Asianux 3(32-bit)", VirtualMachineGuestOsIdentifier.asianux3Guest.toString());
		s_mapper.put("Asianux 3(64-bit)", VirtualMachineGuestOsIdentifier.asianux3_64Guest.toString());

		s_mapper.put("Debian GNU/Linux 5(64-bit)", VirtualMachineGuestOsIdentifier.debian5_64Guest.toString());
        s_mapper.put("Debian GNU/Linux 5.0 (32-bit)", VirtualMachineGuestOsIdentifier.debian5Guest.toString());
		s_mapper.put("Debian GNU/Linux 4(32-bit)", VirtualMachineGuestOsIdentifier.debian4Guest.toString());
		s_mapper.put("Debian GNU/Linux 4(64-bit)", VirtualMachineGuestOsIdentifier.debian4_64Guest.toString());

		s_mapper.put("Novell Netware 6.x", VirtualMachineGuestOsIdentifier.netware6Guest.toString());
		s_mapper.put("Novell Netware 5.1", VirtualMachineGuestOsIdentifier.netware5Guest.toString());

		s_mapper.put("Sun Solaris 10(32-bit)", VirtualMachineGuestOsIdentifier.solaris10Guest.toString());
		s_mapper.put("Sun Solaris 10(64-bit)", VirtualMachineGuestOsIdentifier.solaris10_64Guest.toString());
		s_mapper.put("Sun Solaris 9(Experimental)", VirtualMachineGuestOsIdentifier.solaris9Guest.toString());
		s_mapper.put("Sun Solaris 8(Experimental)", VirtualMachineGuestOsIdentifier.solaris8Guest.toString());

		s_mapper.put("FreeBSD (32-bit)", VirtualMachineGuestOsIdentifier.freebsdGuest.toString());
		s_mapper.put("FreeBSD (64-bit)", VirtualMachineGuestOsIdentifier.freebsd64Guest.toString());

		s_mapper.put("SCO OpenServer 5", VirtualMachineGuestOsIdentifier.otherGuest.toString());
		s_mapper.put("SCO UnixWare 7", VirtualMachineGuestOsIdentifier.unixWare7Guest.toString());

		s_mapper.put("SUSE Linux Enterprise 8(32-bit)", VirtualMachineGuestOsIdentifier.suseGuest.toString());
		s_mapper.put("SUSE Linux Enterprise 8(64-bit)", VirtualMachineGuestOsIdentifier.suse64Guest.toString());
		s_mapper.put("SUSE Linux Enterprise 9(32-bit)", VirtualMachineGuestOsIdentifier.suseGuest.toString());
		s_mapper.put("SUSE Linux Enterprise 9(64-bit)", VirtualMachineGuestOsIdentifier.suse64Guest.toString());
		s_mapper.put("SUSE Linux Enterprise 10(32-bit)", VirtualMachineGuestOsIdentifier.suseGuest.toString());
		s_mapper.put("SUSE Linux Enterprise 10(64-bit)", VirtualMachineGuestOsIdentifier.suse64Guest.toString());
		s_mapper.put("SUSE Linux Enterprise 10(32-bit)", VirtualMachineGuestOsIdentifier.suseGuest.toString());
		s_mapper.put("Other SUSE Linux(32-bit)", VirtualMachineGuestOsIdentifier.suseGuest.toString());
		s_mapper.put("Other SUSE Linux(64-bit)", VirtualMachineGuestOsIdentifier.suse64Guest.toString());

		s_mapper.put("CentOS 4.5 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 4.6 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 4.7 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 4.8 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 5.0 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 5.0 (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());
		s_mapper.put("CentOS 5.1 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 5.1 (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());
		s_mapper.put("CentOS 5.2 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 5.2 (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());
		s_mapper.put("CentOS 5.3 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 5.3 (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());
		s_mapper.put("CentOS 5.4 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 5.4 (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());
		s_mapper.put("CentOS 5.5 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 5.5 (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());
		s_mapper.put("CentOS 5.6 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 5.6 (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());
		s_mapper.put("CentOS 6.0 (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("CentOS 6.0 (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());
		s_mapper.put("Other CentOS (32-bit)", VirtualMachineGuestOsIdentifier.centosGuest.toString());
		s_mapper.put("Other CentOS (64-bit)", VirtualMachineGuestOsIdentifier.centos64Guest.toString());

		s_mapper.put("Red Hat Enterprise Linux 2", VirtualMachineGuestOsIdentifier.rhel2Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 3(32-bit)", VirtualMachineGuestOsIdentifier.rhel3Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 3(64-bit)", VirtualMachineGuestOsIdentifier.rhel3_64Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 4(32-bit)", VirtualMachineGuestOsIdentifier.rhel4Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 4(64-bit)", VirtualMachineGuestOsIdentifier.rhel4_64Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5(32-bit)", VirtualMachineGuestOsIdentifier.rhel5Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5(64-bit)", VirtualMachineGuestOsIdentifier.rhel5_64Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 6(32-bit)", VirtualMachineGuestOsIdentifier.rhel6Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 6(64-bit)", VirtualMachineGuestOsIdentifier.rhel6_64Guest.toString());

		s_mapper.put("Red Hat Enterprise Linux 4.5 (32-bit)", VirtualMachineGuestOsIdentifier.rhel4Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 4.6 (32-bit)", VirtualMachineGuestOsIdentifier.rhel4Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 4.7 (32-bit)", VirtualMachineGuestOsIdentifier.rhel4Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 4.8 (32-bit)", VirtualMachineGuestOsIdentifier.rhel4Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.0(32-bit)", VirtualMachineGuestOsIdentifier.rhel5Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.0(64-bit)", VirtualMachineGuestOsIdentifier.rhel5_64Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.1(32-bit)", VirtualMachineGuestOsIdentifier.rhel5Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.1(64-bit)", VirtualMachineGuestOsIdentifier.rhel5_64Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.2(32-bit)", VirtualMachineGuestOsIdentifier.rhel5Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.2(64-bit)", VirtualMachineGuestOsIdentifier.rhel5_64Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.3(32-bit)", VirtualMachineGuestOsIdentifier.rhel5Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.3(64-bit)", VirtualMachineGuestOsIdentifier.rhel5_64Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.4(32-bit)", VirtualMachineGuestOsIdentifier.rhel5Guest.toString());
		s_mapper.put("Red Hat Enterprise Linux 5.4(64-bit)", VirtualMachineGuestOsIdentifier.rhel5_64Guest.toString());

		s_mapper.put("Ubuntu 8.04 (32-bit)", VirtualMachineGuestOsIdentifier.ubuntuGuest.toString());
		s_mapper.put("Ubuntu 8.04 (64-bit)", VirtualMachineGuestOsIdentifier.ubuntu64Guest.toString());
		s_mapper.put("Ubuntu 8.10 (32-bit)", VirtualMachineGuestOsIdentifier.ubuntuGuest.toString());
		s_mapper.put("Ubuntu 8.10 (64-bit)", VirtualMachineGuestOsIdentifier.ubuntu64Guest.toString());
		s_mapper.put("Ubuntu 9.04 (32-bit)", VirtualMachineGuestOsIdentifier.ubuntuGuest.toString());
		s_mapper.put("Ubuntu 9.04 (64-bit)", VirtualMachineGuestOsIdentifier.ubuntu64Guest.toString());
		s_mapper.put("Ubuntu 9.10 (32-bit)", VirtualMachineGuestOsIdentifier.ubuntuGuest.toString());
		s_mapper.put("Ubuntu 9.10 (64-bit)", VirtualMachineGuestOsIdentifier.ubuntu64Guest.toString());
		s_mapper.put("Ubuntu 10.04 (32-bit)", VirtualMachineGuestOsIdentifier.ubuntuGuest.toString());
		s_mapper.put("Ubuntu 10.04 (64-bit)", VirtualMachineGuestOsIdentifier.ubuntu64Guest.toString());
		s_mapper.put("Ubuntu 10.10 (32-bit)", VirtualMachineGuestOsIdentifier.ubuntuGuest.toString());
		s_mapper.put("Ubuntu 10.10 (64-bit)", VirtualMachineGuestOsIdentifier.ubuntu64Guest.toString());
		s_mapper.put("Other Ubuntu (32-bit)", VirtualMachineGuestOsIdentifier.ubuntuGuest.toString());
		s_mapper.put("Other Ubuntu (64-bit)", VirtualMachineGuestOsIdentifier.ubuntu64Guest.toString());

		s_mapper.put("Other 2.6x Linux (32-bit)", VirtualMachineGuestOsIdentifier.other26xLinuxGuest.toString());
		s_mapper.put("Other 2.6x Linux (64-bit)", VirtualMachineGuestOsIdentifier.other26xLinux64Guest.toString());
		s_mapper.put("Other Linux (32-bit)", VirtualMachineGuestOsIdentifier.otherLinuxGuest.toString());
		s_mapper.put("Other Linux (64-bit)", VirtualMachineGuestOsIdentifier.otherLinux64Guest.toString());

		s_mapper.put("Other (32-bit)", VirtualMachineGuestOsIdentifier.otherGuest.toString());
		s_mapper.put("Other (64-bit)", VirtualMachineGuestOsIdentifier.otherGuest64.toString());
	}

	public static String getGuestOsIdentifier(String guestOsName) {
		return s_mapper.get(guestOsName);
	}
}
