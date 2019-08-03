package me.santipingui58.jhspleef;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.santipingui58.jhspleef.game.DeathReason;
import me.santipingui58.jhspleef.game.GameEndReason;
import me.santipingui58.jhspleef.game.GameState;
import me.santipingui58.jhspleef.game.SpleefArena;
import me.santipingui58.jhspleef.game.SpleefKill;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import me.santipingui58.jhspleef.game.SpleefType;
import me.santipingui58.jhspleef.leaderboard.Leaderboard;
import me.santipingui58.jhspleef.leaderboard.LeaderboardManager;
import me.santipingui58.jhspleef.leaderboard.LeaderboardType;
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
		 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.FFA_kills",sp.getFFAKills());
		 
	int online = Main.data.getConfig().getInt("players."+p.getUniqueId()+".onlinetime");
	
	Main.data.getConfig().set("players."+p.getUniqueId()+".onlinetime", sp.getOnlineTime()+online);
		 Main.data.saveConfig();
	 }
	 public void loadData(Player p) {
		 SpleefPlayer sp = getSpleefPlayer(p);
		 if (Main.data.getConfig().contains("players."+p.getUniqueId())) {
			 int ELO = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.ELO");
			 int _1vs1wins = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.1vs1_wins");
			 int _1vs1games = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.1vs1_games");
			 int FFAwins = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.FFA_wins");
			 int FFAgames = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.FFA_games");
			 int FFAkills = 0;
			 if (Main.data.getConfig().contains("players."+p.getUniqueId()+".stats.FFA_kills")) {
			  FFAkills = Main.data.getConfig().getInt("players."+p.getUniqueId()+".stats.FFA_kills");
			 }
			 
			 
			 
			 
			 sp.setELO(ELO);
			 sp.set1vs1Wins(_1vs1wins);
			 sp.set1vs1Games(_1vs1games);
			 sp.setFFAWins(FFAwins);
			 sp.setFFAGames(FFAgames);
			 sp.setFFAKills(FFAkills);
		 } else {
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.ELO",1000);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.1vs1_wins",0);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.1vs1_games",0);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.FFA_wins",0);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.FFA_games",0);
			 Main.data.getConfig().set("players."+p.getUniqueId()+".stats.FFA_kills",0);
			 saveIP(p);
			 
			 Date now = new Date();
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Main.data.getConfig().set("players."+p.getUniqueId()+".registerdate", format.format(now));
			Main.data.getConfig().set("players."+p.getUniqueId()+".onlinetime",0);
			 Main.data.saveConfig();
		 }
		 
	 }
	 
	 public void saveIP(Player p) {
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
		arena.getKills().clear();
		if (reason.equals(GameEndReason.WINNER)) {
					
			SpleefPlayer winner = arena.getPlayers().get(0);
			SpleefPlayer winstreaker = null;
			int wins = 0;
			boolean isover = false;
		winner.getPlayer().playSound(winner.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.5F);
		
		if (arena.getWinStreak().isEmpty()) {
			arena.getWinStreak().put(winner, 1);
		} else {
			if (arena.getWinStreak().containsKey(winner)) {
				int w = arena.getWinStreak().get(winner)+1;
				 arena.getWinStreak().put(winner, w);
			} else {
				isover = true;
				Iterator<Entry<SpleefPlayer, Integer>> it = arena.getWinStreak().entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry<SpleefPlayer,Integer> pair = (Map.Entry<SpleefPlayer,Integer>)it.next();
			         winstreaker = pair.getKey();		  
			        wins = pair.getValue();
			    }
			    arena.getWinStreak().clear();
				 arena.getWinStreak().put(winner, 1);
				 
			}
		}
		
		for (SpleefPlayer players : this.players) {
			players.getPlayer().sendMessage("§b"+winner.getPlayer().getName()+" §bhas won Spleef!");
			if (arena.getQueue().size()>=3) {
			players.getPlayer().sendMessage("§bSpleef is starting in 5 seconds.");
			
		}
			if (isover) {
				if (wins>1) {
				players.getPlayer().sendMessage("§bWin Streak of " + winstreaker.getPlayer().getName() + " is over! Total wins: " + wins);
			}
			}
			
		}
		
		winner.setFFAWins(winner.getFFAWins()+1);
		winner.getPlayer().teleport(arena.getLobby());
		giveLobbyItems(winner);
		arena.getPlayers().remove(winner);
		resetArena(arena);
		} else if (reason.equals(GameEndReason.TIME_OUT)) {
			
			SpleefPlayer winstreaker = null;
			int wins = 0;
			Iterator<Entry<SpleefPlayer, Integer>> it = arena.getWinStreak().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<SpleefPlayer,Integer> pair = (Map.Entry<SpleefPlayer,Integer>)it.next();
		         winstreaker = pair.getKey();		  
		        wins = pair.getValue();
		    }
		    
			for (SpleefPlayer players : this.players) {
				players.getPlayer().sendMessage("§bNobody has won because the game took too long!");
				if (arena.getQueue().size()>=3) {
				players.getPlayer().sendMessage("§bSpleef is starting in 5 seconds.");
				
				}  
				
				if (wins>1) {
					players.getPlayer().sendMessage("§bWin Streak of " + winstreaker.getPlayer().getName() + " is over! Total wins: " + wins);
				}
				
				
			}
			arena.getWinStreak().clear();
			
			
			for (SpleefPlayer p : arena.getPlayers()) {
				p.getPlayer().teleport(arena.getLobby());
				giveLobbyItems(p);
			}
		
		} else if (reason.equals(GameEndReason.LOG_OFF)) {
			
			SpleefPlayer winstreaker = null;
			int wins = 0;
			Iterator<Entry<SpleefPlayer, Integer>> it = arena.getWinStreak().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<SpleefPlayer,Integer> pair = (Map.Entry<SpleefPlayer,Integer>)it.next();
		         winstreaker = pair.getKey();		  
		        wins = pair.getValue();
		    }
			for (SpleefPlayer players : this.players) {
				players.getPlayer().sendMessage("§bNobody has won Spleef.");
				if (arena.getQueue().size()>=3) {
				players.getPlayer().sendMessage("§bSpleef is starting in 5 seconds.");
				
				}  
				
				if (wins>1) {
					players.getPlayer().sendMessage("§bWin Streak of " + winstreaker.getPlayer().getName() + " is over! Total wins: " + wins);
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
	
	public void fell(SpleefPlayer sp,SpleefPlayer killer,DeathReason reason) {
		SpleefArena arena = Manager.getManager().getArenaByPlayer(sp);
		sp.getPlayer().playSound(sp.getPlayer().getLocation(), Sound.ENTITY_BLAZE_DEATH, 1.0F, 0.9F);
		killer.getPlayer().playSound(killer.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.75F);
		arena.getPlayers().remove(sp);
		sp.getPlayer().teleport(arena.getLobby());
		giveQueueItems(sp);
		if (arena.getPlayers().size()>1) {
			for (SpleefPlayer players : arena.getQueue()) {
				if (!sp.equals(killer)) {
					if (reason.equals(DeathReason.SPLEEFED)) {
				players.getPlayer().sendMessage("§6"+ sp.getPlayer().getName() + "§b was spleefed by §6"+ killer.getPlayer().getName() + "! §a" + arena.getPlayers().size()+" §bplayers left!");
					} else if (reason.equals(DeathReason.SNOWBALLED)) {
						players.getPlayer().sendMessage("§6"+ sp.getPlayer().getName() + "§b was snowballed by §6"+ killer.getPlayer().getName() + "! §a" + arena.getPlayers().size()+" §bplayers left!");
					}
					
					killer.setFFAKills(killer.getFFAKills()+1);
					} else {
				players.getPlayer().sendMessage("§6"+ sp.getPlayer().getName() + " §bspleefed themself! §a" + arena.getPlayers().size()+" §bplayers left!");
			}
			}
			
			
		} else {
			killer.setFFAKills(killer.getFFAKills()+1);
			
			endGame(arena,GameEndReason.WINNER);
			
		}
	
	}
	
	private void giveGameItems(SpleefPlayer sp) {
		sp.getPlayer().getInventory().clear();
		if (sp.getPlayer().hasPermission("jhspleef.diamondshovel")) {
			sp.getPlayer().getInventory().setItem(0, gameitems()[1]);
		} else {
			sp.getPlayer().getInventory().setItem(0, gameitems()[0]);
		}
		
		if (sp.getPlayer().hasPermission("jhspleef.x10snowballs")) {
			sp.getPlayer().getInventory().setItem(1, gameitems()[6]);
		} else if (sp.getPlayer().hasPermission("jhspleef.x8snowballs")) {
			sp.getPlayer().getInventory().setItem(1, gameitems()[5]);
		}else if (sp.getPlayer().hasPermission("jhspleef.x6snowballs")) {
			sp.getPlayer().getInventory().setItem(1, gameitems()[4]);
		}else if (sp.getPlayer().hasPermission("jhspleef.x4snowballs")) {
			sp.getPlayer().getInventory().setItem(1, gameitems()[3]);
		} else {
			sp.getPlayer().getInventory().setItem(1, gameitems()[2]);
		}
			
		
		giveLeaderboardHelmets(sp);
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
		sp.getPlayer().getInventory().setItem(0, lobbyitems()[2]);	
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
		ItemStack x8snowball = new ItemBuilder(Material.SNOW_BALL).setAmount(8).build();
		ItemStack x10snowball = new ItemBuilder(Material.SNOW_BALL).setAmount(10).build();
		
		ItemStack[] items = {iron_shovel, diamond_shovel, x2snowball,x4snowball,x6snowball,x8snowball,x10snowball};
		return items;
	}
	
	public ItemStack[] lobbyitems() {
		
		ItemStack gadgets = new ItemBuilder(Material.CHEST).setTitle("§6§lGadgets").addLore("§cComing Soon").build();
		ItemStack leaderboards = new ItemBuilder(Material.PAPER).setTitle("§b§lLeaderboards").build();
		ItemStack ranked = new ItemBuilder(Material.NETHER_STAR).setTitle("§a§lRanked").addLore("§cComing Soon").build();
		ItemStack[] items = {ranked,gadgets,leaderboards};
		return items;
	}
	
	public ItemStack[] queueitems() {
		ItemStack powerups = new ItemBuilder(Material.ENDER_CHEST).setTitle("§d§lPowerUps").addLore("§cComing Soon").build();
		ItemStack[] items = {powerups};
		return items;
	}
	
	
	public Location getNearest(Location l, List<SpleefKill> kills) {
		Location nearest = null;
	
		for (SpleefKill kill : kills) {
			if (nearest==null) {
				nearest = kill.getLocation();
			} else {
				if (l.distance(kill.getLocation()) < l.distance(nearest)) {
					nearest = kill.getLocation();
				}
			}
		}
	
	    
	    return nearest;
	}
	
	public SpleefPlayer getKillerByLocation(SpleefArena arena, Location l) {
		for (SpleefKill kill : arena.getKills()) {
			if (kill.getLocation().equals(l)) {
				return kill.getKiller();
			}
		}
		return null;
	}
	
	public DeathReason getReasonByKiller(SpleefArena arena,SpleefPlayer killer) {
		for (SpleefKill kill : arena.getKills()) {
			if (kill.getKiller().equals(killer)) {
				return kill.getReason();
			}
		}
		return null;
	}
	private void giveLeaderboardHelmets(SpleefPlayer sp) {
		Leaderboard leaderboard =LeaderboardManager.getManager().getLeaderboardByType(LeaderboardType.ALL_TIME_FFA_WINS);
		if (leaderboard.getTop20().contains(sp.getPlayer().getName())) {
		if (leaderboard.getTop20().get(0).equalsIgnoreCase(sp.getPlayer().getName())) {
			sp.getPlayer().getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		} else if (leaderboard.getTop20().get(1).equalsIgnoreCase(sp.getPlayer().getName())) {
			sp.getPlayer().getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
		} else if (leaderboard.getTop20().get(2).equalsIgnoreCase(sp.getPlayer().getName())) {
			sp.getPlayer().getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		} else {
			List<String> list = new ArrayList<String>();
			int y = 0;
			for (String s : leaderboard.getTop20()) {
				list.add(s);
				y++;
				
				if(y>=10) {
					break;
				}
			}
			
			if (list.contains(sp.getPlayer().getName())) {
				sp.getPlayer().getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
			}  else {
				sp.getPlayer().getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
			}
		}
	}
	}
	
	
	}
