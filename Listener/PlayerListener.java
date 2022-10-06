package me.ghost.Listener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.ghost.Main;
import me.ghost.API.BarAPI;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.CooldownManager;
import me.ghost.Manager.PlayerManager;
import me.ghost.Manager.Teste;
import me.ghost.Util.CharAPI;
import me.ghost.Util.Feast;
import net.minecraft.server.v1_7_R4.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {
	public static ArrayList<UUID> relog = new ArrayList<UUID>();
	private ArrayList<UUID> reloged = new ArrayList<UUID>();
	public ArrayList<UUID> combatlog = new ArrayList<UUID>();
	public ArrayList<UUID> joined = new ArrayList<UUID>();
	public static boolean chat = true;
	private HashMap<UUID, Integer> mute = new HashMap<UUID, Integer>();

	@EventHandler
	public void Join(PlayerJoinEvent e) {
		final Player p = e.getPlayer();

		e.setJoinMessage(ChatColor.AQUA + p.getName() + " entrou no server.");
		Inventory inv = Bukkit.getServer().createInventory(p, 54, ChatColor.BLACK + "Menu de Kits");
		Main.plugin.kitselector.put(p.getUniqueId(), inv);
		if (Main.est == Estagio.PREJOGO) {

			Main.plugin.PlayersPreJogo.add(p.getUniqueId());
			if (p.hasPermission("PixelHG.admin")) {
				Main.plugin.admin.setAdmin(p);

			}
			p.sendMessage(CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua());
			p.sendMessage(CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua());
			p.sendMessage(CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza());
			p.sendMessage(CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza());
			p.sendMessage(CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua());
			p.sendMessage(CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua());
			p.sendMessage(CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua());
			p.sendMessage(CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeCinza() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua() + CharAPI.getCubeAqua());
			e.setJoinMessage(null);
			Location local = new Location(p.getWorld(), 0, p.getWorld().getHighestBlockYAt(0, 0), 0);
			Random r = new Random();
			Location loc = new Location(p.getWorld(), -10 + r.nextInt(20), local.getY() + 1, -10 + r.nextInt(20));
			p.teleport(loc);
			PlayerManager.Items(p);

		} else {
			if (relog.contains(p.getUniqueId()) && !Main.plugin.NaoEstaJogando(p)) {
				reloged.add(p.getUniqueId());
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						relog.remove(p.getUniqueId());
					}
				}, 20 * 20);
			}
			if (Main.plugin.NaoEstaJogando(p) && Main.est != Estagio.PREJOGO) {
				if ((p.hasPermission("PixelHG.vip.entrar") || p.hasPermission("PixelHG.youtuber.entrar")) && Main.TempoPartida < 300) {
					Main.plugin.jogadores.add(p.getUniqueId());
					Location local = PlayerManager.getRespawnLocation();
					local.getWorld().getChunkAt(local).load();
					p.teleport(local);
					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
				} else if ((p.hasPermission("PixelHG.vip.espectar") || p.hasPermission("PixelHG.youtuber.espectar")) && Main.TempoPartida > 300) {
					e.setJoinMessage(null);
					Main.plugin.admin.setYoutuber(p);
					p.getInventory().clear();
					p.teleport(Bukkit.getWorld("world").getSpawnLocation());
				} else if (p.hasPermission("PixelHG.admin") && Main.TempoPartida < 300) {
					e.setJoinMessage(null);
					Main.plugin.admin.setAdmin(p);
					p.getInventory().clear();
					p.teleport(Bukkit.getWorld("world").getSpawnLocation());
				} else if (p.hasPermission("PixelHG.admin") && Main.TempoPartida > 300) {
					e.setJoinMessage(null);
					Main.plugin.admin.setAdmin(p);
					p.getInventory().clear();
					p.teleport(Bukkit.getWorld("world").getSpawnLocation());
				}
			}
		}
		if (Main.plugin.NaoEstaJogando(p)) {
			e.setJoinMessage(null);
		}
		Main.plugin.vanish.updateVanished();
	}

	@EventHandler
	public void onCommandPlayer(final PlayerCommandPreprocessEvent e) {
		String kit = e.getMessage().replace("/", "");
		if (Main.plugin.kit.isKit(kit)) {
			Main.plugin.kit.setKit(e.getPlayer(), kit);
			e.setCancelled(true);
			return;
		}

	}

	@EventHandler
	public void onKit(PlayerMoveEvent event) {
		if (!(event.getPlayer() instanceof Player))
			return;
		if (Main.est != Estagio.JOGO)
			return;
		for (Entity kit : event.getPlayer().getNearbyEntities(2.0D, 2.0D, 2.0D)) {
			if (kit instanceof Player) {
				Player p2 = (Player) kit;
				if (!Main.plugin.NaoEstaJogando(p2)) {
					if (Main.plugin.kit.surprises.contains(p2.getUniqueId())) {
						BarAPI.setMessage(event.getPlayer(), p2.getName() + " - Surprise " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p2)), 3);
					} else {
						BarAPI.setMessage(event.getPlayer(), p2.getName() + " - " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p2)), 3);
					}
				}
			}
		}
	}

	@EventHandler
	public void onCommandPlayer2(final PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equalsIgnoreCase("/random")) {
			if (Main.plugin.aleatorio2.contains(e.getPlayer().getUniqueId()))
				return;
			if (Main.TempoPrejogo >= 20) {
				Teste.AleatorioRodar(e.getPlayer());
			}
			e.setCancelled(true);
			return;
		}

		if (e.getMessage().equalsIgnoreCase("/me")) {
			e.setCancelled(true);
		}

	}

	@EventHandler
	public void onCombatLog(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		if (!(event.getDamager() instanceof Player))
			return;
		if (Main.est != Estagio.JOGO)
			return;
		Player p = (Player) event.getEntity();
		if (!CooldownManager.isInCooldown(p.getUniqueId(), "combatlog")) {
			CooldownManager c = new CooldownManager(p.getUniqueId(), "combatlog", 5);
			c.start();
		} else if (CooldownManager.isInCooldown(p.getUniqueId(), "combatlog")) {
			CooldownManager c = new CooldownManager(p.getUniqueId(), "combatlog", 5);
			c.start();
		}

	}

	@SuppressWarnings("rawtypes")
	@EventHandler
	public void onCompass(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if (Main.plugin.aleatorio2.contains(p.getUniqueId())) {
			event.setCancelled(true);
		}
		if (Main.est != Estagio.PREJOGO) {
			if ((p.getItemInHand().getType() == Material.COMPASS) && ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK) || (event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.RIGHT_CLICK_AIR))) {
				Boolean found = Boolean.valueOf(false);
				for (int i = 0; i < 1000; i++) {
					List entities = p.getNearbyEntities(i, 128.0D, i);
					for (Object e : entities) {
						if ((((Entity) e).getType().equals(EntityType.PLAYER)) && (p.getLocation().distance(((Entity) e).getLocation()) > 25.0D)) {
							if (!Main.plugin.NaoEstaJogando((Player) e)) {
								p.setCompassTarget(((Entity) e).getLocation());
								Player alvo = (Player) e;
								p.sendMessage(ChatColor.AQUA + "Bússola apontando para: " + ChatColor.GRAY + alvo.getName());
								found = Boolean.valueOf(true);
								break;
							}
						}
					}
					if (found.booleanValue()) {
						break;
					}
				}
				if (!found.booleanValue()) {
					p.sendMessage(ChatColor.RED + "Bússola apontando para: Spawn");
					p.setCompassTarget(new Location(p.getWorld(), 0.0D, 120.0D, 0.0D));
				}
			}
		}

		if (Main.est == Estagio.PREJOGO) {
			if (p.getItemInHand().getType() == Material.CHEST && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().contains("Menu de Kits") && ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK) || (event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.RIGHT_CLICK_AIR))) {

				Main.plugin.kit.InventoryKitteste(p);
				p.openInventory(Main.plugin.kitselector.get(p.getUniqueId()));
				event.setCancelled(true);
			}
		}

		if (Main.est == Estagio.JOGO) {
			if (p.getItemInHand().getType() == Material.WOOL && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().contains("Pegar Kit")) {
				Main.plugin.kit.giveMini(p);
				p.setItemInHand(new ItemStack(Material.AIR));
				p.updateInventory();
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void Logar(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if (Main.est != Estagio.PREJOGO) {
			if (!Main.plugin.NaoEstaJogando(p)) {
				if (relog.contains(p.getUniqueId())) {
					if (!reloged.contains(p.getUniqueId()))
						return;
				}
			}
			if (Main.TempoPartida > 300) {
				if (!(p.hasPermission("PixelHG.vip.espectar") || p.hasPermission("PixelHG.youtuber.espectar") || p.hasPermission("PixelHG.admin"))) {
					if (DeathListener.deathMessage.containsKey(p.getUniqueId()))
						e.disallow(Result.KICK_OTHER, DeathListener.deathMessage.get(p.getUniqueId()));
					else
						e.disallow(Result.KICK_OTHER, ChatColor.AQUA + "A partida já começou.\n Quer espectar? Acesse a loja e veja como: " + ChatColor.GRAY + "loja.mc-pixel.com");
				}
			} else {
				if (!(p.hasPermission("PixelHG.vip.entrar") || p.hasPermission("PixelHG.youtuber.entrar") || p.hasPermission("PixelHG.admin"))) {
					if (DeathListener.deathMessage.containsKey(p.getUniqueId()))
						e.disallow(Result.KICK_OTHER, DeathListener.deathMessage.get(p.getUniqueId()));
					else
						e.disallow(Result.KICK_OTHER, ChatColor.AQUA + "A partida já começou.\n Quer entrar nos primeiros 5 minutos? Acesse: " + ChatColor.GRAY + "loja.mc-pixel.com");
				}
			}
		}
		if (e.getResult() == Result.KICK_FULL) {
			if (p.hasPermission("PixelHG.vip.entrarfull")) {
				e.allow();
			} else {
				e.setKickMessage(ChatColor.AQUA + "Sever Lotado!\n Para entrar adiquira vip em nossa loja:" + ChatColor.WHITE + " loja.mc-pixel.com");
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (Feast.isFeastBlock(event.getBlock())) {
			event.getPlayer().sendMessage(ChatColor.RED + "Você não pode quebrar blocos do Feast");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (Feast.isFeastBlock(event.getBlock())) {
			event.getPlayer().sendMessage(ChatColor.RED + "Você não pode colocar blocos no Feast");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onCombatLog2(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		if (!(event.getDamager() instanceof Player))
			return;
		if (Main.est != Estagio.JOGO)
			return;
		Player p = (Player) event.getEntity();
		if (!CooldownManager.isInCooldown(p.getUniqueId(), "deslogar")) {
			CooldownManager c = new CooldownManager(p.getUniqueId(), "deslogar", 10);
			c.start();
		} else if (CooldownManager.isInCooldown(p.getUniqueId(), "deslogar")) {
			CooldownManager c = new CooldownManager(p.getUniqueId(), "deslogar", 10);
			c.start();
		}

	}

	@EventHandler
	public void Sair(PlayerQuitEvent event) {
		final Player p = event.getPlayer();
		if (Main.plugin.aleatorio.contains(p.getUniqueId())) {
			Main.plugin.aleatorio.remove(p.getUniqueId());
		}
		if (Main.plugin.aleatorio2.contains(p.getUniqueId())) {
			Main.plugin.aleatorio2.remove(p.getUniqueId());
		}
		event.setQuitMessage(ChatColor.AQUA + p.getName() + " saiu do server.");
		if (Main.plugin.NaoEstaJogando(p) || Main.est == Estagio.PREJOGO) {
			Main.plugin.resetContador(p);
			event.setQuitMessage(null);
			return;
		}
		if (PlayerManager.winner == p.getUniqueId()) {
			Bukkit.getServer().shutdown();
			return;
		}

		if (CooldownManager.isInCooldown(p.getUniqueId(), "deslogar")) {
			if (!Main.plugin.NaoEstaJogando(p)) {
				if (!p.isDead()) {
					String playerKit = ChatColor.AQUA + p.getName() + "(" + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)) + ")";
					if (Main.plugin.kit.surprises.contains(p.getUniqueId())) {
						playerKit = ChatColor.AQUA + p.getName() + "(Surprise " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)) + ")";
					}
					DeathListener.deathMessage.put(p.getUniqueId(), playerKit + " deslogou enquanto lutava");
					Bukkit.getServer().broadcastMessage(playerKit + " deslogou enquanto lutava");
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.ITALIC + "" + ChatColor.RESET + ChatColor.GRAY + "Jogadores: " + ChatColor.AQUA + (Main.plugin.jogadores.size() - 1));
					PlayerManager.dropItems(p, p.getLocation());
					Main.plugin.RemoverJogador(p);
					PlayerManager.checkWinner();
					return;
				}
			}
		}
		if (Main.plugin.espere.contains(p.getUniqueId())) {
			Main.plugin.espere.remove(p.getUniqueId());
		}
		if (!relog.contains(p.getUniqueId()) && Main.est != Estagio.PREJOGO) {
			relog.add(p.getUniqueId());
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				public void run() {
					if (!reloged.contains(p.getUniqueId())) {
						relog.remove(p.getUniqueId());
						String playerKit = ChatColor.AQUA + p.getName() + "(" + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)) + ")";
						if (Main.plugin.kit.surprises.contains(p.getUniqueId())) {
							playerKit = ChatColor.AQUA + p.getName() + "(Surprise " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)) + ")";
						}
						Random r = new Random();
						int chance = r.nextInt(1);
						if (chance == 0) {
							DeathListener.deathMessage.put(p.getUniqueId(), playerKit + " demorou para entrar de novo e foi desclassificado");
							Bukkit.getServer().broadcastMessage(playerKit + " demorou para entrar de novo e foi desclassificado");
							Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "" + ChatColor.RESET + ChatColor.GRAY + "Jogadores: " + ChatColor.AQUA + (Main.plugin.jogadores.size() - 1));
						} else if (chance == 1) {
							DeathListener.deathMessage.put(p.getUniqueId(), playerKit + " saiu e não voltou mais");
							Bukkit.getServer().broadcastMessage(playerKit + " saiu e não voltou mais");
							Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "" + ChatColor.RESET + ChatColor.GRAY + "Jogadores: " + ChatColor.AQUA + (Main.plugin.jogadores.size() - 1));
						}
						PlayerManager.dropItems(p, p.getLocation());
						Main.plugin.RemoverJogador(p);
						PlayerManager.checkWinner();
					} else {
						reloged.remove(p.getUniqueId());
					}
				}
			}, 30 * 20);
			return;
		}
		if (Main.est == Estagio.JOGO && !p.isDead()) {
			Random r = new Random();
			int chance = r.nextInt(1);
			String playerKit = ChatColor.AQUA + p.getName() + "(" + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)) + ")";
			if (Main.plugin.kit.surprises.contains(p.getUniqueId())) {
				playerKit = ChatColor.AQUA + p.getName() + "(Surprise " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)) + ")";
			}
			if (chance == 0) {
				DeathListener.deathMessage.put(p.getUniqueId(), playerKit + " saiu o torneio e desistiu");
				Bukkit.getServer().broadcastMessage(playerKit + " saiu o torneio e desistiu");
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "" + ChatColor.RESET + ChatColor.GRAY + "Jogadores: " + ChatColor.AQUA + (Main.plugin.jogadores.size() - 1));
			} else if (chance == 1) {
				DeathListener.deathMessage.put(p.getUniqueId(), playerKit + " desistiu do torneio");
				Bukkit.getServer().broadcastMessage(playerKit + " desistiu do torneio");
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "" + ChatColor.RESET + ChatColor.GRAY + "Jogadores: " + ChatColor.AQUA + (Main.plugin.jogadores.size() - 1));
			}
			PlayerManager.dropItems(p, p.getLocation());
			if (!joined.contains(p.getUniqueId()))
				joined.add(p.getUniqueId());
			Main.plugin.RemoverJogador(p);
			PlayerManager.checkWinner();
		}
	}

	@EventHandler
	public void Folha(LeavesDecayEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void Sopa(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (e.getAction().name().contains("RIGHT")) {
			ItemStack hand = player.getItemInHand();
			if ((hand != null) && (hand.getType() == Material.MUSHROOM_SOUP)) {
				EntityPlayer p = ((CraftPlayer) player).getHandle();
				if (p.getHealth() < p.getMaxHealth()) {
					if (p.getHealth() + 7 >= p.getMaxHealth()) {
						p.setHealth(p.getMaxHealth());
						player.getItemInHand().setType(Material.BOWL);
						player.updateInventory();
					} else {
						p.setHealth(p.getHealth() + 7);
						player.getItemInHand().setType(Material.BOWL);
						player.updateInventory();
					}
				} else if (player.getFoodLevel() < 20) {
					if (player.getFoodLevel() + 6 < 20.0F) {
						player.setFoodLevel((int) (player.getFoodLevel() + 6));
					} else {
						player.setFoodLevel((int) (player.getFoodLevel() + 6));
					}
					player.getItemInHand().setType(Material.BOWL);
					player.updateInventory();
				} else {
					player.getFoodLevel();
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamageEvent(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player))
			return;
		Player p = (Player) event.getDamager();
		ItemStack sword = p.getItemInHand();
		double damage = event.getDamage();
		double danoEspada = getDamage(sword.getType());
		boolean isMore = false;
		if (damage > 2) {
			isMore = true;
		}
		if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
			for (PotionEffect effect : p.getActivePotionEffects()) {
				if (effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
					double minus;
					if (isCrital(p)) {
						minus = (danoEspada + (danoEspada / 2)) * 1.3 * (effect.getAmplifier() + 1);
					} else {
						minus = danoEspada * 1.3 * (effect.getAmplifier() + 1);
					}
					damage = damage - minus;
					damage += 2 * (effect.getAmplifier() + 1);
					break;
				}
			}
		}
		if (!sword.getEnchantments().isEmpty()) {
			if (sword.containsEnchantment(Enchantment.DAMAGE_ARTHROPODS) && isArthropod(event.getEntityType())) {
				damage = damage - (1.5 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS));
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);
			}
			if (sword.containsEnchantment(Enchantment.DAMAGE_UNDEAD) && isUndead(event.getEntityType())) {
				damage = damage - (1.5 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD));
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);
			}
			if (sword.containsEnchantment(Enchantment.DAMAGE_ALL)) {
				damage = damage - 1.25 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
			}
		}
		if (isCrital(p)) {
			damage = damage - (danoEspada / 2);
			damage += 1;
		}
		if (isMore)
			damage -= 2;
		event.setDamage(damage);
	}

	@SuppressWarnings("deprecation")
	private boolean isCrital(Player p) {
		return p.getFallDistance() > 0 && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
	}

	private boolean isArthropod(EntityType type) {
		switch (type) {
		case CAVE_SPIDER:
			return true;
		case SPIDER:
			return true;
		case SILVERFISH:
			return true;
		default:
			break;
		}
		return false;
	}

	private boolean isUndead(EntityType type) {
		switch (type) {
		case SKELETON:
			return true;
		case ZOMBIE:
			return true;
		case WITHER_SKULL:
			return true;
		case PIG_ZOMBIE:
			return true;
		default:
			break;
		}
		return false;
	}

	private double getDamage(Material type) {
		double damage = 1.0;
		if (type.toString().contains("DIAMOND_")) {
			damage = 7.0;
		} else if (type.toString().contains("IRON_")) {
			damage = 6.0;
		} else if (type.toString().contains("STONE_")) {
			damage = 5.0;
		} else if (type.toString().contains("WOOD_")) {
			damage = 4.0;
		} else if (type.toString().contains("GOLD_")) {
			damage = 4.0;
		}
		if (!type.toString().contains("_SWORD")) {
			damage--;
			if (!type.toString().contains("_AXE")) {
				damage--;
				if (!type.toString().contains("_PICKAXE")) {
					damage--;
					if (!type.toString().contains("_SPADE")) {
						damage = 1.0;
					}
				}
			}
		}
		return damage;
	}

	@EventHandler
	public void testeasd(AsyncPlayerPreLoginEvent e) {
		UUID p = e.getUniqueId();
		ArrayList<String> kits = new ArrayList<>();
		try {
			PreparedStatement sql = Main.plugin.skits.prepareStatement("SELECT * FROM `Kits` WHERE Player='" + p.toString().replace("-", "") + "';");
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				kits.add(result.getString("Kits"));
			}
			result.close();
			sql.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Main.plugin.seuskits.put(p, kits);
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		if (event.getMessage().contains("%")) {
			event.setCancelled(true);
		}
		if (Main.plugin.chat == false && !event.getPlayer().hasPermission("PixelHG.pex.chat")) {
			event.getPlayer().sendMessage(ChatColor.RED + "O chat esta desativado");
			event.setCancelled(true);
			return;

		}

		final Player p = event.getPlayer();
		if (!p.hasPermission("PixelHG.pex.chat")) {
			if (CooldownManager.isInCooldown(p.getUniqueId(), "mutado")) {
				event.setCancelled(true);
				int timeleft = CooldownManager.getTimeLeft(p.getUniqueId(), "mutado");
				p.sendMessage(ChatColor.RED + "Você está mutado por flood, espere: " + timeleft + " segundos");
				return;
			}
			if (!mute.containsKey(p.getUniqueId())) {
				mute.put(p.getUniqueId(), Integer.valueOf(1));
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 1) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 1) {
				mute.put(p.getUniqueId(), mute.get(p.getUniqueId()).intValue() + 1);
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 2) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 2) {
				mute.put(p.getUniqueId(), mute.get(p.getUniqueId()).intValue() + 1);
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 3) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 3) {
				mute.put(p.getUniqueId(), mute.get(p.getUniqueId()).intValue() + 1);
				p.sendMessage(ChatColor.RED + "Espere para escrever de novo ou será mutado!");
				p.sendMessage(ChatColor.RED + "Você tem mais 1 aviso");
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 4) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 4) {
				mute.put(p.getUniqueId(), mute.get(p.getUniqueId()).intValue() + 1);
				p.sendMessage(ChatColor.RED + "Espere para escrever de novo ou será mutado!");
				p.sendMessage(ChatColor.RED + "Último aviso");
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 5) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 5) {
				p.sendMessage(ChatColor.RED + "Você foi mutado por 1 minuto por flood");
				event.setCancelled(true);
				if (!CooldownManager.isInCooldown(p.getUniqueId(), "mutado")) {
					CooldownManager c = new CooldownManager(p.getUniqueId(), "mutado", 60);
					c.start();
				}
				mute.remove(p.getUniqueId());
			}
		}
	}

	@EventHandler
	public void playermenu(InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getInventory() != null) {
			if (Main.plugin.aleatorio2.contains(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}
		if (e.getInventory() == p.getInventory()) {
			if (Main.est == Estagio.PREJOGO && Main.TempoPrejogo <= 15) {
				e.setCancelled(true);
			}
		}
		if ((e.getInventory().getName().contains("Menu de Kits"))) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().getType() == Material.THIN_GLASS) {
					e.setCancelled(true);

				} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Proxima pagina")) {
					e.setCancelled(true);
					if (Main.plugin.opensele1.contains(p.getUniqueId())) {
						if (Main.plugin.pagina2.contains(p.getUniqueId())) {
							Main.plugin.kit.InventoryKitteste2(p);
						} else {
							p.sendMessage(ChatColor.RED + "Voce não tem proxima pagina");
						}
					} else if (Main.plugin.opensele2.contains(p.getUniqueId())) {
						if (Main.plugin.pagina3.contains(p.getUniqueId())) {
							Main.plugin.kit.InventoryKitteste3(p);
						} else {
							p.sendMessage(ChatColor.RED + "Voce não tem proxima pagina");
						}
					} else if (Main.plugin.opensele3.contains(p.getUniqueId())) {
						if (Main.plugin.pagina4.contains(p.getUniqueId())) {
							Main.plugin.kit.InventoryKitteste3(p);
						} else {
							p.sendMessage(ChatColor.RED + "Voce não tem proxima pagina");
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Voltar")) {
					e.setCancelled(true);
					if (Main.plugin.opensele2.contains(p.getUniqueId())) {
						Main.plugin.kit.InventoryKitteste(p);
					} else if (Main.plugin.opensele3.contains(p.getUniqueId())) {
						Main.plugin.kit.InventoryKitteste2(p);
					} else if (Main.plugin.opensele4.contains(p.getUniqueId())) {
						Main.plugin.kit.InventoryKitteste3(p);
					}

				} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Seletor de Kits")) {
					e.setCancelled(true);
					if (Main.TempoPrejogo >= 20 && Main.plugin.kitsonoff.size() < 33) {
						Teste.AleatorioRodar(p);
					}
					p.closeInventory();
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("---")) {
					e.setCancelled(true);
				} else if (Main.plugin.kit.selector.containsKey(e.getCurrentItem())) {
					e.setCancelled(true);
					p.closeInventory();
					p.chat("/kit " + Main.plugin.kit.selector.get(e.getCurrentItem()).toString());
				}
			}

		}
	}

	@EventHandler
	public void PvPComando(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		if (Main.plugin.pvp == false) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void DanoComando(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (Main.plugin.dano == false) {
			e.setCancelled(true);
		}
	}
}
