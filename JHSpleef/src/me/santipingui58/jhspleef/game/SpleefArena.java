package me.santipingui58.jhspleef.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class SpleefArena {

	private SpleefType type;
	private Location mainspawn;
	private Location lobby;
	private Location arena1;
	private Location arena2;
	private String name;
	
	private int time;
	private GameState state;
	private List<SpleefPlayer> players = new ArrayList<SpleefPlayer>();
	private List<SpleefPlayer> queue = new ArrayList<SpleefPlayer>();
	public SpleefArena(String name,Location mainspawn,Location lobby,Location arena1, Location arena2,SpleefType type) {
		this.name = name;
		this.mainspawn = mainspawn;
		this.lobby = lobby;
		this.arena1 = arena1;
		this.arena2 = arena2;
		this.type = type;
		this.state = GameState.LOBBY;
		this.time = 150;
		
	}
	
	public void time() {
		this.time = this.time - 1;
	}
	public List<SpleefPlayer> getQueue() {
		return this.queue;
	}
	public void resetTimer() {
		this.time = 150;
	}
	public List<SpleefPlayer> getViewers() {
		List<SpleefPlayer> viewers = new ArrayList<SpleefPlayer>();
		for (SpleefPlayer s : this.players) {
			viewers.add(s);
		}
		for (SpleefPlayer s : this.queue) {
			if (!viewers.contains(s)) {
			viewers.add(s);
			}
		}
		return viewers;
	}
	
	public List<SpleefPlayer> getPlayers() {
		return this.players;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public void setTime(int i) {
		this.time = i;
	}
	
	public GameState getState() {
		return this.state;
	}
	
	public void setState(GameState gs) {
		this.state = gs;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Location getMainSpawn() {
		return this.mainspawn;
	}
	
	public Location getLobby() {
		return this.lobby;
	}
	public Location getArena1() {
		return this.arena1;
	}
	
	public Location getArena2() {
		return this.arena2;
	}
	
	public SpleefType getType() {
		return this.type;
	}
}
