package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.GameEndReason;
import me.santipingui58.jhspleef.game.GameState;
import me.santipingui58.jhspleef.game.SpleefArena;

public class ArenaTimeTask {

	public ArenaTimeTask() {
		task();
	}
	
	private void task() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
			
		    public void run() {
		    	for (SpleefArena arena : Manager.getManager().getArenas()) {
		    		if (arena.getState().equals(GameState.GAME)) {	
		    			arena.time();
		    		}
		    		
		    		if (arena.getTime()==0) {
		    			
		    			Manager.getManager().endGame(arena,GameEndReason.TIME_OUT);
		    			
		    		}
		    	}
		    }
		    }, 20, 20L);
	}
}
