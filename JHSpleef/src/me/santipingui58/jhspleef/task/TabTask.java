package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.scoreboard.PinguiScoreboard;


public class TabTask {

	

	public TabTask() {
		task();

	}
	
	
	private void task() {
		
		 Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
		          
               @Override
               public void run() {
            	   
            	   PinguiScoreboard.getScoreboard().setTags();
            	   
               }
		 }, 10, 60L);
		
	}
}
