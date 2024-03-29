package mc.alk.arena.serializers;

import mc.alk.arena.util.Log;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseConfig implements FileConfig{
	FileConfiguration config;
	File file = null;

	
	public int getInt(String node,int defaultValue) {return config.getInt(node, defaultValue);}

	
	public boolean getBoolean(String node, boolean defaultValue) {return config.getBoolean(node, false);}

	
	public double getDouble(String node, double defaultValue) {return config.getDouble(node, defaultValue);}

	
	public String getString(String node,String defaultValue) {return config.getString(node,defaultValue);}

	public ConfigurationSection getConfigurationSection(String node) {return config.getConfigurationSection(node);}

	public BaseConfig(){}

	public BaseConfig(File file){
		setConfig(file);
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public File getFile() {
		return file;
	}

	public boolean setConfig(String file){
		return setConfig(new File(file));
	}

	public boolean setConfig(File file){
		this.file = file;
		if (!file.exists()){
			try {
				if (!file.createNewFile()){
                    Log.err("Couldn't create the config file=" + file);
                    return false;
                }
			} catch (IOException e) {
				Log.err("Couldn't create the config file=" + file);
				Log.printStackTrace(e);
				return false;
			}
		}

		config = new YamlConfiguration();
		try {
			config.load(file);
		} catch (Exception e) {
			Log.err("Couldn't load the config file=" + file);
			Log.printStackTrace(e);
			return false;
		}
		return true;
	}

	public void reloadFile(){
		try {
			config.load(file);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
	}
	public void save() {
		if (config == null)
			return;
		try {
			config.save(file);
		} catch (IOException e) {
			Log.printStackTrace(e);
		}
	}

	
	public List<String> getStringList(String node) {
		return config.getStringList(node);
	}

	
	public void load(File file) {
		this.file = file;
		reloadFile();
	}
}
