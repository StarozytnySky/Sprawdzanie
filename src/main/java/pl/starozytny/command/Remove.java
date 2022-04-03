package pl.starozytny.command;

import lombok.Getter;
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

public class Remove extends SimpleSubCommand {

	List<String> INFORM_REMOVE;
	List<String> INFORM_PLAYER;

	@Getter
	public final static Remove instance = new Remove();


	public Remove() {
		super("remove");
		setPermission("sprawdz.admin");
		setPermissionMessage(Messages.Error.NO_PERMISSION);
	}


	public Object ttt() {
		String targetPlayer = args[0];
		return null;
	}

	@Override
	public void onCommand() {

		Player player = (Player) sender;


		List<String> addedPlayers = Sprawdzanie.getInstance().getTemporaryPlayers();

		if (args.length == 0) {
			Common.tell(sender, Messages.Error.MISSING_PLAYER_NAME);
			return;
		}

		String targetPlayer = args[0];

		INFORM_REMOVE = Messages.Information.INFORM_REMOVE.stream().filter(Objects::nonNull).map(rawList -> rawList.
				replace("{target}", targetPlayer).
				replace("{staff}", sender.getName())).collect(Collectors.toList());

		INFORM_PLAYER = Messages.Information.INFORM_REMOVE.stream().filter(Objects::nonNull).collect(Collectors.toList());

		if (Bukkit.getPlayer(targetPlayer) != null) {
			if (addedPlayers.contains(targetPlayer)) {
				addedPlayers.remove(targetPlayer);
				for (final Player online : Remain.getOnlinePlayers()) {
					if (PlayerUtil.hasPerm(online, "sprawdz.admin"))
						Common.tell(online, INFORM_REMOVE);
				}
				Location location = new Location(
						Bukkit.getWorld(ConfigFile.getInstance().SPRAWDZARKA_WORLD), ConfigFile.getInstance().SPRAWDZARKA_X, ConfigFile.getInstance().SPRAWDZARKA_Y,
						ConfigFile.getInstance().SPRAWDZARKA_Z, ConfigFile.getInstance().SPRAWDZARKA_YAW, 0);
				findPlayer(targetPlayer).teleport(location);
				player.teleport(location);
			} else
				Common.tell(sender, Messages.Error.PLAYER_IS_NO_CHECKED.replace("{player}", targetPlayer));
		} else
			Common.tell(sender, Messages.Error.PLAYER_OFFLINE.replace("{player}", targetPlayer));
	}
}
