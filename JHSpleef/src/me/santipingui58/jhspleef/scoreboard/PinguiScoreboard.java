package me.santipingui58.jhspleef.scoreboard;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.nametagedit.plugin.NametagEdit;

import me.santipingui58.jhspleef.Manager;
import me.santipingui58.jhspleef.game.GameState;
import me.santipingui58.jhspleef.game.SpleefArena;
import me.santipingui58.jhspleef.game.SpleefPlayer;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class PinguiScoreboard {
	
	private static PinguiScoreboard scoreboard;	
	
	 public static PinguiScoreboard getScoreboard() {
	        if (scoreboard == null)
	        	scoreboard = new PinguiScoreboard();
	        return scoreboard;
	    }
	
	public void scoreboard(SpleefPlayer sp) {
				String[] data = null;
				List<String> cache = new ArrayList<String>();
				DecimalFormat df = new DecimalFormat("0.00");
				String displayname = "�3�lJ�c�lH�d�lSpleef";
				if (sp.getScoreboard().equals(ScoreboardType.LOBBY)) {
					cache.add(displayname);
					cache.add("�f�f�f");
					cache.add("�fName: �6" + sp.getPlayer().getName());
					cache.add("�fRank: " + prefix(sp));
					cache.add("�fOnline players: �a" + Bukkit.getOnlinePlayers().size());
					cache.add("�fCoins: �6" + sp.getCoins());
					cache.add("�f");
					cache.add("�aFFA Wins: �e" +sp.getFFAWins());
					cache.add("�aFFA Games: �e" + sp.getFFAGames());
					cache.add("�a1vs1 Wins: �e" + sp.get1vs1Wins());
					cache.add("�a1vs1 Games: �e" + sp.get1vs1Games());
					cache.add("�f�f");
					cache.add("   �7mc.jhspleef.com");
					
				} else if (sp.getScoreboard().equals(ScoreboardType.FFAGAME)) {
					SpleefArena arena = Manager.getManager().getArenaByPlayer(sp);
					cache.add(displayname);
					cache.add("�f");
					cache.add("�2Players left: �a" + arena.getPlayers().size());
					cache.add("�f�f�f�f�f");
					if (arena.getState().equals(GameState.GAME)) {
					cache.add("�2Time left: �e" + time(arena.getTime()));
					cache.add("�f�f");
					} else if (arena.getState().equals(GameState.STARTING)) {
						cache.add("�2Game starting...");
						cache.add("�f�f");
					} 			
					cache.add("�2FFA Wins: �e" +sp.getFFAWins());
					cache.add("�2FFA Games: �e" + sp.getFFAGames());
					cache.add("�2FFA Kills: �e" + sp.getFFAKills());
					cache.add("�2M/W Wins: �e" +sp.getMonthlyFFAWins() +"/" + sp.getWeeklyFFAWins());
					cache.add("�2M/W Games: �e" + sp.getMonthlyFFAGames()+"/" + sp.getWeeklyFFAGames());
					cache.add("�2M/W Kills: �e" + sp.getMonthlyFFAKills()+"/"+sp.getWeeklyFFAKills());
					cache.add("�2W/G: �e" + df.format(sp.getWinGameRatio()));
					cache.add("�2K/G: �e" + df.format(sp.getKillGameRatio()));
					cache.add("�f�f�f");
					cache.add("   �7mc.jhspleef.com");
				}
				
				
				for(int i = 0; i < cache.size(); i++) {
					data = cache.toArray(new String[i]);
				}
				
				BoardAPI.ScoreboardUtil.unrankedSidebarDisplay(sp.getPlayer(), data);	
	}

	
	private String time(int s) {
		
			int minutes = s / 60;
			int seconds = s % 60;

			return String.format("%02d:%02d",  minutes, seconds);
		  }
	
	public void setTags() {
		
		for (SpleefPlayer sp : Manager.getManager().getPlayers()) {
		Player p = sp.getPlayer();
		if (sp.isAfk()) {	
			NametagEdit.getApi().setPrefix(p, "�7�oAFK ");
		} else {
			if (p.hasPermission("jhspleef.donator")) {
			NametagEdit.getApi().setPrefix(p, PermissionsEx.getUser(p).getPrefix());
			} else {
				NametagEdit.getApi().setPrefix(p, "�7");
			}
		}
	}
	}
	
	private String prefix(SpleefPlayer sp) {
		if (PermissionsEx.getUser(sp.getPlayer()).getPrefix().equalsIgnoreCase("")) {
			return "�3User";
		} else {
		return ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(sp.getPlayer()).getPrefix());
		}
	}

	
}
	

