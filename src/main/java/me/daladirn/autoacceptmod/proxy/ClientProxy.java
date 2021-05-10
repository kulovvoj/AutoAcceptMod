package me.daladirn.autoacceptmod.proxy;


import me.daladirn.autoacceptmod.config.Config;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    public void preInit (FMLPreInitializationEvent preEvent) {
        super.preInit(preEvent);
    }

    public void init (FMLInitializationEvent event) {
        super.init(event);
    }

    public void postInit (FMLPostInitializationEvent postEvent) {
        super.postInit(postEvent);
    }
}
