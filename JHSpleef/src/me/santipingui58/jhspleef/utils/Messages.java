package me.santipingui58.jhspleef.utils;


import java.util.ArrayList;
import java.util.List;

import me.santipingui58.jhspleef.Main;
import net.md_5.bungee.api.ChatColor;



/*
args1 %player% %command%
args2 %number%
args3 %bountied%
args4 %total%

*/
public class Messages {

	public static String getMessage(String path, String arg1,String arg2, String arg3, String arg4) {
		String string = "";
		if (Main.messages.getConfig().contains(path)) {
			string = ChatColor.translateAlternateColorCodes('&',Main.messages.getConfig().getString(path));
		} 
		
		if (arg1!=null) {
			string = string.replace("%player%", arg1);
			string =	string.replace("%command%", arg1);
		}
		
		if (arg2!=null) {
			string =string.replace("%number%", arg2);
		}
		if (arg3!=null) {
			string =string.replace("%bountied%", arg3);
		}
		if (arg4!=null) {
			string =string.replace("%total%", arg4);
		}
		
		
		if (Main.prefix_enabled) {
			string  =string.replace("%prefix%", Main.prefix);
		} else {
			string  =string.replace("%prefix%", "");
		}
		return string;
	}
	
	
	public static List<String> getMessageList(String path) {
		List<String> list = new ArrayList<String>();
		
		for (String s : Main.messages.getConfig().getStringList(path)) {
			String str = ChatColor.translateAlternateColorCodes('&',s);
			list.add(str);
		}
		
		return list;
	}
	
}
