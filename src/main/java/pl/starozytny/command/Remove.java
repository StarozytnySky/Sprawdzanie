package pl.starozytny.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;
import pl.starozytny.Sprawdzanie;
import pl.starozytny.file.ConfigFile;
import pl.starozytny.file.Messages;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Remove extends SimpleSubCommand {

	List<String> INFORM_STAFF;
	List<String> INFORM_PLAYER;

	public Remove() {
		super("remove");
		setPermission("sprawdz.admin");
		setPermissionMessage(Messages.Error.NO_PERMISSION);
	}


	@Override
	public void onCommand() {

		List<String> addedPlayers = Sprawdzanie.getInstance().getTemporaryPlayers();

		String targetPlayer = args[0];

		INFORM_STAFF = Messages.Information.INFORM_STAFF_REMOVE.stream().filter(Objects::nonNull).map(rawList -> rawList.
				replace("{target}", targetPlayer).
				replace("{staff}", sender.getName())).collect(Collectors.toList());

		INFORM_PLAYER = Messages.Information.INFORM_PLAYER_REMOVE.stream().filter(Objects::nonNull).collect(Collectors.toList());

		if (Bukkit.getPlayer(targetPlayer) != null) {
			if (addedPlayers.contains(targetPlayer)) {
				addedPlayers.remove(targetPlayer);
				Common.broadcastWithPerm("sprawdzanie.admin", String.valueOf(INFORM_STAFF), true);
				Location location = new Location(
						Bukkit.getWorld(ConfigFile.getInstance().SPRAWDZARKA_WORLD), ConfigFile.getInstance().SPRAWDZARKA_X, ConfigFile.getInstance().SPRAWDZARKA_Y,
						ConfigFile.getInstance().SPRAWDZARKA_X, ConfigFile.getInstance().SPRAWDZARKA_YAW, 0);
				findPlayer(targetPlayer).teleport(location);
				Common.tell(findPlayer(targetPlayer), INFORM_PLAYER);
			} else
				Common.tell(sender, Messages.Error.PLAYER_IS_NOW_GEING_CHECKED.replace("{player}", targetPlayer));
		} else
			Common.tell(sender, Messages.Error.PLAYER_OFFLINE.replace("{player}", targetPlayer));
	}
}
