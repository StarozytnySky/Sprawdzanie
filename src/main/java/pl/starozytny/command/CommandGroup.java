package pl.starozytny.command;

import org.mineacademy.fo.command.SimpleCommandGroup;

public class CommandGroup extends SimpleCommandGroup {

	@Override
	protected void registerSubcommands() {
		registerSubcommand(new Add());
		registerSubcommand(new Remove());
		registerSubcommand(new Reload());

	}

	/**
	 * Show all available command after typing main command
	 */
	@Override
	protected boolean sendHelpIfNoArgs() {
		return true;
	}
}
