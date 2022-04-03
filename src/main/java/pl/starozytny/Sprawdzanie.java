package pl.starozytny;

import lombok.Getter;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;
import pl.starozytny.command.CommandGroup;
import pl.starozytny.command.Przyznaj;
import pl.starozytny.file.ConfigFile;
import pl.starozytny.file.Messages;
import pl.starozytny.file.Settings;
import pl.starozytny.listener.CommandEvent;
import pl.starozytny.listener.QuitEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sprawdzanie extends SimplePlugin {

	private static Sprawdzanie instance;

	/**
	 * @return the main instance class
	 */
	public static Sprawdzanie getInstance() {
		return instance;
	}

	/**
	 * Temporary stored players - is reset after server stop/restart
	 */
	public ArrayList<String> addedPlayers = new ArrayList<>();


	/**
	 * Manage settings and localization
	 */
	@Override
	public List<Class<? extends YamlStaticConfig>> getSettings() {
		return Arrays.asList(Messages.class, Settings.class);
	}

	/**
	 * Get temporary players in other class
	 */
	public ArrayList<String> getTemporaryPlayers() {
		return addedPlayers;
	}

	/**
	 * Register sub commands
	 */
	@Getter
	private final CommandGroup mainCommand = new CommandGroup();


	@Override
	protected void onPluginStart() {
		instance = this;

		registerCommand(new Przyznaj());
		ConfigFile.getInstance();

		registerEvents(new CommandEvent());
		registerEvents(new QuitEvent());

	}
}
