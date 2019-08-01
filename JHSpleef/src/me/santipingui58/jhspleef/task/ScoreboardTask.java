package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import me.santipingui58.jhspleef.scoreboard.PinguiScoreboard;



public class ScoreboardTask {

	
	public ScoreboardTask() {
		task();
	}
	
	
	private void task() {
	Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
			
		    public void run() {
		    	for (SpleefPlayer sp : Manager.getManager().getPlayers()) {
		    		PinguiScoreboard.getScoreboard().scoreboard(sp);;
				}
		    }
		    }, 10, 10L);
	
	
	}
}
