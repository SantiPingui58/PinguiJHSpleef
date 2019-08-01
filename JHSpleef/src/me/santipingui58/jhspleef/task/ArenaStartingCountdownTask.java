package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.game.GameState;
import me.santipingui58.jhspleef.game.SpleefArena;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import net.md_5.bungee.api.ChatColor;

public class ArenaStartingCountdownTask {

	
	private SpleefArena arena;
	private int time;
	private int task;
	public ArenaStartingCountdownTask(SpleefArena arena) {
		this.arena = arena;
		this.time = 5;
		task();
	}
	
	private void task() {
		task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
			
		    public void run() {
		    	
		    	if (arena.getQueue().size()>=3) {		    		
		    	
		    	if (time==3) {
		    		for (SpleefPlayer sp : arena.getPlayers()) {
		    			sp.getPlayer().sendMessage(ChatColor.AQUA+"READY!");
		    		}
		    	} else if (time==2) {
		    		for (SpleefPlayer sp : arena.getPlayers()) {
		    			sp.getPlayer().sendMessage(ChatColor.AQUA+"SET!");
		    		}
		    	} else if (time==1) {
		    		for (SpleefPlayer sp : arena.getPlayers()) {
		    			sp.getPlayer().sendMessage(ChatColor.AQUA+"GOOOOOOOO!");
		    		}
		    	}
		    	time = time-1;
		    	if (time<=0) {
		    		arena.setState(GameState.GAME);
		    		Bukkit.getScheduler().cancelTask(task);
		    	}
		    } else {
		    	arena.setState(GameState.LOBBY);
	    		Bukkit.getScheduler().cancelTask(task);
	    		for (SpleefPlayer p : arena.getQueue()) {
					p.getPlayer().sendMessage("§cThere are not enough players to start! Countdown cancelled.");
				}
		    }
		    }
		    }, 10, 20L);
	}
}
