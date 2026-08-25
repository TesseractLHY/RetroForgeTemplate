package com.example.retroforge.mc1710;

import com.example.retroforge.core.CommonModLogic;
import com.example.retroforge.core.platform.Platform;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        // Install the 1.7.10 platform implementation, then run shared logic.
        Platform.set(new Platform1710());
        CommonModLogic.init();
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}
}
