package me.santipingui58.jhspleef.commands;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.santipingui58.jhspleef.Main;
import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.SpleefType;
import me.santipingui58.jhspleef.utils.Messages;
import me.santipingui58.jhspleef.utils.Utils;


public class SetupCommand implements CommandExecutor {
	
	

		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			
			
			if(cmd.getName().equalsIgnoreCase("setup")) {
			if(!(sender instanceof Player)) {
				
				sender.sendMessage(Messages.getMessage("console-not-allowed",null,null,null,null));
				return true;
				
			} else {
				Player p = (Player) sender;
				if (p.hasPermission("jhspleef.admin")) {
			if (args.length==0) {
				
			} else if (args[0].equalsIgnoreCase("setmainlobby"))  {
				if (args.length==1) {
					Main.arenas.getConfig().set("mainlobby", Utils.setLoc(p.getLocation(), true));
					Main.arenas.saveConfig();
				}
			} else if (args[0].equalsIgnoreCase("setarena1")) {
				if (args.length==2) {
					p.sendMessage("§aYou have set the the location of Arena #1 on arena §b"+ args[1]);
					Main.arenas.getConfig().set("arenas."+args[1]+".arena1", Utils.setLoc(p.getLocation(), false));
					Main.arenas.saveConfig();
				} else {
					
				}
			} else if (args[0].equalsIgnoreCase("setarena2")) {
				if (args.length==2) {
					p.sendMessage("§aYou have set the the location of Arena #2 on arena §b"+ args[1]);
					Main.arenas.getConfig().set("arenas."+args[1]+".arena2", Utils.setLoc(p.getLocation(), false));
					Main.arenas.saveConfig();
				} else {
					
				}
			}else if (args[0].equalsIgnoreCase("setlobby")) {
				if (args.length==2) {
					p.sendMessage("§aYou have set the the location of Lobby on arena §b"+ args[1]);
					Main.arenas.getConfig().set("arenas."+args[1]+".lobby", Utils.setLoc(p.getLocation(), false));
					Main.arenas.saveConfig();
				} else {
					
				}
			}else if (args[0].equalsIgnoreCase("setmainspawn")) {
				if (args.length==2) {
					p.sendMessage("§aYou have set the the location of Main Spawn on arena §b"+ args[1]);
					Main.arenas.getConfig().set("arenas."+args[1]+".mainspawn", Utils.setLoc(p.getLocation(), false));
					Main.arenas.saveConfig();
				} else {
					
				}
			}else if (args[0].equalsIgnoreCase("settype")) {
				if (args.length==3) {
					try {
						SpleefType.valueOf(args[2]);
					p.sendMessage("§aYou have set the the location of Type on arena §b"+ args[1]);
					Main.arenas.getConfig().set("arenas."+args[1]+".type", args[2]);
					Main.arenas.saveConfig();
				} catch(Exception e) {
					p.sendMessage("§cThe Spleef Type §b" + args[2] + " §cdoes not exist.");
				} 
					
			} else {
				}
			} else if (args[0].equalsIgnoreCase("create")) {
				if (args.length == 2) {
					if (Main.arenas.getConfig().contains("arenas."+args[1]+".arena1") && 
							Main.arenas.getConfig().contains("arenas."+args[1]+".arena2") &&
							Main.arenas.getConfig().contains("arenas."+args[1]+".lobby") &&
							Main.arenas.getConfig().contains("arenas."+args[1]+".mainspawn") &&
							Main.arenas.getConfig().contains("arenas."+args[1]+".type")) {
						
						Location mainspawn = Utils.getLoc(Main.arenas.getConfig().getString("arenas."+args[1]+".mainspawn"));
						Location arena1 = Utils.getLoc(Main.arenas.getConfig().getString("arenas."+args[1]+".mainspawn"));
						Location arena2 = Utils.getLoc(Main.arenas.getConfig().getString("arenas."+args[1]+".mainspawn"));
						Location lobby = Utils.getLoc(Main.arenas.getConfig().getString("arenas."+args[1]+".mainspawn"));
						SpleefType type = SpleefType.valueOf(Main.arenas.getConfig().getString("arenas."+args[1]+".type"));
						Manager.getManager().loadArena(args[1], mainspawn, lobby, arena1, arena2, type);
						p.sendMessage("§aThe arena §b" + args[1] + " §ahas been created succesfully!.");
					} else {
						p.sendMessage("§cThe arena §b" + args[1] + " §cdoesnt have all locations set.");
					}
				} else {
					
				}
			}
				
		}
			}
		
}
			return false;
			
		}
}