package com.example.retroforge.mc1710;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.retroforge.core.Constants;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Constants.MOD_ID,
        name = Constants.MOD_NAME,
        version = Constants.VERSION,
        acceptedMinecraftVersions = "[1.7.10]")
public class RetroForge1710 {

    public static final Logger LOG = LogManager.getLogger(Constants.MOD_ID);

    @SidedProxy(
            clientSide = "com.example.retroforge.mc1710.ClientProxy",
            serverSide = "com.example.retroforge.mc1710.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
