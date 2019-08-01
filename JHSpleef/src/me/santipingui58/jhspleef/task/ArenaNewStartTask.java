package me.santipingui58.jhspleef.task;

import org.bukkit.Bukkit;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.GameState;
import me.santipingui58.jhspleef.game.SpleefArena;
import me.santipingui58.jhspleef.game.SpleefPlayer;

public class ArenaNewStartTask {

	
	private SpleefArena arena;
	private int task;
	private int time;
	public ArenaNewStartTask(SpleefArena arena) {
		task();
		this.time = 5;
		this.arena = arena;
	}
	
	
	private void task() {
		
		task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
		          
               @Override
               public void run() {
            	   
            	   if (arena.getQueue().size()>=3) {
            		   
            	   if (time==0) {
                   Manager.getManager().startGame(arena);
               	Bukkit.getScheduler().cancelTask(task);
            	   } 
            	   time--;
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
