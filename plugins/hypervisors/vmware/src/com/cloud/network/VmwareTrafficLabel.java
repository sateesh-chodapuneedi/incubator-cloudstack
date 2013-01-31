package com.cloud.network;

import com.cloud.exception.InvalidParameterValueException;
import com.cloud.hypervisor.vmware.mo.VirtualSwitchType;
import com.cloud.network.Networks.TrafficType;

public class VmwareTrafficLabel implements TrafficLabel {

    TrafficType _trafficType = TrafficType.None;
    String _networkLabel = null;
    VirtualSwitchType _vSwitchType = VirtualSwitchType.StandardVirtualSwitch;
    String _vSwitchName = null;
    String _vlanId = null;

    public VmwareTrafficLabel(String networkLabel, TrafficType trafficType) {
        _networkLabel = networkLabel;
        _trafficType = trafficType;
        _parseLabel();
    }

    public VmwareTrafficLabel(TrafficType trafficType) {
        _trafficType = trafficType; // Define empty traffic label with specific traffic type
    }

    public VmwareTrafficLabel() {
        _parseLabel();
    }

    private void _parseLabel() {
        if (_networkLabel != null) {
            String[] tokens = _networkLabel.split(",");
            if (tokens.length == 3) {
                _vSwitchName = tokens[0].trim();
                _vSwitchType = VirtualSwitchType.getType(tokens[2].trim());
                _vlanId = tokens[1].trim();
            }
            if(VirtualSwitchType.None == _vSwitchType) {
                throw new InvalidParameterValueException("Invalid virtual switch type : " + tokens[2].trim());
            }
        } else {
            _vSwitchName = "vSwitch0";
            _vSwitchType = VirtualSwitchType.StandardVirtualSwitch;
        }
    }

    @Override
    public TrafficType getTrafficType() {
        return _trafficType;
    }

    @Override
    public String getNetworkLabel() {
        return _networkLabel;
    }

    public VirtualSwitchType getVirtualSwitchType() {
        return _vSwitchType;
    }

    public String getVirtualSwitchName() {
        return _vSwitchName;
    }

    public String getVlanId() {
        return _vlanId;
    }
    public void setVirtualSwitchName(String vSwitchName) {
        _vSwitchName = vSwitchName;
    }

    public VirtualSwitchType setVirtualSwitchType(VirtualSwitchType vSwitchType) {
        return _vSwitchType = vSwitchType;
    }
}
