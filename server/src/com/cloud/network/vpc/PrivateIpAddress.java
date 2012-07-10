// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/03/2012
package com.cloud.network.vpc;

import com.cloud.utils.net.NetUtils;


public class PrivateIpAddress implements PrivateIp{
    String vlanTag;
    String gateway;
    String netmask;
    String ipAddress;
    String macAddress;
    long networkId;
    
    /**
     * @param privateIp
     * @param vlanTag
     * @param gateway
     * @param netmask
     * @param macAddress TODO
     * @param physicalNetworkId TODO
     */
    public PrivateIpAddress(PrivateIpVO privateIp, String vlanTag, String gateway, String netmask, long macAddress) {
        super();
        this.ipAddress = privateIp.getIpAddress();
        this.vlanTag = vlanTag;
        this.gateway = gateway;
        this.netmask = netmask;
        this.macAddress = NetUtils.long2Mac(macAddress);
        this.networkId = privateIp.getNetworkId();
    }

    @Override
    public String getVlanTag() {
        return vlanTag;
    }

    @Override
    public String getGateway() {
        return gateway;
    }

    @Override
    public String getNetmask() {
        return netmask;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String getMacAddress() {
        return macAddress;
    }
    
    @Override
    public long getNetworkId() {
        return networkId;
    }
}