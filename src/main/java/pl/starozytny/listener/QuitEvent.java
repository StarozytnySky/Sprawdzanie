package pl.starozytny.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.PlayerUtil;
import org.mineacademy.fo.remain.Remain;
import pl.starozytny.Sprawdzanie;
import pl.starozytny.file.ConfigFile;
import pl.starozytny.file.Messages;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuitEvent implements Listener {

	protected List<String> addedPlayers = Sprawdzanie.getInstance().getTemporaryPlayers();
	List<String> INFORM_LOGOUT;

	@EventHandler(priority = EventPriority.MONITOR)
	public void CommandBlock(PlayerQuitEvent event) {


		if (addedPlayers.contains(event.getPlayer().getName())) {
			if (Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigFile.getInstance().COMMAND_LOGOUT.replace("{player}", event.getPlayer().getName()))) {
				Common.tell(event.getPlayer(), Messages.Error.COMMAND_BLOCKED);
				for (final Player online : Remain.getOnlinePlayers()) {
					if (PlayerUtil.hasPerm(online, "sprawdz.admin")) {
						INFORM_LOGOUT = Messages.Information.INFORM_PLAYER_LOGOUT.stream().filter(Objects::nonNull).map(rawList -> rawList.
								replace("{target}", event.getPlayer().getName())).collect(Collectors.toList());
						Common.tell(online, INFORM_LOGOUT);
					}
				}
			}
		}
	}
}
