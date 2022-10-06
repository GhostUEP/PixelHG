package me.ghost.Constructor;

import net.minecraft.server.v1_7_R4.EntityFishingHook;
import net.minecraft.server.v1_7_R4.EntityHuman;
import net.minecraft.server.v1_7_R4.EntitySnowball;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftSnowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

public class Gancho extends EntityFishingHook {
	private Snowball sb;
	private EntitySnowball controller;
	public int a;
	public EntityHuman owner;
	public Entity hooked;
	public boolean lastControllerDead;
	public boolean isHooked;

	public Gancho(org.bukkit.World world, EntityHuman entityhuman) {
		super(((CraftWorld) world).getHandle(), entityhuman);
		owner = entityhuman;
	}

	protected void c() {
	}

	public void h() {
		if ((!lastControllerDead) && (controller.dead)) {
			((Player) owner.getBukkitEntity()).sendMessage(ChatColor.GREEN + "Seu gancho prendeu em algo!");
		}
		lastControllerDead = controller.dead;
		for (Entity entity : controller.world.getWorld().getEntities()) {
			if ((!(entity instanceof Firework)) && (entity.getEntityId() != getBukkitEntity().getEntityId()) && (entity.getEntityId() != owner.getBukkitEntity().getEntityId()) && (entity.getEntityId() != controller.getBukkitEntity().getEntityId()) && ((entity.getLocation().distance(controller.getBukkitEntity().getLocation()) < 2.0D) || (((entity instanceof Player)) && (((Player) entity).getEyeLocation().distance(controller.getBukkitEntity().getLocation()) < 2.0D)))) {
				controller.die();
				hooked = entity;
				isHooked = true;
				locX = entity.getLocation().getX();
				locY = entity.getLocation().getY();
				locZ = entity.getLocation().getZ();
				motX = 0.0D;
				motY = 0.04D;
				motZ = 0.0D;
			}
		}
		try {
			locX = hooked.getLocation().getX();
			locY = hooked.getLocation().getY();
			locZ = hooked.getLocation().getZ();
			motX = 0.0D;
			motY = 0.04D;
			motZ = 0.0D;
			isHooked = true;
		} catch (Exception e) {
			if (controller.dead) {
				isHooked = true;
			}
			locX = controller.locX;
			locY = controller.locY;
			locZ = controller.locZ;
		}
	}

	public void die() {
	}

	public void remove() {
		super.die();
	}

	@SuppressWarnings("deprecation")
	public void spawn(Location location, Vector v) {
		sb = ((Snowball) owner.getBukkitEntity().launchProjectile(Snowball.class));
		sb.setVelocity(v);
		controller = ((CraftSnowball) sb).getHandle();
		PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { controller.getId() });
		for (Player p : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
		((CraftWorld) location.getWorld()).getHandle().addEntity(this);
	}

	public boolean isHooked() {
		return isHooked;
	}

	public void setHookedEntity(Entity damaged) {
		hooked = damaged;
	}
}
