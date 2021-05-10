package me.daladirn.autoacceptmod;

import me.daladirn.autoacceptmod.commands.Commands;
import me.daladirn.autoacceptmod.config.Config;
import me.daladirn.autoacceptmod.handlers.ChatHandler;
import me.daladirn.autoacceptmod.proxy.CommonProxy;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static me.daladirn.autoacceptmod.NGGlobal.MOD_ID;

@Mod(modid = MOD_ID, name = NGGlobal.MOD_NAME, version = NGGlobal.VERSION)
public class AutoAcceptMod
{
    public Config config;

    @Mod.Instance(MOD_ID)
    public static AutoAcceptMod instance;

    @SidedProxy(clientSide = NGGlobal.NG_CLIENT_PROXY, serverSide = NGGlobal.NG_COMMON_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent preEvent) {
        this.proxy.preInit(preEvent);
        config = new Config(preEvent.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        this.proxy.init(event);

        ClientCommandHandler.instance.registerCommand(new Commands(this));
        MinecraftForge.EVENT_BUS.register(new ChatHandler(this));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent postEvent) {
        this.proxy.postInit(postEvent);
    }

    public static AutoAcceptMod getInstance() {
        return instance;
    }

    public Config getConfig() {
        return config;
    }

}
