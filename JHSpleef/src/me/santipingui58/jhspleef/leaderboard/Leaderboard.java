package me.santipingui58.jhspleef.leaderboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Sign;
import org.bukkit.scheduler.BukkitRunnable;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.utils.Utils;
import me.santipingui58.jhspleef.utils.ValueComparator;

public class Leaderboard {

	private LeaderboardType type;
	private TreeMap<String, Integer> map = new TreeMap<String,Integer>();
	private List<String> top20 = new ArrayList<String>();
	private List<Location> locations = new ArrayList<Location>();
	public Leaderboard(LeaderboardType type) {
	this.type = type;	
	}
	
	public LeaderboardType getType() {
		return this.type;
	}
	public List<String> getTop20() {
		return this.top20;
	}
	public TreeMap<String,Integer> getMap() {
		return this.map;
	}
	
	public void sort() {
		
		List<String> topname = new ArrayList<String>();
		List<Integer> topint = new ArrayList<Integer>();
		this.map.clear();
		Set<String> set =  Main.data.getConfig().getConfigurationSection("players").getKeys(false);
		
    	for (String s : set) {
    		UUID uuid = UUID.fromString(s);
    	OfflinePlayer off = Bukkit.getOfflinePlayer(uuid);
    	String name = off.getName();
    	int i = 0;
    	
    	if (this.type.equals(LeaderboardType.ALL_TIME_FFA_WINS)) { 
    		 i = Main.data.getConfig().getInt("players."+s+".stats.FFA_wins");
    	}
    	try {
    	this.map.put(name, i);
    	} catch(Exception e) {
    		Bukkit.getLogger().info(s);
    		Bukkit.getLogger().info(uuid.toString());
    	}
    	}

		 this.map = sortMapByValue(map);
		
		 int x = 0;
		 top20.clear();
		 Iterator<Entry<String, Integer>> it = this.map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String,Integer> pair = (Map.Entry<String,Integer>)it.next();
		        String string = pair.getKey();		  
		       int i = pair.getValue();
		       topname.add(string);
		       top20.add(string);
		       topint.add(i);
		       x++;
		       if (x>=20) {
		    	   break;
		       }
		    }
		    
		    
		 int i = 1;
		 if (this.locations.isEmpty()) {
			 for (String s : Main.arenas.getConfig().getStringList("leaderboards."+this.type.toString()+".signs")) {
				 Location loc = Utils.getLoc(s);
				 this.locations.add(loc);
			 }
		 }
		 
		 for (Location l : this.locations) {
			if (l.getBlock().getType().equals(Material.WALL_SIGN)) {

				Sign sign = (Sign) l.getBlock().getState();
				final int z = i;
				new BukkitRunnable() {
				     @Override
				     public void run() {
				    	 sign.setLine(0, "§0§l"+z+".");
							sign.setLine(1, "§c§l"+topname.get(z-1));
							sign.setLine(2, "§3§lWins");
							sign.setLine(3, "§a§l"+topint.get(z-1));
							sign.update();
							
				          
				     }
				     }.runTaskLater(Main.get(), 5L);
				     
				
			} 
			i++;
		 }
		 i = 1;
		 
		return; 
	}
	
	public List<Location> getLocations() {
		return this.locations;
	}
	
	private TreeMap<String, Integer> sortMapByValue(TreeMap<String, Integer> map2){
		Comparator<String> comparator = new ValueComparator(map2);
		TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
		result.putAll(map2);
		return result;
	}
}
