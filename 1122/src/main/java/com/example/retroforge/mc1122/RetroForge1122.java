package com.example.retroforge.mc1122;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.retroforge.core.CommonModLogic;
import com.example.retroforge.core.Constants;
import com.example.retroforge.core.platform.Platform;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Constants.MOD_ID,
        name = Constants.MOD_NAME,
        version = Constants.VERSION,
        acceptedMinecraftVersions = "[1.12.2]")
public class RetroForge1122 {

    public static final Logger LOG = LogManager.getLogger(Constants.MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Install the 1.12.2 platform implementation, then run shared logic.
        Platform.set(new Platform1122());
        CommonModLogic.init();
    }
}
