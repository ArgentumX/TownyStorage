package com.shatun.townystorage.utils;

import com.shatun.townystorage.TownyStorage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileUtil {
    public static FileConfiguration getCongigFile(String fileSegment, String fileName){
        File file = new File(TownyStorage.plugin.getDataFolder(), fileSegment + fileName);
        if (!file.exists()){
            TownyStorage.plugin.getResource(fileName);
            file = new File(TownyStorage.plugin.getDataFolder(), fileSegment + fileName);
        }
        return YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration getDataFile(String fileSegment, String fileName){
        File file = new File(TownyStorage.plugin.getDataFolder(), fileSegment + fileName);
        return YamlConfiguration.loadConfiguration(file);
    }
    public static HashMap<String, YamlConfiguration> getDataFilesConfigurations(String fileSegment){
        HashMap<String, YamlConfiguration> dataFiles = new HashMap<>();
        for (File file : getDirectory(fileSegment).listFiles()){
            dataFiles.put(file.getName().replace(".yml", ""),YamlConfiguration.loadConfiguration(file));
        }
        return dataFiles;
    }
    public static File getDirectory(String path){
        File file = new File(TownyStorage.plugin.getDataFolder(), path);
        return file;
    }
    public static void saveDataFile(FileConfiguration toSave, String fileSegment, String fileName){
        try {
            toSave.save(new File(TownyStorage.plugin.getDataFolder(), fileSegment + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removeFile(String fileSegment, String fileName){
        File fileToRemove =  new File(TownyStorage.plugin.getDataFolder(), fileSegment + fileName);
        if (fileToRemove.exists()){
            fileToRemove.delete();
        }
    }
    public static void renameFile(String fileSegment, String oldName, String newName){
        File fileToRename =  new File(TownyStorage.plugin.getDataFolder(), fileSegment + oldName);
        fileToRename.renameTo(new File(TownyStorage.plugin.getDataFolder(), fileSegment + newName));
    }
}
