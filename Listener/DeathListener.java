package me.ghost.Listener;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.ghost.Main;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.PixelsManager;
import me.ghost.Manager.PlayerManager;
import me.ghost.Util.Nome;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathListener implements Listener {

	public static HashMap<UUID, String> deathMessage = new HashMap<>();

	@EventHandler
	public void onMorte(PlayerDeathEvent event) {
		final Player p = event.getEntity();
		Player p2 = p.getKiller();
		PixelsManager.darpixels(p2, 20);
		PixelsManager.darKils(p2, 1);
		PixelsManager.darMorte(p, 1);
		if (Main.plugin.NaoEstaJogando(p)) {
			event.getDrops().clear();
			event.setDroppedExp(0);
			event.setDeathMessage(null);
			return;
		}

		if (Main.est == Estagio.PREJOGO) {
			event.setDeathMessage(null);
			return;
		}

		if (Main.est == Estagio.JOGO && p.hasPermission("PixelHG.pex.respawn") && Main.TempoPartida < 300) {
			PlayerManager.dropItems(p, event.getDrops(), p.getLocation());
			event.getDrops().clear();
			p.setHealth(20.0);
			p.setFoodLevel(20);
			p.setSaturation(5);
			for (PotionEffect pot : p.getActivePotionEffects()) {
				p.removePotionEffect(pot.getType());
				break;
			}
			event.setDeathMessage(null);
			Location local = PlayerManager.getRespawnLocation();
			local.getWorld().getChunkAt(local).load();
			p.closeInventory();
			p.teleport(local);
			p.setFireTicks(0);
			new BukkitRunnable() {
				@Override
				public void run() {
					p.getInventory().addItem(new ItemStack(Material.COMPASS));
					p.updateInventory();
					p.setFireTicks(0);
				}
			}.runTaskLater(Main.plugin, 1);
			return;
		}

		if (p.getLastDamageCause() != null && p.getLastDamageCause().getCause() != null) {
			String playerKit = ChatColor.AQUA + p.getName() + "(" + ChatColor.AQUA + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)) + ChatColor.AQUA + ")";
			if (Main.plugin.kit.surprises.contains(p.getUniqueId())) {
				playerKit = ChatColor.AQUA + p.getName() + "(" + ChatColor.AQUA + "Surprise " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)) + ChatColor.AQUA + ")";
			}
			DamageCause cause = p.getLastDamageCause().getCause();
			String cor = ChatColor.AQUA.toString();
			String messageDeath = "";
			Random r = new Random();
			if (cause == DamageCause.BLOCK_EXPLOSION) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " foi desintegrado";
					deathMessage(playerKit + cor + " foi desintegrado");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " morreu explodido";
					deathMessage(playerKit + cor + " morreu explodido");
				}
			} else if (cause == DamageCause.CONTACT) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu para um cacto";
					deathMessage(playerKit + cor + " morreu para um cacto");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " morreu por espinhos";
					deathMessage(playerKit + cor + " morreu por espinhos");
				}
			} else if (cause == DamageCause.VOID) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " descobriu que existe uma barreira";
					deathMessage(playerKit + cor + " descobriu que existe uma barreira");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " tentou fugir e morreu";
					deathMessage(playerKit + cor + " tentou fugir e morreu");
				}
			} else if (cause == DamageCause.DROWNING) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu afogado";
					deathMessage(playerKit + cor + " morreu afogado");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " encheu o pulmão cheio de água";
					deathMessage(playerKit + cor + " encheu com o pulmão cheio de água");
				}
			} else if (cause == DamageCause.ENTITY_ATTACK) {
				if (p.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
					EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) p.getLastDamageCause();
					if (e.getDamager() instanceof Player) {
						Player killer = p.getKiller();
						String killerKit = ChatColor.AQUA + "(" + ChatColor.AQUA + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(killer)) + ChatColor.AQUA + ")";
						if (Main.plugin.kit.surprises.contains(killer.getUniqueId())) {
							killerKit = ChatColor.AQUA + "(" + ChatColor.AQUA + "Surprise " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(killer)) + ChatColor.AQUA + ")";

						}
						String arma = Nome.getItemName(killer.getItemInHand());
						int chance = r.nextInt(1);
						if (chance == 0) {
							messageDeath = cor + killer.getName() + killerKit + cor + " destruiu " + playerKit + cor + " com " + arma;
							deathMessage(cor + killer.getName() + killerKit + cor + " destruiu " + playerKit + cor + " com " + arma);
						} else if (chance == 1) {
							messageDeath = cor + killer.getName() + killerKit + cor + " acabou com " + playerKit + cor + " com " + arma;
							deathMessage(cor + killer.getName() + killerKit + cor + " acabou com " + playerKit + cor + " com " + arma);
						}
					} else {
						if (e.getDamager().getType().name().contains("ZOMBIE")) {
							int chance = r.nextInt(1);
							if (chance == 0) {
								messageDeath = cor + playerKit + cor + " morreu para um Zombie";
								deathMessage(cor + playerKit + cor + " morreu para um Zombie");
							} else if (chance == 1) {
								messageDeath = cor + playerKit + cor + " foi mordido por um Zombie e cortou o braço fora";
								deathMessage(cor + playerKit + cor + " foi mordido por um Zombie e cortou o braço fora");
							}
						} else if (e.getDamager().getType().name().contains("SKELETON")) {
							int chance = r.nextInt(1);
							if (chance == 0) {
								messageDeath = cor + playerKit + cor + " morreu para um Esqueleto";
								deathMessage(cor + playerKit + cor + " morreu para um Esqueleto");
							} else if (chance == 1) {
								messageDeath = cor + playerKit + cor + " tomou um headshot de um Esqueleto";
								deathMessage(cor + playerKit + cor + " tomou um headshot de um Esqueleto");
							}
						} else {
							messageDeath = cor + playerKit + " morreu para um " + cor + e.getDamager().getType().toString().replace("_", "").toLowerCase();
							deathMessage(cor + playerKit + " morreu para um " + cor + e.getDamager().getType().toString().replace("_", "").toLowerCase());
						}
					}
				}
			} else if (cause == DamageCause.ENTITY_EXPLOSION) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " achou que Creepers eram amigaveis";
					deathMessage(playerKit + cor + " achou que Creepers eram amigaveis");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " explodido por Creeper";
					deathMessage(playerKit + cor + " texplodido por Creeper");
				}
			} else if (cause == DamageCause.FALL) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " percebeu que não sabia voar";
					deathMessage(playerKit + cor + " percebeu que não sabia voar");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " morreu de altura";
					deathMessage(playerKit + cor + " morreu de altura");
				}
			} else if (cause == DamageCause.FIRE) {
				int chance = r.nextInt(3);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu para o fogo";
					deathMessage(playerKit + cor + " morreu para o fogo");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " esqueceu o número dos bombeiros";
					deathMessage(playerKit + cor + " esqueceu o número dos bombeiros");
				}
			} else if (cause == DamageCause.FIRE_TICK) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu para o fogo";
					deathMessage(playerKit + cor + " morreu para o fogo");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " esqueceu o número dos bombeiros";
					deathMessage(playerKit + cor + " esqueceu o número dos bombeiros");
				}
			} else if (cause == DamageCause.LAVA) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu pela lava";
					deathMessage(playerKit + cor + " morreu pela lava");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " achou que a lava era sukita";
					deathMessage(playerKit + cor + " achou que a lava era sukita");
				}
			} else if (cause == DamageCause.LIGHTNING) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu tostado por um raio";
					deathMessage(playerKit + cor + " morreu tostado por um raio");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " morreu eletrocutado por um raio";
					deathMessage(playerKit + cor + " morreu eletrocutado por um raio");
				}
			} else if (cause == DamageCause.MAGIC) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu por algum tipo de magia";
					deathMessage(playerKit + cor + " morreu por algum tipo de magia");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " morreu pelas mágicas de Harry Potter";
					deathMessage(playerKit + cor + " morreu pelas mágicas de Harry Potter");
				}
			} else if (cause == DamageCause.PROJECTILE) {
				if (p.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
					EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) p.getLastDamageCause();
					if (e.getDamager() instanceof Projectile) {
						Projectile projectile = (Projectile) e.getDamager();
						@SuppressWarnings("deprecation")
						ProjectileSource shooter = projectile.getShooter();
						String arma = projectile.getType().toString().toLowerCase();
						if (shooter instanceof Player) {
							Player killer = (Player) shooter;
							String kill = ChatColor.AQUA + killer.getName() + " (" + ChatColor.AQUA + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(killer)) + ChatColor.AQUA + ")";
							if (Main.plugin.kit.surprises.contains(killer.getUniqueId())) {
								kill = ChatColor.AQUA + killer.getName() + " (" + ChatColor.AQUA + "Surprise " + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(killer)) + ChatColor.AQUA + ")";
							}
							messageDeath = playerKit + cor + " morreu pela " + arma + cor + " de " + kill;
							deathMessage(playerKit + cor + " morreu pela " + arma + cor + " de " + kill);
						} else {
							messageDeath = playerKit + cor + " morreu";
							deathMessage(playerKit + cor + " morreu");
						}
					} else {
						messageDeath = playerKit + cor + " morreu";
						deathMessage(playerKit + cor + " morreu");
					}
				}
			} else if (cause == DamageCause.STARVATION) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu de fome";
					deathMessage(playerKit + cor + " morreu de fome");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " ficou sem comer por muito tempo";
					deathMessage(playerKit + cor + " ficou sem comer por muito tempo");
				}
			} else if (cause == DamageCause.SUFFOCATION) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu sufocado";
					deathMessage(playerKit + cor + " morreu sufocado");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " morreu por falta de ar para respirar";
					deathMessage(playerKit + cor + " morreu por falta de ar para respirar");
				}
			} else if (cause == DamageCause.SUICIDE) {
				messageDeath = playerKit + cor + " se suicidou";
				deathMessage(playerKit + cor + " se suicidou");
			} else if (cause == DamageCause.THORNS) {
				messageDeath = playerKit + cor + " não aguentou o dano da armadura de seu inimigo";
				deathMessage(playerKit + cor + " não aguentou o dano da armadura de seu inimigo");
			} else if (cause == DamageCause.WITHER) {
				int chance = r.nextInt(1);
				if (chance == 0) {
					messageDeath = playerKit + cor + " morreu de decomposição";
					deathMessage(playerKit + cor + " morreu de decomposição");
				} else if (chance == 1) {
					messageDeath = playerKit + cor + " morreu para um Wither";
					deathMessage(playerKit + cor + " morreu para um Wither");
				}
			} else if (cause == DamageCause.CUSTOM) {
				if (event.getDeathMessage().contains("desistiu")) {
					int chance = r.nextInt(1);
					if (chance == 0) {
						messageDeath = playerKit + cor + " se suicidou";
						deathMessage(playerKit + cor + " se suicidou");
					} else if (chance == 1) {
						messageDeath = playerKit + cor + " desistiu da vida";
						deathMessage(playerKit + cor + " desistiu da vida");
					}
				}
			} else {
				messageDeath = playerKit + cor + " faceleu";
				deathMessage(playerKit + cor + " faleceu");
			}
			event.setDeathMessage(null);
			Main.plugin.RemoverJogador(p);
			PlayerManager.checkWinner();
			if (!(p.hasPermission("PixelHG.youtuber.espectar") || p.hasPermission("PixelHG.vip.espectar") || p.hasPermission("PixelHG.admin"))) {
				if (Main.plugin.jogadores.size() <= 10) {
					p.kickPlayer("Parabéns, você ficou no top 10!\n " + messageDeath);
					deathMessage.put(p.getUniqueId(), "Você morreu!\n " + messageDeath);
					PlayerManager.dropItems(p, event.getDrops(), p.getLocation());
					event.getDrops().clear();
					return;
				}
				if (Main.TempoPartida < 300) {
					p.kickPlayer(ChatColor.AQUA + "Para renascer durante os primeiros 5 minutos da partida, compre vip em loja.mc-pixel.com\n " + messageDeath);
				} else {
					p.kickPlayer(ChatColor.AQUA + "Você morreu!\n " + messageDeath);
				}
				deathMessage.put(p.getUniqueId(), "Você morreu!\n " + messageDeath);
			} else if (p.hasPermission("PixelHG.admin")) {
				deathMessage.put(p.getUniqueId(), "Você morreu!\n " + messageDeath);
				Main.plugin.admin.setAdmin(p);
			} else {
				deathMessage.put(p.getUniqueId(), "Você morreu!\n " + messageDeath);
				Main.plugin.admin.setYoutuber(p);
				p.sendMessage(ChatColor.GREEN + "Para dar TP para algum player, Use /teleport [Player]");
			}
			PlayerManager.dropItems(p, event.getDrops(), p.getLocation());
			event.getDrops().clear();
			event.setDeathMessage(null);
		}
	}

	@EventHandler
	public void Respawn(PlayerRespawnEvent e) {
		if (e.getPlayer().hasPermission("PixelHG.admin")) {
			if (!(e.getPlayer().getKiller() instanceof Player))
				return;
			Main.plugin.admin.setAdmin(e.getPlayer());
			e.getPlayer().teleport(e.getPlayer().getKiller().getLocation());
		}
	}

	@EventHandler
	public void Death(PlayerDeathEvent e) {
		Player p = e.getEntity().getPlayer();
		if ((p.getKiller() != null) && ((p.getKiller() instanceof Player))) {
			Player k = p.getKiller();
			setStreaks(k);
		}
	}

	public static void deathMessage(String message) {
		Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "" + ChatColor.RESET + message);
		Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "" + ChatColor.RESET + ChatColor.GRAY + "Jogadores: " + ChatColor.AQUA + (Main.plugin.jogadores.size() - 1));
	}

	private static HashMap<UUID, Integer> kills = new HashMap<>();
	private static HashMap<UUID, Integer> ks = new HashMap<>();

	public static int getKs(Player p) {
		return ks.containsKey(p.getUniqueId()) ? ((Integer) ks.get(p.getUniqueId())).intValue() : 0;
	}

	public static void setStreaks(Player p) {
		ks.put(p.getUniqueId(), Integer.valueOf(ks.containsKey(p.getUniqueId()) ? ((Integer) ks.get(p.getUniqueId())).intValue() + 1 : 1));
	}

	public static int getKills(Player p) {
		return kills.containsKey(p.getUniqueId()) ? ((Integer) kills.get(p.getUniqueId())).intValue() : 0;
	}

	public static void setKills(Player p) {
		kills.put(p.getUniqueId(), Integer.valueOf(kills.containsKey(p.getUniqueId()) ? ((Integer) kills.get(p.getUniqueId())).intValue() + 1 : 1));
	}

}
