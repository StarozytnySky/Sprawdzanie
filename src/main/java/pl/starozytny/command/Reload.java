package pl.starozytny.command;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;
import pl.starozytny.file.ConfigFile;
import pl.starozytny.file.Messages;

public class Reload extends SimpleSubCommand {

	protected Reload() {
		super("reload");
		setPermission("sprawdz.reload");
		setPermissionMessage(Messages.Error.NO_PERMISSION);
	}

	@Override
	protected void onCommand() {

		ConfigFile.getInstance().reload();
		Common.tell(sender, "reloaded");

	}
}
