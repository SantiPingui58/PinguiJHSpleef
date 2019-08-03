package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.leaderboard.Leaderboard;
import me.santipingui58.jhspleef.leaderboard.LeaderboardManager;

public class LeaderboardSortTask {

	public LeaderboardSortTask() {
		Manager.getManager().savePlayers();
		task();
	}
	
	private void task() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
			
		    public void run() {
		    	for (Leaderboard leaderboard : LeaderboardManager.getManager().getLeaderboards()) {
		    		leaderboard.sort();
		    	}
		    }
		    }, 20, 20*60*5L);
	}
}
