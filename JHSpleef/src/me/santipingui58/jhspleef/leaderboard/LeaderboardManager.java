package me.santipingui58.jhspleef.leaderboard;

import java.util.ArrayList;
import java.util.List;

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
	 
	 public void 
}
