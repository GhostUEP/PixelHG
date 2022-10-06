package me.ghost.Comandos;

import me.ghost.Main;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConnectCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("connect")) {
			Player p = (Player) sender;
			if (!p.hasPermission("PixelHG.pex.Mod")) {
				p.sendMessage("Você não tem permissão para esse comando");
				return true;
			}
			Player player = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /connect [Server]");
			} else if (args.length == 1) {
				String server = args[0];
				if (server.equalsIgnoreCase("a1")) {
					server = "a1.hg.mc-pixel.com";
				}
				if (server.equalsIgnoreCase("a2")) {
					server = "a2.hg.mc-pixel.com";
				}
				if (server.equalsIgnoreCase("a3")) {
					server = "a3.hg.mc-pixel.com";
				}
				if (server.equalsIgnoreCase("a4")) {
					server = "a4.hg.mc-pixel.com";
				}
				if (server.equalsIgnoreCase("a5")) {
					server = "a5.hg.mc-pixel.com";
				}
				if (server.equalsIgnoreCase("a6")) {
					server = "a6.hg.mc-pixel.com";
				}
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF(server);

				player.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
			}
		}
		return false;
	}
}
