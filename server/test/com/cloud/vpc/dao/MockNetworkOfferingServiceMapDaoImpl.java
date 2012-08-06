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
package com.cloud.vpc.dao;

import javax.ejb.Local;

import com.cloud.network.Network.Provider;
import com.cloud.network.Network.Service;
import com.cloud.offerings.NetworkOfferingServiceMapVO;
import com.cloud.offerings.dao.NetworkOfferingServiceMapDao;
import com.cloud.offerings.dao.NetworkOfferingServiceMapDaoImpl;
import com.cloud.utils.db.DB;

@Local(value = NetworkOfferingServiceMapDao.class)
@DB(txn = false)
public class MockNetworkOfferingServiceMapDaoImpl extends NetworkOfferingServiceMapDaoImpl{
    
    @Override
    public NetworkOfferingServiceMapVO findById(Long id) {
        NetworkOfferingServiceMapVO vo = null;
        System.out.println("hello alena");
        if (id.longValue() == 0) {
            vo = new NetworkOfferingServiceMapVO(1, Service.SourceNat, Provider.VPCVirtualRouter);
        } else if (id.longValue() == 2) {
            vo = new NetworkOfferingServiceMapVO(2, Service.Firewall, Provider.VirtualRouter);
        } else if (id.longValue() == 3) {
            vo = new NetworkOfferingServiceMapVO(3, Service.SourceNat, Provider.VPCVirtualRouter);
        } else if (id.longValue() == 4) {
            vo = new NetworkOfferingServiceMapVO(4, Service.SourceNat, Provider.VPCVirtualRouter);
        }
        
        return vo;
    }

}