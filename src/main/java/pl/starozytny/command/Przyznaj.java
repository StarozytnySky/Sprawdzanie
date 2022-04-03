package pl.starozytny.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.remain.Remain;
import pl.starozytny.Sprawdzanie;
import pl.starozytny.file.ConfigFile;
import pl.starozytny.file.Messages;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Przyznaj extends SimpleCommand {

	List<String> INFORM_SERVER;


	public Przyznaj() {
		super("przyznajesie");
		setPermission(null);
	}

	@Override
	protected void onCommand() {
		List<String> addedPlayers = Sprawdzanie.getInstance().getTemporaryPlayers();

		checkConsole();
		Player player = (Player) sender;

		INFORM_SERVER = Messages.Information.INFORM_SERVER_PRZYZNAJ.stream().filter(Objects::nonNull).map(rawList -> rawList.
				replace("{player}", sender.getName())).collect(Collectors.toList());


		if (addedPlayers.contains(player.getName())) {

			addedPlayers.remove(player.getName());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigFile.getInstance().COMMAND_PRZYZNANIE.replace("{player}", player.getName()));
			for (final Player online : Remain.getOnlinePlayers()) {
				Common.tell(online, INFORM_SERVER);
			}
		}

	}
}
