package me.ghost.API;

import java.util.HashMap;

import me.ghost.Main;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EntityWither;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_7_R4.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BarAPI {
	public static HashMap<String, EntityWither> players = new HashMap<>();

	@SuppressWarnings("deprecation")
	public static void setMessage(String message) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			setMessage(p, message);
		}
	}

	@SuppressWarnings("deprecation")
	public static void setMessage(String message, int seconds) {
		for (final Player p : Bukkit.getOnlinePlayers()) {
			setMessage(p, message);
			new BukkitRunnable() {
				public void run() {
					removeDragon(p);
				}
			}.runTaskLater(Main.plugin, 20 * seconds);
		}
	}

	@SuppressWarnings("deprecation")
	public static void setLoadingMessage(String message, final int seconds, boolean upside) {
		for (final Player p : Bukkit.getOnlinePlayers()) {
			changeMessage(p, message);
			if (upside) {
				final EntityWither dragon = players.get(p.getName());
				changeLife(p, 0);
				new BukkitRunnable() {
					public void run() {
						float hppsec = 300 / seconds / 20;
						float dragonHealth = dragon.getHealth() + hppsec;
						if (dragonHealth >= 300) {
							cancel();
							removeDragon(p);
							return;
						}
						changeLife(p, dragonHealth);
					}
				}.runTaskTimer(Main.plugin, 0, 1);
				return;
			}
			final EntityWither dragon = players.get(p.getName());
			dragon.setCustomName(message);
			new BukkitRunnable() {
				public void run() {
					float hppsec = 300 / seconds / 20;
					float dragonHealth = dragon.getHealth() - hppsec;
					if (dragonHealth <= 0) {
						cancel();
						removeDragon(p);
						return;
					}
					changeLife(p, dragonHealth);
				}
			}.runTaskTimer(Main.plugin, 0, 1);
		}
	}

	public static void setMessage(Player p, String message) {
		changeMessage(p, message);
	}

	public static void setMessage(Player p, String message, float percent) {
		sendDragon(p, message, (300 * percent) / 100);
	}

	public static void setMessage(final Player p, String message, int seconds) {
		setMessage(p, message);
		new BukkitRunnable() {
			public void run() {
				removeDragon(p);
			}
		}.runTaskLater(Main.plugin, 20 * seconds);
	}

	public String getBarMessage(Player p) {
		return players.get(p.getName()).getCustomName();
	}

	public static void removeDragon(Player p) {
		if (!hasBar(p))
			return;
		EntityWither dragon = players.get(p.getName());
		PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(dragon.getId());
		EntityPlayer player = ((CraftPlayer) p).getHandle();
		player.playerConnection.sendPacket(destroy);
		players.remove(p.getName());
	}

	private static void changeMessage(Player p, String text) {
		if (!hasBar(p)) {
			sendDragon(p, text, 300);
			return;
		}
		EntityWither dragon = players.get(p.getName());
		dragon.setCustomName(text);
		PacketPlayOutEntityMetadata data = new PacketPlayOutEntityMetadata(dragon.getId(), dragon.getDataWatcher(), true);
		EntityPlayer player = ((CraftPlayer) p).getHandle();
		player.playerConnection.sendPacket(data);
	}

	private static void changeLife(Player p, float life) {
		if (!hasBar(p)) {
			sendDragon(p, "SEM MENSAGEM", life);
			return;
		}
		EntityWither dragon = players.get(p.getName());
		dragon.setHealth(life);
		PacketPlayOutEntityMetadata data = new PacketPlayOutEntityMetadata(dragon.getId(), dragon.getDataWatcher(), true);
		EntityPlayer player = ((CraftPlayer) p).getHandle();
		player.playerConnection.sendPacket(data);
	}

	public static boolean hasBar(Player p) {
		return players.containsKey(p.getName());
	}

	private static void sendDragon(final Player p, String text, float health) {
		WorldServer world = ((CraftWorld) p.getWorld()).getHandle();
		EntityWither dragon = new EntityWither(world);
		Location loc = getDragonLocation(p);
		dragon.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		dragon.setCustomName(text);
		dragon.setInvisible(true);
		dragon.setHealth(health);
		dragon.motX = 0;
		dragon.motY = 0;
		dragon.motZ = 0;
		PacketPlayOutSpawnEntityLiving spawn = new PacketPlayOutSpawnEntityLiving(dragon);
		EntityPlayer player = ((CraftPlayer) p).getHandle();
		player.playerConnection.sendPacket(spawn);
		players.put(p.getName(), dragon);
		new BukkitRunnable() {
			public void run() {
				if (!players.containsKey(p.getName())) {
					cancel();
					return;
				}
				Location loc = getDragonLocation(p);
				EntityWither dragon = players.get(p.getName());
				dragon.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
				PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(dragon);
				EntityPlayer player = ((CraftPlayer) p).getHandle();
				player.playerConnection.sendPacket(teleport);

			}
		}.runTaskTimer(Main.plugin, 2, 3);
	}

	public static Location getDragonLocation(Player p) {
		Location loc = p.getLocation();

		float pitch = loc.getPitch();

		if (pitch >= 55) {
			loc.add(0, -32, 0);
		} else if (pitch <= -55) {
			loc.add(0, 32, 0);
		} else {
			loc = loc.getBlock().getRelative(getDirection(loc), 16 * 2).getLocation();
		}

		return loc;
	}

	private static BlockFace getDirection(Location loc) {
		float dir = Math.round(loc.getYaw() / 90);
		if (dir == -4 || dir == 0 || dir == 4)
			return BlockFace.SOUTH;
		if (dir == -1 || dir == 3)
			return BlockFace.EAST;
		if (dir == -2 || dir == 2)
			return BlockFace.NORTH;
		if (dir == -3 || dir == 1)
			return BlockFace.WEST;
		return null;
	}
}
