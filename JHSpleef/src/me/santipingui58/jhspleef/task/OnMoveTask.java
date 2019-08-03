package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;

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
		    		
		    		if (sp.getPlayer().hasPermission("jhspleef.afk")) {
		    		if (sp.isAfk()) {
		    			if (!sp.getLocation().equals(sp.getPlayer().getLocation())) {
		    			sp.back();
		    			sp.getPlayer().sendMessage("§7You are not longer AFK");	
		    			}
		    			
		    		}
		    		sp.setLocation(sp.getPlayer().getLocation());
		    		}
		    		sp.getPlayer().setFireTicks(0);
		    		if (Manager.getManager().isInGame(sp)) {
		    			SpleefArena arena = Manager.getManager().getArenaByPlayer(sp);
		    			if (sp.getPlayer().getLocation().getBlockY()<arena.getArena1().getBlockY()) {
		    				Location death_block = Manager.getManager().getNearest(sp.getPlayer().getLocation(), arena.getKills());
		    			SpleefPlayer killer =Manager.getManager().getKillerByLocation(arena, death_block);
		    				Manager.getManager().fell(sp,killer,Manager.getManager().getReasonByKiller(arena, killer));
		    				
		    			}
		    		}
		    	}
		    }
		    }, 20, 8L);
	}
}
