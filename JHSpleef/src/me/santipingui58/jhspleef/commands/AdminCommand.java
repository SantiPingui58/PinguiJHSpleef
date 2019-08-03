package me.santipingui58.jhspleef.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class AdminCommand implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("afk")){
			final Player p = (Player) sender;
			if (p.hasPermission("*")) {
				if (args[0].equalsIgnoreCase("resetstats")) {
					
				}
			}
			}
			

}
		
		
		return false;
	}
	
	

	
}