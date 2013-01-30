package com.cloud.network;

import com.cloud.network.Networks.TrafficType;

public interface TrafficLabel {

    public TrafficType getTrafficType();

    public String getNetworkLabel();

}
