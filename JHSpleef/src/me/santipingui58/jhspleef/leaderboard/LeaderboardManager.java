package me.santipingui58.jhspleef.leaderboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import me.santipingui58.jhspleef.utils.Direction;
import me.santipingui58.jhspleef.utils.Utils;

public class LeaderboardManager {
	private static LeaderboardManager manager;	
	 public static LeaderboardManager getManager() {
	        if (manager == null)
	        	manager = new LeaderboardManager();
	        return manager;
	    }
	 
	 
	 private List<Leaderboard> leaderboards = new ArrayList<Leaderboard>();
	 
	 public List<Leaderboard> getLeaderboards() {
		 return this.leaderboards;
	 }
	 
	 public void loadLeaderboards() {
		 Leaderboard ffawins = new Leaderboard(LeaderboardType.ALL_TIME_FFA_WINS);
		 this.leaderboards.add(ffawins);
}
	 
	 public Leaderboard getLeaderboardByType(LeaderboardType type) {
		 for (Leaderboard leaderboard : this.leaderboards) {
			 if (leaderboard.getType().equals(type)) {
				 return leaderboard;
			 }
		 }
		 return null;
	 }
	 
	 public void generateHoloLeaderboard(LeaderboardType type) {
		 
	 }
	 
	 public void generateWallLeaderboard(LeaderboardType type,SpleefPlayer sp,Location l) {
		 Leaderboard leaderboard = this.getLeaderboardByType(type);
		 Direction dir = Direction.getDirection(sp.getPlayer().getLocation());
		 if (type.equals(LeaderboardType.ALL_TIME_FFA_WINS)) {
			 if (dir.equals(Direction.SOUTH)) {
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(4,-1,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(4,-1,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(4,-1,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));
				 l = l.add(-1,0,0);
				 l.getBlock().setType(Material.WALL_SIGN);
				 leaderboard.getLocations().add(new Location(l.getWorld(),l.getX(),l.getY(),l.getZ()));

					Main.arenas.getConfig().set("leaderboards."+type.toString()+".signs", Utils.setLocs(leaderboard.getLocations()));
					Main.arenas.saveConfig();
				
			 }
		 }
		 leaderboard.sort();
	 }
	 


		
}
