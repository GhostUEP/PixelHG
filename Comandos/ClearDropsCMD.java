package me.ghost.Comandos;

import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class ClearDropsCMD implements CommandExecutor {
	Integer items = 0;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("cleardrops")) {
			if (!p.hasPermission("PixelHG.pex.Mod")) {
				p.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
				return true;
			}
			List<Entity> list = p.getWorld().getEntities();
			Iterator<Entity> entities = list.iterator();
			items = 0;
			while (entities.hasNext()) {
				Entity entity = entities.next();
				if (entity instanceof Item) {
					entity.remove();
					items++;
				}
			}
			p.sendMessage(ChatColor.GREEN + "" + items + " Items removidos com sucesso!");
			return true;
		}
		return false;
	}
}
