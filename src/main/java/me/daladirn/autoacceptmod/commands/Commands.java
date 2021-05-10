package me.daladirn.autoacceptmod.commands;

import me.daladirn.autoacceptmod.AutoAcceptMod;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Collections;
import java.util.List;

public class Commands extends CommandBase {

    private AutoAcceptMod aam;

    public Commands(AutoAcceptMod aam) {
        this.aam = aam;
    }

    /**
     * Gets the name of the command
     */
    public String getCommandName() {
        return "autoacceptmod";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel() {
        return 0;
    }

    /**
     * Returns the aliases of this command
     */
    public List<String> getCommandAliases() {
        return Collections.singletonList("aam");
    }

    /**
     * Gets the usage string for the command.
     */
    public String getCommandUsage(ICommandSender sender) {
        return  "------------[AutoAcceptMod]------------" + "\n" +
                "- /aam toggle - Enables/Disables AutoAcceptMod" + "\n" +
                "- /aam add <name> - Whitelist a name" + "\n" +
                "- /aam remove <name> - Blacklist a name" + "\n" +
                "- /aam list - Lists all whitelisted names" + "\n" +
                "--------------------------------------";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("toggle")) {
                aam.config.toggle();
                aam.config.writeConfig();
                if (aam.config.getEnabled()) {
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("AAM: Enabled"));
                } else {
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("AAM: Disabled"));
                }
            } else if (args.length > 1 && args[0].equalsIgnoreCase("add")) {
                if (aam.config.addName(args[1])) {
                    aam.config.writeConfig();
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("AAM: " + args[1] + " whitelisted"));
                } else {
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("AAM: " + args[1] + " already whitelisted"));
                }
            } else if (args.length > 1 && args[0].equalsIgnoreCase("remove")) {
                if (aam.config.getNames().contains(args[1])) {
                    aam.config.removeName(args[1]);
                    aam.config.writeConfig();
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("AAM: " + args[1] + " blacklisted"));
                } else {
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("AAM: " + args[1] + " already blacklisted"));
                }
            } else if (args[0].equalsIgnoreCase("list")) {
                    if (!aam.config.getNames().isEmpty()) {
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("AAM: " + String.join(", ", aam.getConfig().getNames())));
                    } else {
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("AAM: Whitelist is empty"));
                    }

            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
        } else {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(getCommandUsage(sender)));
        }
    }
}
