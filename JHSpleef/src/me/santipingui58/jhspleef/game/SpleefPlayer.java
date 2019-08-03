package me.santipingui58.jhspleef.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.santipingui58.jhspleef.Rank;
import me.santipingui58.jhspleef.scoreboard.ScoreboardType;
import me.santipingui58.jhspleef.utils.GetCountry;

public class SpleefPlayer {

	private Player player;
	private Rank rank;
	private int FFA_wins;
	private int FFA_games;
	private int FFA_kills;
	private int ELO;
	private int _1vs1_wins;
	private int _1vs1_games;	
	
	private int monthly_FFA_wins;
	private int monthly_FFA_games;
	private int monthly_FFA_kills;

	
	private int weekly_FFA_wins;
	private int weekly_FFA_games;
	private int weekly_FFA_kills;

	
	private int coins;
	private ScoreboardType scoreboard;
	private boolean isafk;
	private boolean fly;
	private String ip;
	private String country;
	private int onlinetime;
	private String nick;
	private Location location;
	
	public SpleefPlayer(Player player) {
		this.player = player;
		this.scoreboard = ScoreboardType.LOBBY;
	}
	
	public String getCountry() {
		if (this.country==null) {
			new GetCountry(this);
		}
		
		return this.country;
	}
	
	public void setCountry(String s) {
		this.country = s;
	}
	public int getMonthlyFFAWins() {
		return this.monthly_FFA_wins;
	}
	public void setMonthlyFFAWins(int i) {
		this.monthly_FFA_wins = i;
	}
	
	public int getMonthlyFFAGames() {
		return this.monthly_FFA_games;
	}
	public void setMonthlyFFAGames(int i) {
		this.monthly_FFA_games = i;
	}
	
	public int getMonthlyFFAKills() {
		return this.monthly_FFA_kills;
	}
	public void setMonthlyFFAKills(int i) {
		this.monthly_FFA_kills = i;
	}
	
	
	
	
	public int getWeeklyFFAWins() {
		return this.weekly_FFA_wins;
	}
	public void setWeeklyFFAWins(int i) {
		this.weekly_FFA_wins = i;
	}
	
	public int getWeeklyFFAGames() {
		return this.weekly_FFA_games;
	}
	public void setWeeklyFFAGames(int i) {
		this.weekly_FFA_games = i;
	}
	
	public int getWeeklyFFAKills() {
		return this.weekly_FFA_kills;
	}
	public void setWeeklyFFAKills(int i) {
		this.weekly_FFA_kills = i;
	}
	public int getOnlineTime() {
		return this.onlinetime;
	}
	
	public void addOnlineTime() {
		this.onlinetime = this.onlinetime + 1;
	}
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getNick() {
		return this.nick;
	}
	
	public boolean hasNick() {
		if (this.nick!=null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void deleteNick() {
		this.nick = null;
	}
	public boolean isAfk() {
		return this.isafk;
	}
	
	public String getIP() {
		return this.ip;
	}
	
	public void setIP(String ip) {
		this.ip = ip;
	}
	public void fly() {
		this.fly = true;
		this.player.setAllowFlight(true);
		this.player.setFlying(true);
	}
	public void stopfly() {
		this.fly = false;
		this.player.setAllowFlight(false);
		this.player.setFlying(false);
		
	}
	

	public boolean isFlying() {
		return this.fly;
	}
	public void afk() {
		this.isafk = true;
	}
	
	public void back() {
		this.isafk = false;
	}
	public ScoreboardType getScoreboard() {
		return this.scoreboard;
	}
	
	public void setScoreboard(ScoreboardType type) {
		this.scoreboard = type;
	}
	public Player getPlayer() {
		return this.player;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	
	public double getWinGameRatio() {
		if (this.FFA_games==0) {
			return (double) this.FFA_wins;
		} else {
			return (double) this.FFA_wins/this.FFA_games;
		}
	}
	
	public double getKillGameRatio() {
		if (this.FFA_games==0) {
			return (double) this.FFA_kills;
		} else {
			return (double) this.FFA_kills/this.FFA_games;
		}
	}
	public int getFFAWins() {
		return this.FFA_wins;
	}
	
	public void setFFAWins(int i) {
		this.FFA_wins = i;
	}
	
	public int getFFAGames() {
		return this.FFA_games;
	}
	
	public int getFFAKills() {
		return this.FFA_kills;
	}
	public void setFFAKills(int i) {
		this.FFA_kills = i;
	}
	public void setFFAGames(int i) {
		this.FFA_games = i;
	}
	
	public int getELO() {
		return this.ELO;
	}
	
	public void setELO(int i) {
		this.ELO = i;
	}
	
	public int get1vs1Games() {
		return this._1vs1_games;
	}
	
	public void set1vs1Games(int i) {
		this._1vs1_games = i;
	}
	
	public int get1vs1Wins() {
		return this._1vs1_wins;
	}
	
	public void set1vs1Wins(int i) {
		this._1vs1_wins = i;
	}
	
	public int getTotalGames() {
	return	this._1vs1_games + this.FFA_games;
	}
	

	
	public int getCoins() {
		return this.coins;
	}
	
	public void setCoins(int i) {
		this.coins = i;
	}
}
