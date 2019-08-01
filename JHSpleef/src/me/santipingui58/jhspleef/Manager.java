package me.santipingui58.jhspleef;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import me.santipingui58.jhspleef.game.GameEndReason;
import me.santipingui58.jhspleef.game.GameState;
import me.santipingui58.jhspleef.game.SpleefArena;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import me.santipingui58.jhspleef.game.SpleefType;
import me.santipingui58.jhspleef.scoreboard.ScoreboardType;
import me.santipingui58.jhspleef.task.ArenaNewStartTask;
import me.santipingui58.jhspleef.task.ArenaStartingCountdownTask;
import me.santipingui58.jhspleef.utils.ItemBuilder;
import me.santipingui58.jhspleef.utils.Utils;


public class Manager {
	private static Manager manager;	
	 public static Manager getManager() {
	        if (manager == null)
	        	manager = new Manager();
	        return manager;
	    }
	 
	 private List<SpleefArena> arenas = new ArrayList<SpleefArena>();
	 private List<SpleefPlayer> players = new ArrayList<SpleefPlayer>();
	 
	 public List<SpleefArena> getArenas() {
		 return this.arenas;
	 }
	 
	 public List<SpleefPlayer> getPlayers() {
		 return this.players;
	 }
	 
	 
	 public boolean isInGame(SpleefPlayer sp) {
		 for (SpleefArena arena : this.arenas) {
			 if (arena.getPlayers().contains(sp)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public boolean isInQueue(SpleefPlayer sp) {
		 for (SpleefArena arena : this.arenas) {
			 if (arena.getQueue().contains(sp)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
		public SpleefPlayer getSpleefPlayer(Player p) {	
			for (SpleefPlayer rp : players) {
				if (rp.getPlayer().equals(p)) {				
					return rp;
				}
			}
			SpleefPlayer nuevo = new SpleefPlayer(p);
			this.players.add(nuevo);
			return nuevo;
			
		}
		
	 public SpleefArena getArenaByPlayer(SpleefPlayer sp) {
		 for (SpleefArena arena : this.arenas) {
			 if (arena.getPlayers().contains(sp) || arena.getQueue().contains(sp)) {
				 return arena;
			 }
		 }
		 return null;
	 }
	 
	 public void loadPlayers() {
		 for (Player p : Bukkit.getOnlinePlayers()) {
			 loadData(p);
		 }
	 }
	 public void savePlayers() {
		 for (Player p : Bukkit.getOnlinePlayers()) {
			 saveData(p);
		 }
	 }
	 
	 public void saveData(Player p) {
		 SpleefPlayer sp = getSpleefPlayer(p);
		 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.totalgames",sp.getTotalGames());
		 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.ELO",sp.getELO());
		 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.1vs1_wins",sp.get1vs1Wins());
		 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.1vs1_games",sp.get1vs1Games());
		 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.FFA_wins",sp.getFFAWins());
		 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.FFA_games",sp.getFFAGames());
		 Main.data.saveConfig();
	 }
	 public void loadData(Player p) {
		 SpleefPlayer sp = getSpleefPlayer(p);
		 if (Main.data.getConfig().contains("players."+p.getUniqueId())) {
			 int games = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.totalgames");
			 int ELO = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.ELO");
			 int _1vs1wins = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.1vs1_wins");
			 int _1vs1games = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.1vs1_games");
			 int FFAwins = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.FFA_wins");
			 int FFAgames = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.FFA_games");
			 
			 sp.setTotalGames(games);
			 sp.setELO(ELO);
			 sp.set1vs1Wins(_1vs1wins);
			 sp.set1vs1Games(_1vs1games);
			 sp.setFFAWins(FFAwins);
			 sp.setFFAGames(FFAgames);
		 } else {
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.totalgames",0);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.ELO",1000);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.1vs1_wins",0);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.1vs1_games",0);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.FFA_wins",0);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.FFA_games",0);
			 Main.data.saveConfig();
		 }
		 
	 }
	 
	 public void savePing(Player p) {
		 InetAddress ip = p.getAddress().getAddress();
		 if (!Main.data.getConfig().contains("data."+p.getUniqueId()+".IP")) {
			 if (!ip.toString().equalsIgnoreCase(Main.data.getConfig().getString("data."+p.getUniqueId()+".IP"))) {
		   Main.data.getConfig().set("data." + p.getUniqueId() + ".IP", ip.toString());
		   Main.data.saveConfig();
			 }
		 }
		   
	 }
	 public SpleefArena loadArena(String name, Location mainspawn,Location lobby,Location arena1,Location arena2,SpleefType type) {       
		 SpleefArena a = new SpleefArena(name,mainspawn,lobby,arena1,arena2,type);
        this.arenas.add(a);      
        Manager.getManager().resetArena(a);
        return a;
    }
    
    
    
    public void loadArenas() { 
    	int arenasint = 0;
    	if (Main.arenas.getConfig().contains("arenas")) {
    	Set<String> arenas = Main.arenas.getConfig().getConfigurationSection("arenas").getKeys(false);
    	
    		for (String b : arenas) {		
    			try {
    				
    			Location mainspawn = Utils.getLoc(Main.arenas.getConfig().getString("arenas." + b + ".mainspawn"), false);
    			Location lobby = Utils.getLoc(Main.arenas.getConfig().getString("arenas." + b + ".lobby"), false);	
    			Location arena1 = Utils.getLoc(Main.arenas.getConfig().getString("arenas." + b + ".arena1"), false);
    			Location arena2 = Utils.getLoc(Main.arenas.getConfig().getString("arenas." + b + ".arena2"), false);		   		
				String type = Main.arenas.getConfig().getString("arenas."+b+".type");
				type = type.toUpperCase();
				SpleefType spleeftype = SpleefType.valueOf(type);
				Manager.getManager().loadArena(b,mainspawn,lobby,arena1,arena2,spleeftype);
				arenasint++;
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		
    	}
    		Main.get().getLogger().info(arenasint+ " arenas cargadas!");
    }
     



	public void addQueue(SpleefPlayer sp,SpleefArena arena) {
		arena.getQueue().add(sp);
		sp.setScoreboard(ScoreboardType.FFAGAME);
		giveQueueItems(sp);
		if (arena.getState().equals(GameState.GAME) || arena.getState().equals(GameState.FINISHING) || arena.getState().equals(GameState.STARTING)) {
			sp.getPlayer().sendMessage("§aYou have been added to the queue, you will play when the next game starts!");
		} else {
			for (SpleefPlayer p : arena.getQueue()) {
				p.getPlayer().sendMessage("§6" + sp.getPlayer().getName() + " §ahas joined the queue! Total: " + arena.getQueue().size());
			}
			
			if (arena.getQueue().size()>=3) {
				new ArenaNewStartTask(arena);
				arena.setState(GameState.STARTING);
				for (SpleefPlayer p : arena.getQueue()) {
					p.getPlayer().sendMessage("§bSpleef is starting in 5 seconds.");
				}
			
			
		}
		}
	}
	
	
	public void leaveQueue(SpleefPlayer sp, SpleefArena arena) {
		giveLobbyItems(sp);
		if (sp.getPlayer().isOnline()) {
		sp.getPlayer().sendMessage("§aYou have left the queue.");		
		sp.setScoreboard(ScoreboardType.LOBBY);
		}
		if (arena.getPlayers().contains(sp)) {
			arena.getPlayers().remove(sp);
			if (arena.getPlayers().size()<=1) {
				Manager.getManager().endGame(arena, GameEndReason.WINNER);
			}
		}		
			arena.getQueue().remove(sp);
			
	}
	public void startGame(SpleefArena arena) {
		arena.setState(GameState.STARTING);
		arena.resetTimer();
		resetArena(arena);
		for (SpleefPlayer sp : arena.getQueue()) {
			giveGameItems(sp);
			sp.setTotalGames(sp.getTotalGames()+1);
			sp.setFFAGames(sp.getFFAGames()+1);
			sp.getPlayer().teleport(arena.getMainSpawn());
			sp.getPlayer().setGameMode(GameMode.SURVIVAL);
			sp.stopfly();
			arena.getPlayers().add(sp);
		}
		
		
		new ArenaStartingCountdownTask(arena);
		
	}
	
	public void resetArena(SpleefArena arena) {
		
	Location a = arena.getArena1();
	Location b = arena.getArena2();
	  int ax = a.getBlockX();
	  int az = a.getBlockZ();	  
	  int y = a.getBlockY();		  
	  int bx = b.getBlockX();
	  int bz = b.getBlockZ();
  	   for (int x = ax; x < bx; x++) {
			  for (int z = az; z < bz; z++) {
				  Location aire = new Location (a.getWorld(), x, y, z);
				  if (aire.getBlock().getType().equals(Material.AIR)) {
				  aire.getBlock().setType(Material.SNOW_BLOCK); 
				  }
			  }
		  }
     
 
	}
	public void endGame(SpleefArena arena,GameEndReason reason) {		
		arena.setState(GameState.FINISHING);
		arena.resetTimer();
		if (reason.equals(GameEndReason.WINNER)) {
			SpleefPlayer winner = arena.getPlayers().get(0);
		
		
		if (winner!=null) {
			
			for (SpleefPlayer players : this.players) {
				players.getPlayer().sendMessage("§b"+winner.getPlayer().getName()+" §bhas won Spleef!");
				if (arena.getQueue().size()>=3) {
				players.getPlayer().sendMessage("§bSpleef is starting in 5 seconds.");
			}
			}
			
			winner.setFFAWins(winner.getFFAWins()+1);
			winner.getPlayer().teleport(arena.getLobby());
			giveLobbyItems(winner);
			arena.getPlayers().remove(winner);
			resetArena(arena);
			
		} 
		} else if (reason.equals(GameEndReason.TIME_OUT)) {
			for (SpleefPlayer players : this.players) {
				players.getPlayer().sendMessage("§bNobody has won because the game took too long!");
				if (arena.getQueue().size()>=3) {
				players.getPlayer().sendMessage("§bSpleef is starting in 5 seconds.");
				}  
				
				
			}
			
			
			for (SpleefPlayer p : arena.getPlayers()) {
				p.getPlayer().teleport(arena.getLobby());
				giveLobbyItems(p);
				arena.getPlayers().remove(p);
			}
		
		} else if (reason.equals(GameEndReason.LOG_OFF)) {
			for (SpleefPlayer players : this.players) {
				players.getPlayer().sendMessage("§bNobody has won Spleef.");
				if (arena.getQueue().size()>=3) {
				players.getPlayer().sendMessage("§bSpleef is starting in 5 seconds.");
				}  
			}
		}
		
		arena.getPlayers().clear();
		
		if (arena.getQueue().size()>=3) {
			new ArenaNewStartTask(arena);
			arena.setState(GameState.STARTING);
		} else {
			arena.setState(GameState.LOBBY);
		}
	}
	
	public void fell(SpleefPlayer sp) {
		SpleefArena arena = Manager.getManager().getArenaByPlayer(sp);
		arena.getPlayers().remove(sp);
		sp.getPlayer().teleport(arena.getLobby());
		giveLobbyItems(sp);
		if (arena.getPlayers().size()>1) {
			for (SpleefPlayer players : arena.getQueue()) {
				players.getPlayer().sendMessage("§6"+ sp.getPlayer().getName() + "§b has fallen! §a" + arena.getPlayers().size()+" §bplayers left!");
			}
		} else {
			endGame(arena,GameEndReason.WINNER);
		}
	
	}
	
	private void giveGameItems(SpleefPlayer sp) {
		sp.getPlayer().getInventory().clear();
		if (sp.getPlayer().hasPermission("jhspleef.donator")) {
			sp.getPlayer().getInventory().setItem(0, gameitems()[1]);
			sp.getPlayer().getInventory().setItem(1, gameitems()[3]);	
		} else {
			sp.getPlayer().getInventory().setItem(0, gameitems()[0]);
			sp.getPlayer().getInventory().setItem(1, gameitems()[2]);
		}
	}
	
	public void giveLobbyItems(SpleefPlayer sp) {
		sp.getPlayer().setGameMode(GameMode.ADVENTURE);
		sp.getPlayer().getInventory().clear();
		sp.getPlayer().getInventory().setItem(3, lobbyitems()[2]);
		sp.getPlayer().getInventory().setItem(4, lobbyitems()[0]);	
		sp.getPlayer().getInventory().setItem(5, lobbyitems()[1]);
	}
	
	public void giveQueueItems(SpleefPlayer sp) {
		sp.getPlayer().setGameMode(GameMode.ADVENTURE);
		sp.getPlayer().getInventory().clear();
		sp.getPlayer().getInventory().setItem(8, queueitems()[0]);		
	}
	
	public ItemStack[] gameitems() {
		ItemStack iron_shovel = new ItemStack(Material.IRON_SPADE);
		ItemMeta ironMeta = iron_shovel.getItemMeta();
		ironMeta.setUnbreakable(true);
		ironMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
		ironMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iron_shovel.setItemMeta(ironMeta);
		
		ItemStack diamond_shovel = new ItemStack(Material.DIAMOND_SPADE);
		ItemMeta diamondMeta = diamond_shovel.getItemMeta();
		diamondMeta.setUnbreakable(true);
		diamondMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
		diamondMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		diamond_shovel.setItemMeta(diamondMeta);
		
		ItemStack x2snowball = new ItemBuilder(Material.SNOW_BALL).setAmount(2).build();
		ItemStack x4snowball = new ItemBuilder(Material.SNOW_BALL).setAmount(4).build();
		ItemStack x6snowball = new ItemBuilder(Material.SNOW_BALL).setAmount(6).build();
		
		
		ItemStack[] items = {iron_shovel, diamond_shovel, x2snowball,x4snowball,x6snowball};
		return items;
	}
	
	public ItemStack[] lobbyitems() {
		
		ItemStack gadgets = new ItemBuilder(Material.CHEST).setTitle("§6§lGadgets").addLore("§cComing Soon").build();
		ItemStack leaderboards = new ItemBuilder(Material.PAPER).setTitle("§b§lLeaderboards").addLore("§cComing Soon").build();
		ItemStack ranked = new ItemBuilder(Material.NETHER_STAR).setTitle("§a§lRanked").addLore("§cComing Soon").build();
		ItemStack[] items = {ranked,gadgets,leaderboards};
		return items;
	}
	
	public ItemStack[] queueitems() {
		ItemStack powerups = new ItemBuilder(Material.ENDER_CHEST).setTitle("§d§lPowerUps").addLore("§cComing Soon").build();
		ItemStack[] items = {powerups};
		return items;
	}
	}
