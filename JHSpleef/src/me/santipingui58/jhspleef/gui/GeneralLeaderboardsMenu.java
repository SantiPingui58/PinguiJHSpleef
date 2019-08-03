package me.santipingui58.jhspleef.gui;






import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import me.santipingui58.jhspleef.utils.ItemBuilder;





public class GeneralLeaderboardsMenu extends MenuBuilder {

	private HashMap<SpleefPlayer,Integer> page = new HashMap<SpleefPlayer,Integer>();
	private HashMap<SpleefPlayer,String> selected = new HashMap<SpleefPlayer,String>();
	
	public GeneralLeaderboardsMenu(Player p) {
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
	
		//Total time online
		//Total Coins
		//Players by country
		//Total Wins
		//Total Games
		
		if (selected.get(sp).equalsIgnoreCase("MonthlyWins")) {
			s(1,new ItemBuilder(Material.PAPER).setTitle("Sort by: Monthly Wins").addEnchantment(Enchantment.ARROW_FIRE, 1).build());
		} else {
			s(1,new ItemBuilder(Material.PAPER).setTitle("Sort by: Monthly Wins").build());
		}
		
		
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot) {

	}
	
	
	
	
	}



