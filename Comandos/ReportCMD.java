package me.ghost.Comandos;

import me.ghost.Main;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("report")) {
			if (!p.hasPermission("PixelHG.report")) {
				return true;
			}

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Testando");
			out.writeUTF("caht e");

			p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());

			// if (args.length < 3) {
			// p.sendMessage(ChatColor.RED + "Use /report [Player] [Report]");
			// return true;
			// }

			// String msg = "";
			// for (int i = 1; i < args.length; i++)
			// msg += args[i] + " ";
			// Main.plugin.sendAllSevers(msg, p);
			// return true;
		}

		return false;
	}
}
