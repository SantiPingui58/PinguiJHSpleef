package me.santipingui58.jhspleef.gui;






import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import me.santipingui58.jhspleef.utils.ItemBuilder;





public class FFALeaderboardsMenu extends MenuBuilder {

	private HashMap<SpleefPlayer,Integer> page = new HashMap<SpleefPlayer,Integer>();
	private HashMap<SpleefPlayer,String> selected = new HashMap<SpleefPlayer,String>();
	
	public FFALeaderboardsMenu(Player p) {
		super("§2§lFFA Leaderboards",6);
		SpleefPlayer sp = Manager.getManager().getSpleefPlayer(p);
		if (!page.containsKey(sp)) {
			page.put(sp, 0);
		}
		
		if (!selected.containsKey(sp)) {
			selected.put(sp, "TotalWins");
		}
		for (int i = 0; i <=53; i++) {
			s(i,new ItemBuilder(Material.STAINED_GLASS_PANE,1,(byte)2).setTitle("§2").build());
		}
		
		if (page.get(sp)>0) {
			s(0,new ItemBuilder(Material.ARROW).setTitle("§aPrev Page").build());
		}
		
		s(8,new ItemBuilder(Material.ARROW).setTitle("§aNext Page").build());
	
		//FFA Total Wins
		//FFA Total Games
		//FFA Total Kills
		
		//FFA Monthly Wins
		//FFA Monthly Games
		//FFA Monthly Kills
		
		//FFA Weekly Wins
		//FFA Weekly Games
		//FFA Weekly Kills
		
		// W/G
		// K/G
		
		if (selected.get(sp).equalsIgnoreCase("MonthlyWins")) {
			s(1,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Monthly Wins").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(1,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Monthly Wins").build());
		}
		
		if (selected.get(sp).equalsIgnoreCase("MonthlyGames")) {
			s(2,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Monthly Games").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(2,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Monthly Games").build());
		}
		
		if (selected.get(sp).equalsIgnoreCase("MonthlyKills")) {
			s(3,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Monthly Kills").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(3,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Monthly Kills").build());
		}
		
		if (selected.get(sp).equalsIgnoreCase("WeeklyWins")) {
			s(5,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Weekly Wins").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(5,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Weekly Wins").build());
		}
		if (selected.get(sp).equalsIgnoreCase("WeeklyGames")) {
			s(6,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Weekly Games").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(6,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Weekly Games").build());
		}
		
		if (selected.get(sp).equalsIgnoreCase("WeeklyKills")) {
			s(7,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Weekly Kills").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(7,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Weekly Kills").build());
		}
		
		if (selected.get(sp).equalsIgnoreCase("WG")) {
			s(1,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Win/Game Ratio").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(1,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Win/Game Ratio").build());
		}
		
		if (selected.get(sp).equalsIgnoreCase("KG")) {
			s(1,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Kill/Game Ratio").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(1,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Kill/Game Ratio").build());
		}
		
		
		if (selected.get(sp).equalsIgnoreCase("TotalWins")) {
			s(1,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Total Wins").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(1,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Total Wins").build());
		}
		
		if (selected.get(sp).equalsIgnoreCase("TotalGames")) {
			s(2,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Total Games").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(2,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Total Games").build());
		}
		
		if (selected.get(sp).equalsIgnoreCase("TotalKills")) {
			s(3,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Total Kills").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(3,new ItemBuilder(Material.PAPER).setTitle("§aSort by: Total Kills").build());
		}
		

		
		
		
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot) {

	}
	
	
	
	
	}



