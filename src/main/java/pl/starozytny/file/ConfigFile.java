package pl.starozytny.file;

import lombok.Getter;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.List;

public class ConfigFile extends YamlConfig {

	/**
	 * The singleton of this class
	 */
	@Getter
	public static ConfigFile instance = new ConfigFile();

	@Override
	protected boolean saveComments() {
		return true;
	}


	public float SPAWN_YAW;
	public Double SPAWN_X;
	public Double SPAWN_Y;
	public Double SPAWN_Z;
	public String SPAWN_WORLD;

	public Integer SPRAWDZARKA_YAW;
	public Double SPRAWDZARKA_X;
	public Double SPRAWDZARKA_Y;
	public Double SPRAWDZARKA_Z;
	public String SPRAWDZARKA_WORLD;

	public String COMMAND_LOGOUT;

	public List<String> ALLOWED_COMMANDS;

	public ConfigFile() {

		this.loadConfiguration(this.getSettingsFileName());
	}


	/*
	 * Automatically load the data upon calling CustomDataStorage#getInstance()
	 */
	public String getSettingsFileName() {
		return "config.yml";
	}

	/**
	 * Automatically called when the file is loaded, you can
	 * pull your values from the file here.
	 */
	@Override
	protected void onLoadFinish() {

		SPAWN_YAW = getInteger("Settings.Spawn.yaw");
		SPAWN_X = getDouble("Settings.Spawn.z");
		SPAWN_Y = getDouble("Settings.Spawn.y");
		SPAWN_Z = getDouble("Settings.Spawn.z");
		SPAWN_WORLD = getString("Settings.Spawn.world");

		SPRAWDZARKA_YAW = getInteger("Settings.Sprawdzarka.yaw");
		SPRAWDZARKA_X = getDouble("Settings.Sprawdzarka.z");
		SPRAWDZARKA_Y = getDouble("Settings.Sprawdzarka.y");
		SPRAWDZARKA_Z = getDouble("Settings.Sprawdzarka.z");
		SPRAWDZARKA_WORLD = getString("Settings.Sprawdzarka.world");

		COMMAND_LOGOUT = getString("Settings.Command.logout");

		ALLOWED_COMMANDS = getStringList("Settings.Allowed_Commands");

	}
}
