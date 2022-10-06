package me.ghost.Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Eventos.PlayerSelectKitEvent;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitManager {
	public HashMap<UUID, String> KITS = new HashMap<>();
	public ArrayList<String> kits = new ArrayList<>();
	public HashMap<UUID, ArrayList<String>> kitspagina2player = new HashMap<>();
	public HashMap<UUID, ArrayList<String>> kitspagina2playerloja = new HashMap<>();
	public HashMap<UUID, ArrayList<String>> kitspagina3playerloja = new HashMap<>();
	public HashMap<UUID, ArrayList<String>> kitspagina3player = new HashMap<>();
	public HashMap<UUID, ArrayList<String>> kitspagina4player = new HashMap<>();
	public HashMap<ItemStack, String> selector = new HashMap<>();
	public HashMap<ItemStack, String> selectorloja = new HashMap<>();
	public HashMap<ItemStack, Integer> selectorloja2 = new HashMap<>();
	public HashMap<String, Kit> items = new HashMap<>();
	public HashMap<UUID, ArrayList<String>> playerKit = new HashMap<>();
	public HashMap<UUID, Integer> seuskits = new HashMap<>();
	public HashMap<String, ArrayList<String>> freeKits = new HashMap<>();
	public ArrayList<UUID> surprises = new ArrayList<>();
	public static ArrayList<UUID> segundapagina = new ArrayList<>();
	public boolean kitsHabilitados = true;
	public Main m;

	public void InventoryKitteste(Player p) {
		if (Main.plugin.openloja1.contains(p.getUniqueId())) {
			Main.plugin.openloja1.remove(p.getUniqueId());
		}
		if (Main.plugin.openloja2.contains(p.getUniqueId())) {
			Main.plugin.openloja2.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele1.contains(p.getUniqueId())) {
			Main.plugin.opensele1.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele2.contains(p.getUniqueId())) {
			Main.plugin.opensele2.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele3.contains(p.getUniqueId())) {
			Main.plugin.opensele3.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele4.contains(p.getUniqueId())) {
			Main.plugin.opensele4.remove(p.getUniqueId());
		}
		Main.plugin.opensele1.add(p.getUniqueId());
		Main.plugin.kitselector.get(p.getUniqueId()).clear();
		ItemStack vidro = new ItemStack(Material.THIN_GLASS);
		ItemMeta metav = vidro.getItemMeta();
		metav.setDisplayName("§c");
		vidro.setItemMeta(metav);

		ItemStack cabeca = new ItemStack(Material.ENCHANTMENT_TABLE);
		ItemMeta cabecaim = cabeca.getItemMeta();
		cabecaim.setDisplayName(ChatColor.GOLD + "Seletor de Kits!");
		cabeca.setItemMeta(cabecaim);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(4, cabeca);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(2, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(3, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(5, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(6, vidro);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(1, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(7, vidro);

		ItemStack la = new ItemStack(Material.INK_SACK, (short) 1, (short) 10);
		ItemMeta laim = la.getItemMeta();
		laim.setDisplayName(ChatColor.GRAY + "Proxima pagina");
		la.setItemMeta(laim);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(0, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(8, la);

		ArrayList<String> kitspagina2 = new ArrayList<>();
		HashMap<ItemStack, Integer> quantidade = new HashMap<>();
		ArrayList<String> lista = kits;
		Collections.sort(lista, String.CASE_INSENSITIVE_ORDER);
		int numeros = 0;
		for (String name : lista) {
			if (name.equalsIgnoreCase("semkit"))
				continue;
			if (!p.hasPermission("PixelHG.kit." + name))
				continue;
			if (Main.plugin.kitsonoff.contains(name))
				continue;
			numeros++;
			char[] stringArray = name.toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			name = new String(stringArray);
			ItemStack item = new ItemStack(items.get(name.toLowerCase()).icon);
			ItemMeta itemim = item.getItemMeta();
			itemim.setDisplayName(ChatColor.AQUA + "Kit: " + ChatColor.GRAY + name);
			itemim.setLore(items.get(name.toLowerCase()).kitInfo);
			item.setItemMeta(itemim);
			quantidade.put(item, numeros);
			if (quantidade.get(item).intValue() <= 45) {
				if (Main.plugin.pagina2.contains(p.getUniqueId())) {
					Main.plugin.pagina2.remove(p.getUniqueId());
				}
				kitspagina2player.remove(p.getUniqueId());
				kitspagina2.clear();
				selector.put(item, name.toLowerCase());
				Main.plugin.kitselector.get(p.getUniqueId()).addItem(item);
			} else {
				if (!Main.plugin.pagina2.contains(p.getUniqueId())) {
					Main.plugin.pagina2.add(p.getUniqueId());
				}
				kitspagina2.remove(name.toLowerCase());
				kitspagina2.add(name.toLowerCase());
				kitspagina2player.put(p.getUniqueId(), kitspagina2);
			}

		}
		ItemStack[] arrayOfItemStack;
		int vidros = (arrayOfItemStack = Main.plugin.kitselector.get(p.getUniqueId()).getContents()).length;
		for (int metavidros = 0; metavidros < vidros; metavidros++) {
			ItemStack item2 = arrayOfItemStack[metavidros];
			if (item2 == null) {
				Main.plugin.kitselector.get(p.getUniqueId()).setItem(Main.plugin.kitselector.get(p.getUniqueId()).firstEmpty(), vidro);
			}
		}
	}

	public void InventoryKitteste2(Player p) {
		if (Main.plugin.openloja1.contains(p.getUniqueId())) {
			Main.plugin.openloja1.remove(p.getUniqueId());
		}
		if (Main.plugin.openloja2.contains(p.getUniqueId())) {
			Main.plugin.openloja2.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele1.contains(p.getUniqueId())) {
			Main.plugin.opensele1.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele2.contains(p.getUniqueId())) {
			Main.plugin.opensele2.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele3.contains(p.getUniqueId())) {
			Main.plugin.opensele3.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele4.contains(p.getUniqueId())) {
			Main.plugin.opensele4.remove(p.getUniqueId());
		}
		Main.plugin.opensele2.add(p.getUniqueId());
		Main.plugin.kitselector.get(p.getUniqueId()).clear();
		ItemStack vidro = new ItemStack(Material.THIN_GLASS);
		ItemMeta metav = vidro.getItemMeta();
		metav.setDisplayName("§c");
		vidro.setItemMeta(metav);

		ItemStack cabeca = new ItemStack(Material.ENCHANTMENT_TABLE);
		ItemMeta cabecaim = cabeca.getItemMeta();
		cabecaim.setDisplayName(ChatColor.GOLD + "Seletor de Kits!");
		cabeca.setItemMeta(cabecaim);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(4, cabeca);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(2, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(3, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(5, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(6, vidro);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(1, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(7, vidro);

		ItemStack la2 = new ItemStack(Material.INK_SACK, (short) 1, (short) 10);
		ItemMeta laim2 = la2.getItemMeta();
		laim2.setDisplayName(ChatColor.GRAY + "Voltar");
		la2.setItemMeta(laim2);

		ItemStack la = new ItemStack(Material.INK_SACK, (short) 1, (short) 10);
		ItemMeta laim = la.getItemMeta();
		laim.setDisplayName(ChatColor.GRAY + "Proxima pagina");
		la.setItemMeta(laim);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(0, la2);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(8, la);
		ArrayList<String> kitspagina3 = new ArrayList<>();
		HashMap<ItemStack, Integer> quantidade = new HashMap<>();
		ArrayList<String> lista = kitspagina2player.get(p.getUniqueId());
		Collections.sort(lista, String.CASE_INSENSITIVE_ORDER);
		int numeros = 0;
		for (String name : lista) {
			numeros++;
			char[] stringArray = name.toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			name = new String(stringArray);
			ItemStack item = new ItemStack(items.get(name.toLowerCase()).icon);
			ItemMeta itemim = item.getItemMeta();
			itemim.setDisplayName(ChatColor.AQUA + "Kit: " + ChatColor.GRAY + name);
			itemim.setLore(items.get(name.toLowerCase()).kitInfo);
			item.setItemMeta(itemim);
			quantidade.put(item, numeros);
			if (quantidade.get(item).intValue() <= 45) {
				if (Main.plugin.pagina3.contains(p.getUniqueId())) {
					Main.plugin.pagina3.remove(p.getUniqueId());
				}
				kitspagina3player.remove(p.getUniqueId());
				kitspagina3.clear();
				selector.put(item, name.toLowerCase());
				Main.plugin.kitselector.get(p.getUniqueId()).addItem(item);
			} else {
				if (!Main.plugin.pagina3.contains(p.getUniqueId())) {
					Main.plugin.pagina3.add(p.getUniqueId());
				}
				kitspagina3.remove(name.toLowerCase());
				kitspagina3.add(name.toLowerCase());
				kitspagina3player.put(p.getUniqueId(), kitspagina3);
			}
		}
		ItemStack[] arrayOfItemStack;
		int vidros = (arrayOfItemStack = Main.plugin.kitselector.get(p.getUniqueId()).getContents()).length;
		for (int metavidros = 0; metavidros < vidros; metavidros++) {
			ItemStack item2 = arrayOfItemStack[metavidros];
			if (item2 == null) {
				Main.plugin.kitselector.get(p.getUniqueId()).setItem(Main.plugin.kitselector.get(p.getUniqueId()).firstEmpty(), vidro);
			}
		}
	}

	public void InventoryKitteste3(Player p) {
		if (Main.plugin.openloja1.contains(p.getUniqueId())) {
			Main.plugin.openloja1.remove(p.getUniqueId());
		}
		if (Main.plugin.openloja2.contains(p.getUniqueId())) {
			Main.plugin.openloja2.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele1.contains(p.getUniqueId())) {
			Main.plugin.opensele1.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele2.contains(p.getUniqueId())) {
			Main.plugin.opensele2.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele3.contains(p.getUniqueId())) {
			Main.plugin.opensele3.remove(p.getUniqueId());
		}
		if (Main.plugin.opensele4.contains(p.getUniqueId())) {
			Main.plugin.opensele4.remove(p.getUniqueId());
		}
		Main.plugin.opensele3.add(p.getUniqueId());
		Main.plugin.kitselector.get(p.getUniqueId()).clear();
		ItemStack vidro = new ItemStack(Material.THIN_GLASS);
		ItemMeta metav = vidro.getItemMeta();
		metav.setDisplayName("§c");
		vidro.setItemMeta(metav);

		ItemStack cabeca = new ItemStack(Material.ENCHANTMENT_TABLE);
		ItemMeta cabecaim = cabeca.getItemMeta();
		cabecaim.setDisplayName(ChatColor.GOLD + "Seletor de Kits!");
		cabeca.setItemMeta(cabecaim);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(4, cabeca);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(2, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(3, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(5, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(6, vidro);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(1, vidro);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(7, vidro);

		ItemStack la2 = new ItemStack(Material.INK_SACK, (short) 1, (short) 10);
		ItemMeta laim2 = la2.getItemMeta();
		laim2.setDisplayName(ChatColor.GRAY + "Voltar");
		la2.setItemMeta(laim2);

		ItemStack la = new ItemStack(Material.INK_SACK, (short) 1, (short) 10);
		ItemMeta laim = la.getItemMeta();
		laim.setDisplayName(ChatColor.GRAY + "Proxima pagina");
		la.setItemMeta(laim);

		Main.plugin.kitselector.get(p.getUniqueId()).setItem(0, la2);
		Main.plugin.kitselector.get(p.getUniqueId()).setItem(8, la);
		ArrayList<String> kitspagina4 = new ArrayList<>();
		HashMap<ItemStack, Integer> quantidade = new HashMap<>();
		ArrayList<String> lista = kitspagina3player.get(p.getUniqueId());
		Collections.sort(lista, String.CASE_INSENSITIVE_ORDER);
		int numeros = 0;
		for (String name : lista) {
			numeros++;
			char[] stringArray = name.toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			name = new String(stringArray);
			ItemStack item = new ItemStack(items.get(name.toLowerCase()).icon);
			ItemMeta itemim = item.getItemMeta();
			itemim.setDisplayName(ChatColor.AQUA + "Kit: " + ChatColor.GRAY + name);
			itemim.setLore(items.get(name.toLowerCase()).kitInfo);
			item.setItemMeta(itemim);
			quantidade.put(item, numeros);
			if (quantidade.get(item).intValue() <= 45) {
				if (Main.plugin.pagina4.contains(p.getUniqueId())) {
					Main.plugin.pagina4.remove(p.getUniqueId());
				}
				kitspagina4player.remove(p.getUniqueId());
				kitspagina4.clear();
				selector.put(item, name.toLowerCase());
				Main.plugin.kitselector.get(p.getUniqueId()).addItem(item);
			} else {
				if (!Main.plugin.pagina4.contains(p.getUniqueId())) {
					Main.plugin.pagina4.add(p.getUniqueId());
				}
				kitspagina4.remove(name.toLowerCase());
				kitspagina4.add(name.toLowerCase());
				kitspagina4player.put(p.getUniqueId(), kitspagina4);
			}

		}
		ItemStack[] arrayOfItemStack;
		int vidros = (arrayOfItemStack = Main.plugin.kitselector.get(p.getUniqueId()).getContents()).length;
		for (int metavidros = 0; metavidros < vidros; metavidros++) {
			ItemStack item2 = arrayOfItemStack[metavidros];
			if (item2 == null) {
				Main.plugin.kitselector.get(p.getUniqueId()).setItem(Main.plugin.kitselector.get(p.getUniqueId()).firstEmpty(), vidro);
			}
		}
	}

	public void giveItem(Player p) {
		p.getInventory().clear();
		if (KITS.get(p.getUniqueId()) == null) {

			return;
		}
		if (hasAbility(p, "surprise")) {
			setSurprise(p);
		}
		String name = KITS.get(p.getUniqueId()).toLowerCase();
		if (items.get(name) != null) {
			for (ItemStack i : items.get(name).items) {
				p.getInventory().addItem(i);
			}
		}
	}

	public void giveMini(Player p) {
		String name = KITS.get(p.getUniqueId()).toLowerCase();
		if (hasAbility(p, "surprise")) {
			setSurprise(p);
		}
		if (items.get(name) != null) {
			for (ItemStack i : items.get(name).items) {
				p.getInventory().addItem(i);
			}
		}
	}

	public void setKit(Player p, String kit) {
		if (!isKit(kit)) {
			p.closeInventory();
			p.sendMessage(ChatColor.RED + getKitName(kit) + " não encontrado.");
			return;
		}
		if (Main.plugin.kitsonoff.contains(kit)) {
			p.sendMessage(ChatColor.RED + "Kit desativado.");
			return;
		}
		if ((Main.est != Estagio.PREJOGO && !p.hasPermission("PixelHG.pex.VIP")) || Main.TempoPartida > 300) {
			p.sendMessage(ChatColor.RED + "A partida já começou.");
			return;
		}
		if (!p.hasPermission("PixelHG.kit." + getKitName(kit))) {
			p.closeInventory();
			p.sendMessage(ChatColor.RED + "Você não possuí o kit: " + getKitName(kit));
			return;
		}

		if (Main.est != Estagio.PREJOGO && KITS.containsKey(p.getUniqueId())) {
			p.sendMessage(ChatColor.RED + "A partida já começou.");
			String info = "";
			if (items.get(kit) != null) {
				if (!items.get(kit).kitInfo.isEmpty()) {

				} else {
					info = "Acesse sua lista de kits, use /kit";
				}
			}
			if (!info.isEmpty())
				p.sendMessage(ChatColor.RED + info);
			return;
		}
		p.sendMessage(ChatColor.AQUA + "Kit " + getKitName(kit) + " selecionado.");
		p.sendMessage(ChatColor.AQUA + "Acesse sua lista de kits, use /kit");
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.5F);
		String info = "";
		if (items.get(kit) != null) {
			if (!items.get(kit).kitInfo.isEmpty()) {
				String str = "";
				for (String s : items.get(kit).kitInfo) {
					str = str + " " + s;
				}

				info = "Info:" + str;
			} else {
				info = "Acesse sua lista de kits, use /kit";
			}
		}
		if (!info.isEmpty())
			p.sendMessage(ChatColor.RED + info);
		KITS.put(p.getUniqueId(), kit);
		Bukkit.getPluginManager().callEvent(new PlayerSelectKitEvent(p, kit));
		if (Main.est != Estagio.PREJOGO) {
			giveMini(p);
		}
	}

	public void toggleKit(Player p, String kit, String off) {
		if (kit.equalsIgnoreCase("all")) {
			if (off.equalsIgnoreCase("on")) {
				for (String desa : kits) {
					if (desa.equalsIgnoreCase("semkit"))
						continue;
					if (!Main.plugin.kitsonoff.contains(desa))
						continue;
					Main.plugin.kitsonoff.remove(desa);
				}
				p.sendMessage(ChatColor.GREEN + "Todos os kits foram ativados.");
			} else if (off.equalsIgnoreCase("off")) {
				for (String desa : kits) {
					if (desa.equalsIgnoreCase("semkit"))
						continue;
					if (Main.plugin.kitsonoff.contains(desa))
						continue;
					Main.plugin.kitsonoff.add(desa);
				}
				p.sendMessage(ChatColor.GREEN + "Todos os kits foram desativados.");
			}
		} else {
			if (!isKit(kit)) {
				p.sendMessage(ChatColor.RED + "Kit não encontrando.");
				return;
			}
			if (off.equalsIgnoreCase("on")) {
				if (Main.plugin.kitsonoff.contains(kit)) {
					Main.plugin.kitsonoff.remove(kit);
					p.sendMessage(ChatColor.GREEN + "Kit " + kit + " ativado.");
				} else {
					p.sendMessage(ChatColor.RED + "O Kit não está desativado.");
				}
			} else if (off.equalsIgnoreCase("off")) {
				if (Main.plugin.kitsonoff.contains(kit)) {
					p.sendMessage(ChatColor.RED + "O Kit não está ativado.");
				} else {
					Main.plugin.kitsonoff.add(kit);
					p.sendMessage(ChatColor.GREEN + "Kit " + kit + " desativado.");
				}
			}

		}
	}

	public void toggleKit(Logger p, String kit, String off) {
		if (kit.equalsIgnoreCase("all")) {
			if (off.equalsIgnoreCase("on")) {
				for (String desa : kits) {
					if (desa.equalsIgnoreCase("semkit"))
						continue;
					if (!Main.plugin.kitsonoff.contains(desa))
						continue;
					Main.plugin.kitsonoff.remove(desa);
				}
				p.info(ChatColor.GREEN + "Todos os kits foram ativados.");
			} else if (off.equalsIgnoreCase("off")) {
				for (String desa : kits) {
					if (desa.equalsIgnoreCase("semkit"))
						continue;
					if (Main.plugin.kitsonoff.contains(desa))
						continue;
					Main.plugin.kitsonoff.add(desa);
				}
				p.info(ChatColor.GREEN + "Todos os kits foram desativados.");
			}
		} else {
			if (!isKit(kit)) {
				p.info(ChatColor.RED + "Kit não encontrando.");
				return;
			}
			if (off.equalsIgnoreCase("on")) {
				if (Main.plugin.kitsonoff.contains(kit)) {
					Main.plugin.kitsonoff.remove(kit);
					p.info(ChatColor.GREEN + "Kit " + kit + " ativado.");
				} else {
					p.info(ChatColor.RED + "O Kit não está desativado.");
				}
			} else if (off.equalsIgnoreCase("off")) {
				if (Main.plugin.kitsonoff.contains(kit)) {
					p.info(ChatColor.RED + "O Kit não está ativado.");
				} else {
					Main.plugin.kitsonoff.add(kit);
					p.info(ChatColor.GREEN + "Kit " + kit + " desativado.");
				}
			}

		}
	}

	@SuppressWarnings("deprecation")
	public void forceKit(Player sender, String p, String kit) {

		if (p.equalsIgnoreCase("all")) {
			if (kit.equalsIgnoreCase("nenhum")) {
				for (Player on : Bukkit.getOnlinePlayers()) {

					KITS.remove(on.getUniqueId());
				}
			} else {
				if (!isKit(kit)) {
					sender.sendMessage(ChatColor.RED + "Kit não encontrando.");
					return;
				}
				for (Player on : Bukkit.getOnlinePlayers()) {

					KITS.put(on.getUniqueId(), kit);
					if (Main.est != Estagio.PREJOGO) {
						giveMini(on);
					}
				}

			}
			sender.sendMessage(ChatColor.GREEN + "Kit setado para todos os players da partida.");

		} else {
			Player p2 = Bukkit.getPlayer(p);
			if (p2 == null) {
				sender.sendMessage(ChatColor.RED + "Jogador não encontrando.");
				return;
			}

			if (kit.equalsIgnoreCase("nenhum")) {
				KITS.remove(p2.getUniqueId());
			} else {
				if (!isKit(kit)) {
					sender.sendMessage(ChatColor.RED + "Kit não encontrando.");
					return;
				}
				KITS.put(p2.getUniqueId(), kit);
				if (Main.est != Estagio.PREJOGO) {
					giveMini(p2);
				}

			}
			sender.sendMessage(ChatColor.GREEN + "Kit setado.");
		}

	}

	public void forceKit(Player p, String kit) {
		if (!isKit(kit)) {
			return;
		}

		KITS.put(p.getUniqueId(), kit);

	}

	public Kit[] getOtherKits(Player p) {
		ArrayList<String> kits = new ArrayList<>();
		for (String kit : kits) {
			if ((!p.hasPermission("PixelHG.kit." + kit.toLowerCase())) && (!p.hasPermission("PixelHG.kit.*"))) {
				kits.add(kit);
			}
		}
		return (Kit[]) kits.toArray(new Kit[0]);
	}

	public void printKitChat(Player player) {
		PlayerManager.updateKits(player);
		List<String> yourkits = new ArrayList<String>();
		List<String> otherkits = new ArrayList<String>();
		for (String name : kits) {
			if (name.equalsIgnoreCase("semkit"))
				continue;
			if (Main.plugin.kitsonoff.contains(name))
				continue;
			char[] stringArray = name.toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			name = new String(stringArray);

			if (Main.plugin.seuskits.get(player.getUniqueId()).contains(name) || Main.plugin.seuskits.get(player.getUniqueId()).contains(name.toLowerCase())) {
				yourkits.add(name);
			} else {
				otherkits.add(name);
			}
		}

		Collections.sort(yourkits, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(otherkits, String.CASE_INSENSITIVE_ORDER);
		String list = StringUtils.join(yourkits, ", ");
		String list2 = StringUtils.join(otherkits, ", ");
		if (kitsHabilitados == true) {
			player.sendMessage(ChatColor.GREEN + "Para escolher um kit use /kit (Kit)");
			if (list.isEmpty()) {
				player.sendMessage(ChatColor.AQUA + "Seus kits: " + ChatColor.RED + "Erro ao carregar seus kits!");
			} else {
				player.sendMessage(ChatColor.AQUA + "Seus kits: " + ChatColor.WHITE + list);
			}
			if (list2.isEmpty()) {
				player.sendMessage(ChatColor.AQUA + "Outros Kits: " + ChatColor.RED + "Você tem todos os kits!");
			} else {
				player.sendMessage(ChatColor.AQUA + "Outros Kits: " + ChatColor.RED + list2);
			}
			player.sendMessage(ChatColor.GRAY + "quer comprar kits? acesse: " + ChatColor.AQUA + "loja.mc-pixel.com");
		} else {
			player.sendMessage(ChatColor.GREEN + "Para escolher um kit use /kit (Kit)");
			if (list.isEmpty()) {
				player.sendMessage(ChatColor.AQUA + "Seus kits: " + ChatColor.RED + "Desativados");
			} else {
				player.sendMessage(ChatColor.AQUA + "Seus kits: " + ChatColor.RED + "Desativados");
			}
			if (list2.isEmpty()) {
				player.sendMessage(ChatColor.AQUA + "Outros Kits: " + ChatColor.RED + "Você tem todos os kits!");
			} else {
				player.sendMessage(ChatColor.AQUA + "Outros Kits: " + ChatColor.RED + list2);
			}
			player.sendMessage(ChatColor.GRAY + "quer comprar kits? acesse: " + ChatColor.AQUA + "loja.mc-pixel.com");
		}
	}

	public boolean hasAbility(Player p, String kitName) {
		return !Main.plugin.kitsonoff.contains(kitName) && KITS.containsKey(p.getUniqueId()) && KITS.get(p.getUniqueId()).toLowerCase().equals(kitName.toLowerCase());
	}

	public void addKit(String kit, Kit k) {
		kits.add(kit.toLowerCase());
		items.put(kit.toLowerCase(), k);
	}

	public String getKitInfo(String kit) {
		String info = "";
		if (items.get(kit) != null) {
			if (!items.get(kit).kitInfo.isEmpty()) {
				String str = "";
				for (String s : items.get(kit).kitInfo) {
					str = str + " " + s;
				}
				info = str;
			} else {
				info = "Acesse sua lista de kits, use /kit";
			}
		}
		return info;
	}

	public boolean isKit(String kit) {
		return kits.contains(kit.toLowerCase());
	}

	public String getKitName(String kit) {
		char[] stringArray = kit.toLowerCase().toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		return new String(stringArray);
	}

	public void setSurprise(Player player) {
		surprises.add(player.getUniqueId());
		String kit = getViableKit();
		KITS.put(player.getUniqueId(), kit);
		player.sendMessage(ChatColor.AQUA + "Seu surprise é: " + getKitName(kit));
		String info = "";
		if (items.get(kit) != null) {
			if (!items.get(kit).kitInfo.isEmpty()) {
				String str = "";
				for (String s : items.get(kit).kitInfo) {
					str = str + " " + s;
				}
				info = "Info:" + str;
			} else {
				info = "Acesse sua lista de kits, use /kit";
			}
		}
		if (!info.isEmpty())
			player.sendMessage(ChatColor.AQUA + info);
	}

	public void setAleatório(Player player) {
		String kit = getViableKit(player);
		KITS.put(player.getUniqueId(), kit);
		player.sendMessage(ChatColor.AQUA + "Seu kit aleatório é: " + getKitName(kit));
		String info = "";
		if (items.get(kit) != null) {
			if (!items.get(kit).kitInfo.isEmpty()) {
				String str = "";
				for (String s : items.get(kit).kitInfo) {
					str = str + " " + s;
				}
				info = ChatColor.RED + "Info:" + str;
			} else {
				info = "Acesse sua lista de kits, use /kit";
			}
		}
		if (!info.isEmpty())
			player.sendMessage(ChatColor.AQUA + info);
	}

	public String getViableKit() {

		if (kits.size() > 0)
			return (String) kits.get(new Random().nextInt(kits.size()));
		return null;
	}

	public String getViableKit(Player p) {
		ArrayList<String> kits2 = new ArrayList<>();
		for (String name : kits) {
			if (name.equalsIgnoreCase("semkit"))
				continue;
			if (!p.hasPermission("PixelHG.kit." + name))
				continue;
			if (Main.plugin.kitsonoff.contains(name))
				continue;
			kits2.add(name);
		}
		if (kits2.size() > 0)
			return (String) kits2.get(new Random().nextInt(kits2.size()));
		return null;
	}

	public String getPlayerKit(Player p) {
		String kit = KITS.get(p.getUniqueId());
		if (kit == null) {
			String nenhum = "Nenhum";
			char[] stringArray = nenhum.toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			kit = new String(stringArray);
		} else {
			String surprise = "Surprise";
			char[] stringArray2 = surprise.toCharArray();
			stringArray2[0] = Character.toUpperCase(stringArray2[0]);
			surprise = new String(stringArray2);
			char[] stringArray = kit.toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			kit = new String(stringArray);
			if (surprises.contains(p.getUniqueId()))
				kit = surprise + " " + kit;
		}
		return kit;
	}

	public String getPlayerKitFixo(Player p) {
		String kit = "";
		if (surprises.contains(p.getUniqueId())) {
			kit = "Surprise " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p));
		} else {
			kit = Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p));
		}
		return kit;
	}

	public String getPlayerKitnosurprise(Player p) {
		String kit = KITS.get(p.getUniqueId());
		String kitName = "";
		if (kit != null && !Main.plugin.kitsonoff.contains(kit)) {
			char[] stringArray = kit.toLowerCase().toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			kitName = kitName + new String(stringArray);
			return kitName;
		}
		return "Nenhum";
	}
}