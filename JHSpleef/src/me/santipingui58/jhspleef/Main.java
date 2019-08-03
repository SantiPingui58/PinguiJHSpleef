package me.santipingui58.jhspleef;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.santipingui58.jhspleef.commands.AFKCommand;
import me.santipingui58.jhspleef.commands.FlyCommand;
import me.santipingui58.jhspleef.commands.LeaderboardCommand;
import me.santipingui58.jhspleef.commands.MsgCommand;
import me.santipingui58.jhspleef.commands.PingCommand;
import me.santipingui58.jhspleef.commands.RankCommand;
import me.santipingui58.jhspleef.commands.SetupCommand;
import me.santipingui58.jhspleef.leaderboard.LeaderboardManager;
import me.santipingui58.jhspleef.listener.PlayerChat;
import me.santipingui58.jhspleef.listener.PlayerConnectListener;
import me.santipingui58.jhspleef.listener.PlayerListener;
import me.santipingui58.jhspleef.listener.ServerListener;
import me.santipingui58.jhspleef.task.ArenaTimeTask;
import me.santipingui58.jhspleef.task.LeaderboardSortTask;
import me.santipingui58.jhspleef.task.OnMoveTask;
import me.santipingui58.jhspleef.task.OnlineTimeTask;
import me.santipingui58.jhspleef.task.ScoreboardTask;
import me.santipingui58.jhspleef.task.TabTask;
import me.santipingui58.jhspleef.utils.Configuration;


//TO DO LIST
//Leaderboard
//Configurar rangos
// /msg
// buycraft



public class Main extends JavaPlugin {

	public static Plugin pl;
	public static String prefix;
	public static boolean prefix_enabled;
	public static Configuration config,messages,arenas,data;
	public static Plugin get() {
	    return pl;
	  }	
	
	
	@Override
	public void onEnable() {
		pl = this;
	
		config = new Configuration("config.yml",this);
		data = new Configuration("data.yml",this);
		messages = new Configuration("messages.yml",this);
		arenas = new Configuration("arenas.yml",this);
		Manager.getManager().loadArenas();
		Manager.getManager().loadPlayers();
		LeaderboardManager.getManager().loadLeaderboards();
		registerTasks();
		registerEvents();
		registerCommands();
	
	}
		
	@Override
	public void onDisable() {
		Manager.getManager().savePlayers();
	}
	
	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerConnectListener(), this);
		getServer().getPluginManager().registerEvents(new ServerListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerChat(), this);
	}
	
	private void registerCommands() {
		getCommand("setup").setExecutor(new SetupCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("ping").setExecutor(new PingCommand());
		getCommand("leaderboard").setExecutor(new LeaderboardCommand());
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("msg").setExecutor(new MsgCommand());
		getCommand("afk").setExecutor(new AFKCommand());
	}
	
	private void registerTasks() {
		new OnMoveTask();
		new ScoreboardTask();
		new ArenaTimeTask();
		new LeaderboardSortTask();
		new TabTask();
		new OnlineTimeTask();
	}
	

	
}
