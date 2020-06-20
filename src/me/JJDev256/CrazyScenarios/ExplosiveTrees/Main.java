package me.JJDev256.CrazyScenarios.ExplosiveTrees;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener, CommandExecutor {
	private boolean isActive = false;
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("settreesexplosive").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("explosivetrees.commands.toggle.use")) {
			sender.sendMessage("§4You cannot use that command.");
			return true;
		}
		if (args.length != 1 || !args[0].matches("^true$|^false$"))
			return false;
		isActive = args[0].equalsIgnoreCase("true");
		return true;
	}
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent event) {
		if (!isActive) return;
		World world = event.getPlayer().getWorld();
		Block block = event.getBlock();
		if (block.getType() == Material.ACACIA_LOG || 
				block.getType() == Material.BIRCH_LOG || 
				block.getType() == Material.DARK_OAK_LOG || 
				block.getType() == Material.JUNGLE_LOG || 
				block.getType() == Material.OAK_LOG || 
				block.getType() == Material.SPRUCE_LOG || 
				block.getType() == Material.COAL_ORE || 
				block.getType() == Material.IRON_ORE || 
				block.getType() == Material.GOLD_ORE || 
				block.getType() == Material.REDSTONE_ORE || 
				block.getType() == Material.DIAMOND_ORE || 
				block.getType() == Material.EMERALD_ORE || 
				block.getType() == Material.NETHER_QUARTZ_ORE) {
			block.breakNaturally();
			world.createExplosion(block.getLocation().add(0.5f, 0.5f, 0.5f),4f);
		}
	}
}
