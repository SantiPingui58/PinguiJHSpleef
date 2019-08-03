package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.SpleefPlayer;




public class OnlineTimeTask {

	public OnlineTimeTask() {
		task();
	}
	
	private void task() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
			
		    public void run() {
		    	
		    	for (SpleefPlayer sp : Manager.getManager().getPlayers()) {
		    		sp.addOnlineTime();
		    	}
		    	
		    }
		    }, 20, 20*60L);
	}
}
