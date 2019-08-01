package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.SpleefArena;
import me.santipingui58.jhspleef.game.SpleefPlayer;

public class OnMoveTask {

	public OnMoveTask() {
		task();
	}
	
	private void task() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
			
		    public void run() {
		    	for (SpleefPlayer sp : Manager.getManager().getPlayers()) {
		    		sp.getPlayer().setFireTicks(0);
		    		if (Manager.getManager().isInGame(sp)) {
		    			SpleefArena arena = Manager.getManager().getArenaByPlayer(sp);
		    			if (sp.getPlayer().getLocation().getBlockY()<arena.getArena1().getBlockY()) {
		    				Manager.getManager().fell(sp);
		    			}
		    		}
		    	}
		    }
		    }, 20, 8L);
	}
}
