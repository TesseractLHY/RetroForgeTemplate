package com.example.retroforge.mc1710;

import com.example.retroforge.core.platform.IPlatform;

/** 1.7.10 implementation of the shared {@link IPlatform} seam. */
public class Platform1710 implements IPlatform {

    @Override
    public String platformName() {
        return "Forge 1.7.10";
    }

    @Override
    public void logInfo(String message) {
        RetroForge1710.LOG.info(message);
    }
}
