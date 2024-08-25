package com.manaj.server.dto;

public class PCInfoWrapper {
    public PCInfo pcInfo;
    public long lastUpdateTime;
    public boolean up;

    public PCInfoWrapper(PCInfo pcInfo) {
        this.pcInfo = pcInfo;
        this.lastUpdateTime = System.currentTimeMillis();
        this.up = true;
    }

    public PCInfo getPcInfo() {
        return pcInfo;
    }

    public void setPcInfo(PCInfo pcInfo) {
        this.pcInfo = pcInfo;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    @Override
    public String toString() {
        return "PCInfoWrapper{" +
                "pcInfo=" + pcInfo +
                ", lastUpdateTime=" + lastUpdateTime +
                ", up=" + up +
                '}';
    }
}
