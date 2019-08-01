package me.santipingui58.jhspleef;


import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.santipingui58.jhspleef.commands.FlyCommand;
import me.santipingui58.jhspleef.commands.LeaderboardCommand;
import me.santipingui58.jhspleef.commands.PingCommand;
import me.santipingui58.jhspleef.commands.SetupCommand;
import me.santipingui58.jhspleef.leaderboard.LeaderboardManager;
import me.santipingui58.jhspleef.listener.PlayerChat;
import me.santipingui58.jhspleef.listener.PlayerConnectListener;
import me.santipingui58.jhspleef.listener.PlayerListener;
import me.santipingui58.jhspleef.listener.ServerListener;
import me.santipingui58.jhspleef.task.ArenaTimeTask;
import me.santipingui58.jhspleef.task.LeaderboardSortTask;
import me.santipingui58.jhspleef.task.OnMoveTask;
import me.santipingui58.jhspleef.task.ScoreboardTask;
import me.santipingui58.jhspleef.utils.Configuration;


//TO DO LIST
//Leaderboard
//Parpadeo Scoreboard
//Configurar rangos
// /msg /fly /ping 
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
		config();
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
	}
	
	private void registerTasks() {
		new OnMoveTask();
		new ScoreboardTask();
		new ArenaTimeTask();
		new LeaderboardSortTask();
	}
	private void config() {
		if (getConfig().getString("plugin-chat-prefix")!=null) {
			prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("plugin-chat-prefix"));
		} else {
			getConfig().set("plugin-chat-prefix", "&5[&e&FactionsVault&5]");
			prefix = "&c[&5[&e&FactionsVault&5]";
		}
			
		if (getConfig().get("prefix-enabled")!=null) {
			prefix_enabled = getConfig().getBoolean("prefix-enabled");
		} else {
			getConfig().set("prefix-enabled", true);
			prefix_enabled = true;	
		}
	}
	
}
