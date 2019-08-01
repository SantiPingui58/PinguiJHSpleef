package me.santipingui58.jhspleef.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.GameState;
import me.santipingui58.jhspleef.game.SpleefArena;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import net.md_5.bungee.api.ChatColor;

public class ServerListener implements Listener {

	
	@EventHandler
	public void onSmelt(BlockFadeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler 
	public void onDeath(EntityDamageByEntityEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onSign(SignChangeEvent e) {
		
			for (int i = 0; i < 4; i++) {
	            String line = e.getLine(i);
	            if (line != null && !line.equals("")) {
	                e.setLine(i, ChatColor.translateAlternateColorCodes('&', line));
	            }
			}
		
		if (e.getLine(0).equalsIgnoreCase("[Spleef]") && e.getLine(1).equalsIgnoreCase("Join")) {
			for (SpleefArena arena : Manager.getManager().getArenas()) {
				if (arena.getName().equals(e.getLine(2))) {
					e.setLine(0, "§0§l[Spleef]");
					e.setLine(1, "§aJoin");
					e.setLine(2, "§5§l"+arena.getName());
					return;
				}
			}
			e.getPlayer().sendMessage("§cThe arena §b"+ e.getLine(2) + " §cdoesnt exist.");
		} else if (e.getLine(0).equalsIgnoreCase("[Spleef]") && e.getLine(1).equalsIgnoreCase("Leave")) {
			for (SpleefArena arena : Manager.getManager().getArenas()) {
				if (arena.getName().equals(e.getLine(2))) {
					e.setLine(0, "§0§l[Spleef]");
					e.setLine(1, "§cLeave");
					e.setLine(2, "§5§l"+arena.getName());
					return;
				}
			}
			e.getPlayer().sendMessage("§cThe arena §b"+ e.getLine(2) + " §cdoesnt exist.");
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
	    SpleefPlayer sp = Manager.getManager().getSpleefPlayer(p);
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getState() instanceof Sign) {
			
				Sign sign = (Sign) e.getClickedBlock().getState();
				if (sign.getLine(0).contains("[Spleef]") && sign.getLine(1).contains("Join")) {
					for (SpleefArena arena : Manager.getManager().getArenas()) {
						String name = "§5§l"+arena.getName();
						if (name.equalsIgnoreCase(sign.getLine(2))) {
							if (!arena.getQueue().contains(sp)) {
							Manager.getManager().addQueue(sp, arena); 
							} else {
								p.sendMessage("§cYou are already on the queue!");
							}
							return;
						} 
					}
				} else if (sign.getLine(0).contains("[Spleef]") && sign.getLine(1).contains("Leave")) {
					for (SpleefArena arena : Manager.getManager().getArenas()) {
						String name = "§5§l"+arena.getName();
						if (name.equalsIgnoreCase(sign.getLine(2))) {
							if (arena.getQueue().contains(sp)) {
							Manager.getManager().leaveQueue(sp, arena); 
							} else {
								p.sendMessage("§cYou are not in the queue!");
							}
							return;
						} 
					}
				}
			}
		} 
	}
	
	@EventHandler
	public void onInventoryMove(InventoryClickEvent e) {
		for (ItemStack i : Manager.getManager().lobbyitems()) {
			if (e.getCurrentItem()!=null) {
				if (e.getCurrentItem().equals(i)) {
					e.setCancelled(true);
					return;
				}
			}
		}
		
		for (ItemStack i : Manager.getManager().queueitems()) {
			if (e.getCurrentItem()!=null) {
				if (e.getCurrentItem().equals(i)) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	  public void onProjectileHit(ProjectileHitEvent e)  {
		if (e.getEntity().getShooter() instanceof Player) {
	    Player p = (Player)e.getEntity().getShooter();
	    SpleefPlayer sp = Manager.getManager().getSpleefPlayer(p);
	    if (Manager.getManager().isInGame(sp)) {
	    	if (Manager.getManager().getArenaByPlayer(sp).getState().equals(GameState.GAME)) {
	    BlockIterator iterator = new BlockIterator(e.getEntity().getWorld(), e.getEntity().getLocation().toVector(), e.getEntity().getVelocity().normalize(), 0.0D, 4);
	    Block hitblock = null;
	    while (iterator.hasNext()) {
	      hitblock = iterator.next();

	      if (hitblock.getTypeId() != 0)
	      {
	        break;
	      }
	    }
	    if (hitblock.getType() == Material.SNOW_BLOCK)
	    {
	      p.playSound(hitblock.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.5F, 2.0F);
	      hitblock.setType(Material.AIR);
	    }
	    
	    	}
	  } 
	    }
		}
	
}

