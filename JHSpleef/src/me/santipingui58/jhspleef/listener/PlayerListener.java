package me.santipingui58.jhspleef.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.GameState;
import me.santipingui58.jhspleef.game.SpleefPlayer;

public class PlayerListener implements Listener {

	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
	
		SpleefPlayer sp = Manager.getManager().getSpleefPlayer(p);
		if (!p.getGameMode().equals(GameMode.CREATIVE)) {
			if (Manager.getManager().isInGame(sp)) {
				if (Manager.getManager().getArenaByPlayer(sp).getState().equals(GameState.GAME)) {
				if (!e.getBlock().getType().equals(Material.SNOW_BLOCK)) {
					e.setCancelled(true);
				}
				} else {
					e.setCancelled(true);
				}
			} else {
				e.setCancelled(true);
			}
		} 
	}
	
	@EventHandler
	public void onHand(PlayerSwapHandItemsEvent e) {
		e.setCancelled(true);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			e.setCancelled(true);
		} else {
			for (ItemStack i : Manager.getManager().lobbyitems()) {
				if (i.equals(e.getPlayer().getItemInHand())) {	
					e.setCancelled(true);
					return;
				}
			}
			
			for (ItemStack i : Manager.getManager().queueitems()) {
				if (i.equals(e.getPlayer().getItemInHand())) {
			e.setCancelled(true);
			return;
				}
			}
		}
	}
	
	
	
}
