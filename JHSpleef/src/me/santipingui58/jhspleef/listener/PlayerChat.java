package me.santipingui58.jhspleef.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;



public class PlayerChat implements Listener {
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		
		String prefix = ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix());
		String msg = e.getMessage();
		 msg = e.getMessage().replaceAll("%", "%%");
		if (p.hasPermission("jhspleef.staff")) {
			 msg = ChatColor.translateAlternateColorCodes('&', msg);
			e.setFormat(prefix +p.getName() +"§8: §b"+msg );
		} else if (p.hasPermission("jhspleef.donator")) {
			 msg = ChatColor.translateAlternateColorCodes('&', msg);
			e.setFormat(prefix +p.getName() +"§8: §3"+ ChatColor.translateAlternateColorCodes('&', msg) );
		} else {
			e.setFormat(prefix +" "+p.getName() +"§8: §7"+ msg );
		}
	}
	

}
