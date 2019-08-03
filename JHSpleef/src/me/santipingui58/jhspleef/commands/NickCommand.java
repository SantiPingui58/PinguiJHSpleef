package me.santipingui58.jhspleef.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.SpleefPlayer;



public class NickCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
		if(cmd.getName().equalsIgnoreCase("nick")){
			Player p = (Player) sender;
			SpleefPlayer sp = Manager.getManager().getSpleefPlayer(p);
		if (p.hasPermission("jhspleef.nick")) {
			if (args.length==0) {
				
			} else {
				if (args.length==1) {
					if (args[0].equalsIgnoreCase("off")) {
						if (sp.hasNick()) {
							
						} else {
							
						}
					}
				}
			}
		}
		}
		}
		return false;
	}

}
