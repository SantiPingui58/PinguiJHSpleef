package me.santipingui58.jhspleef.game;

import org.bukkit.entity.Player;

import me.santipingui58.jhspleef.Rank;
import me.santipingui58.jhspleef.scoreboard.ScoreboardType;

public class SpleefPlayer {

	private Player player;
	private Rank rank;
	private int FFA_wins;
	private int FFA_games;
	private int ELO;
	private int _1vs1_wins;
	private int _1vs1_games;
	private int totalgames;
	private int coins;
	private ScoreboardType scoreboard;
	private boolean isafk;
	private boolean fly;
	private String ip;
	public SpleefPlayer(Player player) {
		this.player = player;
		this.scoreboard = ScoreboardType.LOBBY;
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
	
	public int getFFAWins() {
		return this.FFA_wins;
	}
	
	public void setFFAWins(int i) {
		this.FFA_wins = i;
	}
	
	public int getFFAGames() {
		return this.FFA_games;
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
		return this.totalgames;
	}
	
	public void setTotalGames(int i) {
		this.totalgames = i;
	}
	
	public int getCoins() {
		return this.coins;
	}
	
	public void setCoins(int i) {
		this.coins = i;
	}
}
