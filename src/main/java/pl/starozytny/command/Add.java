package pl.starozytny.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.PlayerUtil;
import org.mineacademy.fo.command.SimpleSubCommand;
import org.mineacademy.fo.remain.Remain;
import pl.starozytny.Sprawdzanie;
import pl.starozytny.file.ConfigFile;
import pl.starozytny.file.Messages;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Add extends SimpleSubCommand {

	List<String> INFORM_PLAYER;
	List<String> INFORM_STAFF;

	public Add() {
		super("add");
		setPermission("sprawdz.admin");
		setPermissionMessage(Messages.Error.NO_PERMISSION);
	}

	@Override
	public void onCommand() {
		checkConsole();

		List<String> addedPlayers = Sprawdzanie.getInstance().getTemporaryPlayers();

		if (args.length == 0) {
			Common.tell(sender, Messages.Error.MISSING_PLAYER_NAME);
			return;
		}


		String targetPlayer = args[0];

		INFORM_STAFF = Messages.Information.INFORM_STAFF_ADD.stream().filter(Objects::nonNull).map(rawList -> rawList.
				replace("{target}", targetPlayer).
				replace("{staff}", sender.getName())).collect(Collectors.toList());
		INFORM_PLAYER = Messages.Information.INFORM_PLAYER_ADD.stream().filter(Objects::nonNull).map(rawList -> rawList.
				replace("{staff}", sender.getName())).collect(Collectors.toList());

		if (Bukkit.getPlayer(targetPlayer) != null) {
			if (!addedPlayers.contains(targetPlayer)) {
				addedPlayers.add(targetPlayer);
				for (final Player online : Remain.getOnlinePlayers()) {
					if (PlayerUtil.hasPerm(online, "sprawdz.admin"))
						Common.tell(online, INFORM_STAFF);
				}
				Location location = new Location(
						Bukkit.getWorld(ConfigFile.getInstance().SPAWN_WORLD), ConfigFile.getInstance().SPAWN_X, ConfigFile.getInstance().SPAWN_Y,
						ConfigFile.getInstance().SPAWN_Z, ConfigFile.getInstance().SPAWN_YAW, 0);
				findPlayer(targetPlayer).teleport(location);
				Common.tell(findPlayer(targetPlayer), INFORM_PLAYER);
			} else
				Common.tell(sender, Messages.Error.PLAYER_IS_NOW_GEING_CHECKED.replace("{player}", targetPlayer));
		} else
			Common.tell(sender, Messages.Error.PLAYER_OFFLINE.replace("{player}", targetPlayer));
	}
}
