package com.example.retroforge.mc1122;

import com.example.retroforge.core.platform.IPlatform;

/** 1.12.2 implementation of the shared {@link IPlatform} seam. */
public class Platform1122 implements IPlatform {

    @Override
    public String platformName() {
        return "Forge 1.12.2";
    }

    @Override
    public void logInfo(String message) {
        RetroForge1122.LOG.info(message);
    }
}
