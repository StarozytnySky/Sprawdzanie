package pl.starozytny.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.mineacademy.fo.Common;
import pl.starozytny.Sprawdzanie;
import pl.starozytny.file.ConfigFile;
import pl.starozytny.file.Messages;

import java.util.List;

public class CommandEvent implements Listener {

	protected List<String> addedPlayers = Sprawdzanie.getInstance().getTemporaryPlayers();

	@EventHandler(priority = EventPriority.MONITOR)
	public void CommandBlock(PlayerCommandPreprocessEvent event) {

		String[] message = event.getMessage().split(" ");

		if (addedPlayers.contains(event.getPlayer().getName())) {
			if (!event.getPlayer().hasPermission("sprawdzanie.bypass")) {
				if (!ConfigFile.getInstance().ALLOWED_COMMANDS.contains(message[0].replaceFirst("/", ""))) {
					event.setCancelled(true);
					Common.tell(event.getPlayer(), Messages.Error.COMMAND_BLOCKED);
				}
			}
		}
	}
}
