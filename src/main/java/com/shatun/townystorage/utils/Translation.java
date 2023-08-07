package com.shatun.townystorage.utils;

import com.shatun.townystorage.utils.FileUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;

public class Translation {
    private static HashMap<String, String> TranslationMap = new HashMap<>();
    public static String of(String key){
        return TranslationMap.get("PREFIX") + TranslationMap.getOrDefault(key,"");
    }
    public static String of(String key, boolean needsPrefix){
        if (!needsPrefix)
            return TranslationMap.getOrDefault(key,"");
        return TranslationMap.get("PREFIX") + TranslationMap.getOrDefault(key,"");
    }
    public static void initialize(){
        TranslationMap = new HashMap<>();
        FileConfiguration languageConfig = FileUtil.getCongigFile(Paths.SETTINGS, "language.yml");
        for (String key : languageConfig.getKeys(false)){
            TranslationMap.put(key, languageConfig.getString(key).replace("&", "§"));
        }
    }
}