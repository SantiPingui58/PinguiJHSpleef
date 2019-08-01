package me.santipingui58.jhspleef.leaderboard;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.utils.ValueComparator;

public class Leaderboard {

	private LeaderboardType type;
	private TreeMap<String, Integer> map = new TreeMap<String,Integer>();
	
	public Leaderboard(LeaderboardType type) {
	this.type = type;	
	}
	
	public LeaderboardType getType() {
		return this.type;
	}
	
	public TreeMap<String,Integer> getMap() {
		return this.map;
	}
	public void sort() {
		this.map.clear();
		Set<String> set = Main.data.getConfig().getConfigurationSection("players").getKeys(false);
    	for (String s : set) {
    		UUID uuid = UUID.fromString(s);
    	OfflinePlayer off = Bukkit.getOfflinePlayer(uuid);
    	String name = off.getName();
    	int i = 0;
    	
    	if (this.type.equals(LeaderboardType.ALL_TIME_FFA_WINS)) { 
    		 i = Main.data.getConfig().getInt("players."+s+".stats.FFA_wins");
    	}
    	this.map.put(name, i);
    	
    	}

		 this.map = sortMapByValue(map);
		return; 
	}
	
	
	private TreeMap<String, Integer> sortMapByValue(TreeMap<String, Integer> map2){
		Comparator<String> comparator = new ValueComparator(map2);
		TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
		result.putAll(map2);
		return result;
	}
}
