package me.santipingui58.jhspleef.commands;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.santipingui58.jhspleef.leaderboard.Leaderboard;
import me.santipingui58.jhspleef.leaderboard.LeaderboardManager;
import me.santipingui58.jhspleef.leaderboard.LeaderboardType;
import me.santipingui58.jhspleef.utils.Messages;


public class LeaderboardCommand implements CommandExecutor {
	
	

		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			
			
			if(cmd.getName().equalsIgnoreCase("leaderboard")) {
			if(!(sender instanceof Player)) {
				
				sender.sendMessage(Messages.getMessage("console-not-allowed",null,null,null,null));
				return true;
				
			} else {
			Player p = (Player) sender;
			p.sendMessage("Leaderboard");
			Leaderboard leaderboard = LeaderboardManager.getManager().getLeaderboardByType(LeaderboardType.ALL_TIME_FFA_WINS);
			Iterator<Entry<String, Integer>> it = leaderboard.getMap().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String,Integer> pair = (Map.Entry<String,Integer>)it.next();
		       String name = pair.getKey();
		       int i = pair.getValue();
		       p.sendMessage(name + ":" + i);
			}
		    
		    p.sendMessage("Players: " + leaderboard.getMap().size());
			}
			}

			return false;
			
		}
}