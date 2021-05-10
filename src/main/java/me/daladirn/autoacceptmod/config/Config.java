package me.daladirn.autoacceptmod.config;

import com.google.common.collect.Sets;
import com.google.gson.*;
import net.minecraft.util.Tuple;

import java.io.*;
import java.util.*;

class Vars {
    public Boolean enabled;
    public Set<String> names;

    public Vars () {
        enabled = true;
        names = new HashSet<String>();
    }
}

public class Config {
    private File configFile;
    private Boolean enabled;
    private Set<String> names;
    private JsonObject settingsConfig = new JsonObject();

    public Config (File configFile) {
        this.configFile = configFile;
        this.enabled = true;
        this.names = new HashSet<String>();
        readConfig();
    }

    public void readConfig () {
        try {
            System.out.println("READING CONFIG!!!!!!!__________________________");
            FileReader reader = new FileReader(configFile);
            Vars vars = new Vars();
            Gson gson = new GsonBuilder().create();
            vars = gson.fromJson(reader, Vars.class);
            enabled = vars.enabled;
            names = vars.names;
            reader.close();
        } catch (Exception ex) {
            return;
        }
    }

    public void writeConfig () {
        try {
            Writer writer = new FileWriter(configFile, false);
            Vars vars = new Vars();
            vars.enabled = enabled;
            vars.names = names;
            Gson gson = new GsonBuilder().create();
            gson.toJson(vars, writer);
            System.out.println("Writing config");
            writer.close();
        } catch (Exception e) {
            return;
        }
    }

    public Boolean getEnabled () {
        return enabled;
    }

    public Set<String> getNames () {
        return names;
    }

    public Boolean addName (String name) {
        return names.add(name.toLowerCase());
    }

    public void removeName (String name) {
        names.remove(name.toLowerCase());
    }

    public void toggle () {
        enabled = !enabled;
    }
}
