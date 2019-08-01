package me.santipingui58.jhspleef.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.SpleefArena;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import me.santipingui58.jhspleef.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerConnectListener implements Listener {

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null); 
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
		Manager.getManager().loadData(e.getPlayer());
		SpleefPlayer sp = Manager.getManager().getSpleefPlayer(e.getPlayer());
		Manager.getManager().giveLobbyItems(sp);
		if (e.getPlayer().hasPermission("jhspleef.join")) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				String prefix = ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(e.getPlayer()).getPrefix());
				p.sendMessage(prefix+  e.getPlayer().getName() + " §ahas joined the server!");
			}
		}
		if (Main.arenas.getConfig().contains("mainlobby")) {
		e.getPlayer().teleport(Utils.getLoc(Main.arenas.getConfig().getString("mainlobby"), true));
	}
		
		if (e.getPlayer().hasPermission("jhspleef.fly")) {
			sp.fly();
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		SpleefPlayer sp = Manager.getManager().getSpleefPlayer(e.getPlayer());
		if (Manager.getManager().isInGame(sp)) {
			SpleefArena arena = Manager.getManager().getArenaByPlayer(sp);
			Manager.getManager().leaveQueue(sp, arena);
				for (SpleefPlayer s : arena.getViewers()) {
					s.getPlayer().sendMessage(ChatColor.GOLD + e.getPlayer().getName() + " §chas left the server!");
				}
			}	
		if (Manager.getManager().isInQueue(sp)) {
			SpleefArena arena = Manager.getManager().getArenaByPlayer(sp);
			Manager.getManager().leaveQueue(sp, arena);
		}
		Manager.getManager().saveData(e.getPlayer());
		}
}

	

