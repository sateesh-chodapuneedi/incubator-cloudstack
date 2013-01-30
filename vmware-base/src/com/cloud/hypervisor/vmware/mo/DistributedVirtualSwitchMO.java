package com.cloud.hypervisor.vmware.mo;

import org.apache.log4j.Logger;

import com.cloud.hypervisor.vmware.util.VmwareContext;
import com.vmware.vim25.DVPortgroupConfigSpec;
import com.vmware.vim25.HostPortGroupSpec;
import com.vmware.vim25.ManagedObjectReference;

public class DistributedVirtualSwitchMO extends BaseMO {
    private static final Logger s_logger = Logger.getLogger(DistributedVirtualSwitchMO.class);

    public DistributedVirtualSwitchMO(VmwareContext context, ManagedObjectReference morDvs) {
        super(context, morDvs);
    }

    public DistributedVirtualSwitchMO(VmwareContext context, String morType, String morValue) {
        super(context, morType, morValue);
    }

    public void createDVPortGroup(DVPortgroupConfigSpec dvPortGroupSpec) throws Exception {
        DVPortgroupConfigSpec[] dvPortGroupSpecArray = new DVPortgroupConfigSpec[1];
        dvPortGroupSpecArray[0] = dvPortGroupSpec;
        _context.getService().addDVPortgroup_Task(_mor, dvPortGroupSpecArray);
    }

    public void updateDvPortGroup(ManagedObjectReference dvPortGroupMor, DVPortgroupConfigSpec dvPortGroupSpec) throws Exception {
        // TODO(sateesh): Update numPorts
        _context.getService().reconfigureDVPortgroup_Task(dvPortGroupMor, dvPortGroupSpec);
    }
}
