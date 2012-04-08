package me.Hacr.HW;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HW extends JavaPlugin implements Listener {
	
	public static HW instance;

	//Lager liste med sting, da slipper jeg Œ hente blokk navn i command
	HashMap<Player, List<String>> blocks = new HashMap <Player, List<String>>();
	
	public static final Logger log = Logger.getLogger("Minecraft");

	
	public void onEnable() {
		log.log(Level.INFO, "[HW] Plugin lastet!");
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	public void onDisable() {
		log.log(Level.INFO, "[HW] Plugin deaktivert!");

		
	}
	
	//events - Legger det i main class, er ikke n¿dvendig med egen listener.

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		String block = b.getType().toString();
		List<String> block01 = null;
		
		//skikkelig pirat-fiksing ;=)
		if (blocks.get(p) != null){
			block01 = blocks.get(p);
		} else{
		block01 = new ArrayList<String>();
		}

		block01.add(0, block);
		blocks.put(p, block01);
		
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		 
		
		if(cmd.getName().equalsIgnoreCase("blocks")){ 
			
			if (blocks.get(player) != null){
				player.sendMessage(ChatColor.GREEN + "Du har satt ut f¿lgende blokker:");
				player.sendMessage("Blokk 1: " + blocks.get(player).get(0) );
				if (blocks.get(player).size() > 1) player.sendMessage("Blokk 2: " + blocks.get(player).get(1) );
				if (blocks.get(player).size() > 2) player.sendMessage("Blokk 3: " + blocks.get(player).get(2) );
				if (blocks.get(player).size() > 3) player.sendMessage("Blokk 4: " + blocks.get(player).get(3) );
				if (blocks.get(player).size() > 4) player.sendMessage("Blokk 5: " + blocks.get(player).get(4) );
				player.sendMessage(ChatColor.GREEN + "Du har satt ut totalt: " + blocks.get(player).size());
				
			} else player.sendMessage(ChatColor.RED + "Du har ikke plassert noen blokker.");
			return true;
		} 
		return false; 
	}
	

}
