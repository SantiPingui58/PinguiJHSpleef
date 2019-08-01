package me.santipingui58.jhspleef.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utils {
	
	
	  public static String setLoc(Location loc, boolean pitch)
	  {
	    if (pitch) {
	      return loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "," + loc.getYaw() + "," + loc.getPitch();
	    }
	    return loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
	  }
	  
	  public static List<String> setLocs(List<Location> locs) {
		  List<String> list = new ArrayList<String>();
			  for (Location loc : locs) {
		      list.add(loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
		    }
			  return list;
	  }

	  public static Location getLoc(String path, boolean pitch)
	  {
	    Location loc = null;
	    if (!pitch) {
	      String[] locs = path.split(",");
	      loc = new Location(Bukkit.getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
	      loc.add(0.5D, 0.0D, 0.5D);
	      return loc;
	    }
	    String[] locs = path.split(",");
	    loc = new Location(Bukkit.getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]), Float.valueOf(locs[4]).floatValue(), Float.valueOf(locs[5]).floatValue());
	    loc.add(0.5D, 0.0D, 0.5D);
	    return loc;
	  }

	  public static Location getLoc(String path)
	  {
	    String[] locs = path.split(",");

	    Location loc = new Location(Bukkit.getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
	    loc.add(0.5D, 0.0D, 0.5D);
	    return loc;
	  }
	  
	  
	  public static void debug(String s) {
		  Player p = Bukkit.getPlayer("SantiPingui58");
		  if (Bukkit.getOnlinePlayers().contains(p))
		  p.sendMessage(s);
	  }
	  
	  public static String getPluginPrefix() {
		return "§e[§5§lSpleefCTF§e] §f";
		  
	  }
	  
	  
	  public static Location getCenter(Location loc) {
		    return new Location(loc.getWorld(),
		        getRelativeCoord(loc.getBlockX()),
		        getRelativeCoord(loc.getBlockY()),
		        getRelativeCoord(loc.getBlockZ()));
		}
		 
		private static double getRelativeCoord(int i) {
		    double d = i;
		    d = d < 0 ? d - .5 : d + .5;
		    return d;
		}
	  
}
