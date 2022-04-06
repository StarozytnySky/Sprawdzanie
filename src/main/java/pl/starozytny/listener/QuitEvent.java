package pl.starozytny.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.Remain;
import pl.starozytny.Sprawdzanie;
import pl.starozytny.file.ConfigFile;

import java.util.List;

public class QuitEvent implements Listener {

	protected List<String> addedPlayers = Sprawdzanie.getInstance().getTemporaryPlayers();
	List<String> INFORM_LOGOUT;

	@EventHandler(priority = EventPriority.MONITOR)
	public void playerQuit(PlayerQuitEvent event) {


		if (addedPlayers.contains(event.getPlayer().getName())) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigFile.getInstance().COMMAND_LOGOUT.replace("{player}", event.getPlayer().getName()));
			addedPlayers.remove(event.getPlayer().getName());
			for (final Player online : Remain.getOnlinePlayers()) {
				Common.tell(online, INFORM_LOGOUT);
			}
		}
	}
}
