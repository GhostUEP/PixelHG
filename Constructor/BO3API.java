package me.ghost.Constructor;

import org.bukkit.Material;

public class BO3API {
	private int x;
	private int y;
	private int z;
	private Material type;
	private byte data;

	public BO3API(int x, int y, int z, Material type, byte data) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
		this.data = data;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Material getType() {
		return type;
	}

	public byte getData() {
		return data;
	}
}
