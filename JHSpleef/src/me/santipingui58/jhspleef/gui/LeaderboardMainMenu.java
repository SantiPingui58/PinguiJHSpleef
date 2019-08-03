package me.santipingui58.jhspleef.gui;






import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.jhspleef.utils.ItemBuilder;




public class LeaderboardMainMenu extends MenuBuilder {

	public static HashMap<Player,Integer> page = new HashMap<Player,Integer>();
	
	
	public LeaderboardMainMenu(Player p) {
		super("§2§lLeaderboards",3);
 
		s(11,new ItemBuilder(Material.PAPER).setTitle("§2FFA Leaderboards").build());
		s(13,new ItemBuilder(Material.PAPER).setTitle("§2General Leaderboards").addLore("§cComing soon").build());
		s(15,new ItemBuilder(Material.PAPER).setTitle("§21vs1 Leaderboards").addLore("§cComing soon").build());
	
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot) {
if (slot==11) {
	new FFALeaderboardsMenu(p).o(p);
} 
	}
	
	
	
	
	}



