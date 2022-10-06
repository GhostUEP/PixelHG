package me.ghost.Manager;

import java.lang.reflect.Field;

import me.ghost.Main;
import me.ghost.API.TagAPI;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_7_R4.PacketPlayOutPlayerInfo;
import net.minecraft.util.com.mojang.authlib.GameProfile;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FakeManager {

	@SuppressWarnings("deprecation")
	public static void setFake(final Player p, final String nick) {
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {

			@Override
			public void run() {
				if (!Main.plugin.fakeoriginal.containsKey(p.getUniqueId())) {
					Main.plugin.fakeoriginal.put(p.getUniqueId(), p.getName());
				}
				EntityPlayer gay = ((CraftPlayer) p).getHandle();
				PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(gay.getId());
				for (Player todos : Bukkit.getOnlinePlayers()) {
					if (todos != p) {
						((CraftPlayer) todos).getHandle().playerConnection.sendPacket(destroy);
					}
				}
				GameProfile pf = gay.getProfile();
				pf.getProperties().clear();
				try {
					Field f = pf.getClass().getDeclaredField("name");
					f.setAccessible(true);
					f.set(pf, nick);
				} catch (Exception e) {
					e.printStackTrace();
				}
				PacketPlayOutPlayerInfo inforemove = PacketPlayOutPlayerInfo.removePlayer(gay);
				PacketPlayOutPlayerInfo infoadd = PacketPlayOutPlayerInfo.addPlayer(gay);
				PacketPlayOutNamedEntitySpawn npc = new PacketPlayOutNamedEntitySpawn(gay);

				for (Player todos : Bukkit.getOnlinePlayers()) {
					((CraftPlayer) todos).getHandle().playerConnection.sendPacket(inforemove);
					if (todos != p) {
						((CraftPlayer) todos).getHandle().playerConnection.sendPacket(npc);

					}
					((CraftPlayer) todos).getHandle().playerConnection.sendPacket(infoadd);
				}
				p.setDisplayName(ChatColor.GRAY + nick);
				p.setPlayerListName(ChatColor.GRAY + nick);
				p.sendMessage(ChatColor.AQUA + "Seu Nick é: " + nick);
				p.sendMessage(ChatColor.AQUA + "Para tirar o Fake, Use /tfake");
				Main.plugin.fake.put(p.getUniqueId(), nick);
				Main.plugin.espere.remove(p.getUniqueId());

			}
		}, 20L);
	}

	@SuppressWarnings("deprecation")
	public static void removeFake(Player p) {
		p.sendMessage(ChatColor.AQUA + "Seu nick voltou ao normal");
		p.sendMessage(ChatColor.AQUA + "Escolha outro Fake, use /fake [Nome]");
		Main.plugin.fake.remove(p.getUniqueId());
		EntityPlayer gay = ((CraftPlayer) p).getHandle();
		PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(gay.getId());
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (todos != p) {
				((CraftPlayer) todos).getHandle().playerConnection.sendPacket(destroy);
			}
		}
		GameProfile pf = gay.getProfile();
		pf.getProperties().clear();
		try {
			Field f = pf.getClass().getDeclaredField("name");
			f.setAccessible(true);
			f.set(pf, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Main.plugin.fakeoriginal.remove(p.getUniqueId());
		PacketPlayOutNamedEntitySpawn npc = new PacketPlayOutNamedEntitySpawn(gay);
		PacketPlayOutPlayerInfo inforemove = PacketPlayOutPlayerInfo.removePlayer(gay);
		PacketPlayOutPlayerInfo infoadd = PacketPlayOutPlayerInfo.addPlayer(gay);

		for (Player todos : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) todos).getHandle().playerConnection.sendPacket(inforemove);
			if (todos != p) {
				((CraftPlayer) todos).getHandle().playerConnection.sendPacket(npc);

			}
			((CraftPlayer) todos).getHandle().playerConnection.sendPacket(infoadd);
		}
		if (p.hasPermission("PixelHG.tag.Dono") && p.hasPermission("PixelHG.tag.Pro") && p.hasPermission("PixelHG.tag.Youtuber") && p.hasPermission("PixelHG.tag.Admin") && p.hasPermission("PixelHG.tag.Mod") && p.hasPermission("PixelHG.tag.Mvp") && p.hasPermission("PixelHG.tag.Vip")) {
			p.setPlayerListName(ChatColor.DARK_RED + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.DARK_RED + p.getName() + ChatColor.RESET);
		} else if (p.hasPermission("PixelHG.tag.Pro")) {
			p.setPlayerListName(ChatColor.GOLD + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.GOLD + p.getName() + ChatColor.RESET);
		} else if (p.hasPermission("PixelHG.tag.Youtuber")) {
			p.setPlayerListName(ChatColor.AQUA + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.AQUA + p.getName() + ChatColor.RESET);
		} else if (p.hasPermission("PixelHG.tag.Admin")) {
			p.setPlayerListName(ChatColor.RED + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.RED + p.getName() + ChatColor.RESET);
		} else if (p.hasPermission("PixelHG.tag.Dono")) {
			p.setPlayerListName(ChatColor.DARK_RED + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.DARK_RED + p.getName() + ChatColor.RESET);
		} else if (p.hasPermission("PixelHG.tag.Mod")) {
			p.setPlayerListName(ChatColor.GRAY + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.GRAY + p.getName() + ChatColor.RESET);
		} else if (p.hasPermission("PixelHG.tag.Mvp")) {
			p.setPlayerListName(ChatColor.BLUE + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.BLUE + p.getName() + ChatColor.RESET);
		} else if (p.hasPermission("PixelHG.tag.Vip")) {
			p.setPlayerListName(ChatColor.GREEN + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.GREEN + p.getName() + ChatColor.RESET);
		} else if (!p.hasPermission("PixelHG.tag.Dono") && !p.hasPermission("PixelHG.tag.Vip") && !p.hasPermission("PixelHG.tag.Mvp") && !p.hasPermission("PixelHG.tag.Mod") && !p.hasPermission("PixelHG.tag.Admin") && !p.hasPermission("PixelHG.tag.Youtuber") && !p.hasPermission("PixelHG.tag.Pro")) {
			p.setPlayerListName(ChatColor.GRAY + TagAPI.getShortStr(p.getName()));
			p.setDisplayName(ChatColor.GRAY + p.getName() + ChatColor.RESET);

		}
	}
}
