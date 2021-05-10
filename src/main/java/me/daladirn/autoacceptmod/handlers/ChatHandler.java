package me.daladirn.autoacceptmod.handlers;

import com.google.common.eventbus.Subscribe;
import me.daladirn.autoacceptmod.AutoAcceptMod;
import me.daladirn.autoacceptmod.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatHandler {
    private AutoAcceptMod aam;

    public ChatHandler (AutoAcceptMod aam) {
        this.aam = aam;
    }

    @SubscribeEvent
    public void messageReceived (ClientChatReceivedEvent event) {
        if (aam.config.getEnabled()) {
            String message = event.message.getUnformattedText();
            Boolean isInvite = message.contains("has invited you to join their party!");
            if (isInvite) {
                String name = message.replaceAll("(\\[.*\\] )|( has invited you to join their party!)|(You have 60 seconds to accept. Click here to join!)|(-*)", "");

                name = name.replaceAll("\\s+","");
                if (aam.config.getNames().contains(name.toLowerCase())) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/p accept " + name);
                }
            }
        }
    }
}
