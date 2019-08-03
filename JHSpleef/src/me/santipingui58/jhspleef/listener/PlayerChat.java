package me.santipingui58.jhspleef.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.santipingui58.jhspleef.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;



public class PlayerChat implements Listener {
	
	private List<Player> cooldown = new ArrayList<Player>();
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if (!p.hasPermission("jhspleef.chatcooldown")) {
			if (cooldown.contains(p)) {
					p.sendMessage("§cWait 2 seconds between messages.");
					e.setCancelled(true);
					return;
				}
				
			}
			
		
		
		String prefix = ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix());
		String msg = e.getMessage();
		 msg = e.getMessage().replaceAll("%", "%%");
		if (p.hasPermission("jhspleef.staff")) {
			 msg = ChatColor.translateAlternateColorCodes('&', msg);
			e.setFormat(prefix +p.getName() +"§8: §b"+msg );
		} else if (p.hasPermission("jhspleef.donatorchat")) {
			 msg = ChatColor.translateAlternateColorCodes('&', msg);
			e.setFormat(prefix +p.getName() +"§8: §f"+msg);
		} else {
			e.setFormat(prefix +" "+p.getName() +"§8: §7"+ msg );
		}
		
		cooldown.add(p);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				cooldown.remove(p);
			}
		}.runTaskLater(Main.get(), 40L);
	}
	
	

}
