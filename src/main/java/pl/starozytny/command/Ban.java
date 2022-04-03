package pl.starozytny.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;
import org.mineacademy.fo.remain.Remain;
import pl.starozytny.Sprawdzanie;
import pl.starozytny.file.ConfigFile;
import pl.starozytny.file.Messages;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ban extends SimpleSubCommand {

	List<String> INFORM_SERVER;

	protected Ban() {
		super("ban");
		setPermission("sprawdz.admin");
		setPermissionMessage(Messages.Error.NO_PERMISSION);
	}

	@Override
	protected void onCommand() {
		List<String> addedPlayers = Sprawdzanie.getInstance().getTemporaryPlayers();

		checkConsole();
		Player player = (Player) sender;

		if (args.length == 0) {
			Common.tell(sender, Messages.Error.MISSING_PLAYER_NAME);
			return;
		}
		INFORM_SERVER = Messages.Information.INFORM_SERVER_BAN.stream().filter(Objects::nonNull).map(rawList -> rawList.
				replace("{player}", sender.getName())).collect(Collectors.toList());


		addedPlayers.remove(args[0]);
		Bukkit.dispatchCommand(sender, ConfigFile.getInstance().COMMAND_BAN.replace("{player}", args[0]));
		for (final Player online : Remain.getOnlinePlayers()) {
			Common.tell(online, INFORM_SERVER);
		}

	}
}
