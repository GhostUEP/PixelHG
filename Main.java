package me.ghost;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

import me.flame.utils.permissions.PermissionManager;
import me.ghost.Comandos.AdminCMD;
import me.ghost.Comandos.ChatCMD;
import me.ghost.Comandos.ClearDropsCMD;
import me.ghost.Comandos.ConnectCMD;
import me.ghost.Comandos.ContaCMD;
import me.ghost.Comandos.ExecCMD;
import me.ghost.Comandos.FFeastCMD;
import me.ghost.Comandos.FeastCMD;
import me.ghost.Comandos.ForceKitCMD;
import me.ghost.Comandos.GhostCMD;
import me.ghost.Comandos.HelpCMD;
import me.ghost.Comandos.InvseeCMD;
import me.ghost.Comandos.KitCMD;
import me.ghost.Comandos.MiniCMD;
import me.ghost.Comandos.PitCMD;
import me.ghost.Comandos.PixelsCMD;
import me.ghost.Comandos.PlayersCMD;
import me.ghost.Comandos.PvPCMD;
import me.ghost.Comandos.ReportCMD;
import me.ghost.Comandos.SkitCMD;
import me.ghost.Comandos.TeleportCMD;
import me.ghost.Comandos.TellCMD;
import me.ghost.Comandos.TempoCMD;
import me.ghost.Comandos.ToggleKitCMD;
import me.ghost.Comandos.TpallCMD;
import me.ghost.Constructor.BO3API;
import me.ghost.Constructor.GladiatorFight;
import me.ghost.Enums.Estagio;
import me.ghost.Eventos.TimeSecondEvent;
import me.ghost.Eventos.TimeSecondEvent2;
import me.ghost.Eventos.TimeSecondEvent3;
import me.ghost.Listener.DeathListener;
import me.ghost.Listener.EspectadorListener;
import me.ghost.Listener.ForceFieldListener;
import me.ghost.Listener.InvencibilidadeListener;
import me.ghost.Listener.PlayerListener;
import me.ghost.Listener.PreJogoListener;
import me.ghost.Listener.ServerListener;
import me.ghost.Listener.VitoriaListener;
import me.ghost.Manager.AbilityManager;
import me.ghost.Manager.KitInterface;
import me.ghost.Manager.KitManager;
import me.ghost.Manager.ServerManager;
import me.ghost.Tempo.PreJogo;
import me.ghost.Util.Feast;
import me.ghost.Util.Mode;
import me.ghost.Util.Pit;
import me.ghost.Util.Vanish;
import me.ghost.sql.MySQL;
import me.ghost.sql.SQLD;
import net.minecraft.util.com.google.common.io.ByteArrayDataInput;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements PluginMessageListener {

	public static Estagio est = Estagio.PREJOGO;
	public static int TempoPrejogo = 0;
	public static int TempoInvencibilidade = 0;
	public static int TempoPartida = 0;
	public static Main plugin;
	public Feast feastnovo;
	public PermissionManager PM;
	public boolean chat = true;
	public ArrayList<UUID> PlayersPreJogo = new ArrayList<>();
	public ArrayList<UUID> jogadores = new ArrayList<>();
	public ArrayList<String> kitsonoff = new ArrayList<>();
	public Vanish vanish = new Vanish(this);
	public Mode admin = new Mode(this);
	public KitManager kit;
	public GladiatorFight gladiatorfight;
	public ArrayList<BO3API> feast;
	public ArrayList<BO3API> bausfeast;
	public ArrayList<BO3API> win;
	public ArrayList<BO3API> pit;
	public Location feastlocation;
	public ArrayList<BO3API> minifeast1;
	public ArrayList<BO3API> gladiator;
	public ArrayList<UUID> espere = new ArrayList<>();
	public ArrayList<UUID> aleatorio = new ArrayList<>();
	public ArrayList<UUID> aleatorio2 = new ArrayList<>();
	public File confFile = new File(getDataFolder().getPath(), "config.yml");
	public FileConfiguration conf;
	public String IP;
	public String IP2;
	public ArrayList<UUID> invencivel = new ArrayList<>();
	public String host;
	public String port;
	public KitInterface kiti;
	public String db;
	public String user;
	public String pw;
	public static Pit pit2;
	public HashMap<UUID, String> fake = new HashMap<>();
	public HashMap<UUID, String> fakeoriginal = new HashMap<>();
	public ArrayList<Block> forcefieldblock = new ArrayList<>();
	public static Connection c;
	public Connection skits;
	static MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);
	public ArrayList<UUID> openloja1 = new ArrayList<>();
	public ArrayList<UUID> openloja2 = new ArrayList<>();
	public ArrayList<UUID> opensele1 = new ArrayList<>();
	public ArrayList<UUID> opensele2 = new ArrayList<>();
	public ArrayList<UUID> opensele3 = new ArrayList<>();
	public ArrayList<UUID> opensele4 = new ArrayList<>();
	public ArrayList<UUID> pagina2 = new ArrayList<>();
	public ArrayList<UUID> pagina2loja = new ArrayList<>();
	public HashMap<UUID, ArrayList<String>> seuskits = new HashMap<>();
	public ArrayList<UUID> pagina3loja = new ArrayList<>();
	public ArrayList<UUID> pagina3 = new ArrayList<>();
	public ArrayList<UUID> pagina4 = new ArrayList<>();
	public HashMap<UUID, Inventory> kitselector = new HashMap<>();
	public HashMap<UUID, Boolean> specs = new HashMap<>();
	public boolean pvp = true;
	public boolean dano = true;
	public static HashMap<String, List<String>> ignoring = new HashMap<String, List<String>>();
	public static ArrayList<String> muted = new ArrayList<String>();
	public static HashMap<String, String> lastMsg = new HashMap<String, String>();

	public void onLoad() {
		Bukkit.getServer().unloadWorld("world", false);
		deletarMundo(new File("world"));
		Bukkit.getServer().getConsoleSender().sendMessage("Mundo apagado.");
		if (!new File(getDataFolder(), "bausfeast.bo3").exists()) {
			saveResource("bausfeast.bo3", false);
		}
		if (!new File(getDataFolder(), "casa.bo3").exists()) {
			saveResource("casa.bo3", false);
		}
		if (!new File(getDataFolder(), "casa3333.bo3").exists()) {
			saveResource("casa3333.bo3", false);
		}
		if (!new File(getDataFolder(), "feast.bo3").exists()) {
			saveResource("feast.bo3", false);
		}
		if (!new File(getDataFolder(), "feast3333.bo3").exists()) {
			saveResource("feast3333.bo3", false);
		}
		if (!new File(getDataFolder(), "gladiator.bo3").exists()) {
			saveResource("gladiator.bo3", false);
		}
		if (!new File(getDataFolder(), "minifeast1.bo3").exists()) {
			saveResource("minifeast1.bo3", false);
		}
		if (!new File(getDataFolder(), "pit.bo3").exists()) {
			saveResource("pit.bo3", false);
		}
	}

	public static void deletarMundo(File deletar) {
		if (deletar.isDirectory()) {
			String[] children = deletar.list();
			for (int i = 0; i < children.length; i++) {
				deletarMundo(new File(deletar, children[i]));

			}
		}
		deletar.delete();
	}

	public synchronized static boolean playerDataContainsPlayer(String p) {
		try {
			PreparedStatement sql = c.prepareStatement("SELECT * FROM `player_pixels` WHERE player=?;");
			sql.setString(1, p);

			ResultSet rs = sql.executeQuery();
			boolean containsPlayer = rs.next();

			sql.close();
			rs.close();

			return containsPlayer;
		} catch (Exception e) {

			return false;
		}
	}

	public synchronized static boolean playerDataContainsPlayerStatus(String p) {
		try {
			PreparedStatement sql = c.prepareStatement("SELECT * FROM `player_status` WHERE player=?;");
			sql.setString(1, p);

			ResultSet rs = sql.executeQuery();
			boolean containsPlayer = rs.next();

			sql.close();
			rs.close();

			return containsPlayer;
		} catch (Exception e) {

			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public void onEnable() {
		plugin = this;
		kit = new KitManager();
		PreJogo.ComeçarPreJogo(301);
		RegistrarEventos();
		RegistrarComandos();
		AbilityManager hm = new AbilityManager(this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		hm.registerAbilityListeners();
		Bukkit.getServer().getWorld("world").setSpawnLocation(0, getServer().getWorlds().get(0).getHighestBlockYAt(0, 0), 0);
		feast = loadBO3("feast");
		bausfeast = loadBO3("bausfeast");
		win = loadBO3("casa");
		pit = loadBO3("pit");
		gladiator = loadBO3("gladiator");
		minifeast1 = loadBO3("minifeast1");
		if (!confFile.exists())
			saveResource("config.yml", false);
		conf = YamlConfiguration.loadConfiguration(confFile);
		IP = conf.getString("IP");
		IP2 = conf.getString("IP2");

		host = conf.getString("host");
		port = conf.getString("port");
		db = conf.getString("db");
		user = conf.getString("user");
		pw = conf.getString("pw");
		pit2 = new Pit(Main.plugin);

		ServerManager.gerarBorda();

		ItemStack Resultado = new ItemStack(Material.MUSHROOM_SOUP, 1);

		ItemMeta Cactos = Resultado.getItemMeta();
		Resultado.setItemMeta(Cactos);
		ShapelessRecipe CraftCactos = new ShapelessRecipe(Resultado);
		CraftCactos.addIngredient(1, Material.CACTUS);
		CraftCactos.addIngredient(1, Material.BOWL);
		Bukkit.getServer().addRecipe(CraftCactos);

		ItemMeta Cocoa = Resultado.getItemMeta();
		Resultado.setItemMeta(Cocoa);
		ShapelessRecipe CraftCocoa = new ShapelessRecipe(Resultado);
		CraftCocoa.addIngredient(1, Material.INK_SACK, 3);
		CraftCocoa.addIngredient(1, Material.BOWL);
		Bukkit.getServer().addRecipe(CraftCocoa);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			public void run() {
				Bukkit.getPluginManager().callEvent(new TimeSecondEvent());
			}
		}, 1, 20L);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			public void run() {
				Bukkit.getPluginManager().callEvent(new TimeSecondEvent2());
			}
		}, 1, 10L);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			public void run() {
				Bukkit.getPluginManager().callEvent(new TimeSecondEvent3());
			}
		}, 1, 20 * 180);
		try {
			skits = trySQLConnection();
			Main.plugin.getLogger().info("SQL CARREGADA");
		} catch (Exception e) {
		}
		try {
			c = SQL.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("static-method")
	public synchronized Connection trySQLConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String conn = "jdbc:mysql://localhost:3306/freehunger";
			return DriverManager.getConnection(conn, "root", "7JiO2_._Q6NS");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void getCount(Player player, String server) {

		if (server == null) {
			server = "ALL";
		}
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
		out.writeUTF("PlayerCount");
		out.writeUTF(server);

		player.sendPluginMessage(this, "BungeeCord", out.toByteArray());

	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}

		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();

		if (subchannel.equals("PlayerCount")) {

			String server = in.readUTF();
			int playerCount = in.readInt();
			String players = "players";
			if (playerCount == 1) {
				players = "player";
			}

			player.sendMessage(ChatColor.RED + "O Servidor " + server.replace("ALL", "Global") + " tem " + playerCount + " " + players + ".");

		} else if (subchannel.equals("Testando")) {
			String causa = in.readUTF();
			player.sendMessage(ChatColor.RED + "Report enviado!");
			Bukkit.broadcastMessage(ChatColor.RED + "teste " + causa);
		}

	}

	public void sendAllSevers(String message, Player p) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Message");
			out.writeUTF("ALL");
			out.writeUTF(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
	}

	@SuppressWarnings("static-access")
	public void onDisable() {
		c = SQL.closeConnection(c);
		skits = null;
	}

	public void resetPlayersPreJogo(Player p) {
		PlayersPreJogo.remove(p.getUniqueId());
	}

	public void RegistrarEventos() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new PreJogoListener(), Main.plugin);
		pm.registerEvents(new PlayerListener(), Main.plugin);
		pm.registerEvents(new InvencibilidadeListener(), Main.plugin);
		pm.registerEvents(new DeathListener(), Main.plugin);
		pm.registerEvents(new VitoriaListener(), Main.plugin);
		pm.registerEvents(new EspectadorListener(), Main.plugin);
		pm.registerEvents(new ServerListener(), Main.plugin);
		pm.registerEvents(new ForceFieldListener(), Main.plugin);

	}

	public void RegistrarComandos() {
		Main.plugin.getCommand("kit").setExecutor(new KitCMD());
		Main.plugin.getCommand("checkwinner").setExecutor(new KitCMD());
		Main.plugin.getCommand("admin").setExecutor(new AdminCMD());
		Main.plugin.getCommand("tempo").setExecutor(new TempoCMD());
		Main.plugin.getCommand("ffeast").setExecutor(new FFeastCMD());
		Main.plugin.getCommand("feast").setExecutor(new FeastCMD());
		Main.plugin.getCommand("pit").setExecutor(new PitCMD());
		Main.plugin.getCommand("mini").setExecutor(new MiniCMD());
		Main.plugin.getCommand("exec").setExecutor(new ExecCMD());
		Main.plugin.getCommand("execall").setExecutor(new ExecCMD());
		Main.plugin.getCommand("chat").setExecutor(new ChatCMD());
		Main.plugin.getCommand("invsee").setExecutor(new InvseeCMD());
		Main.plugin.getCommand("ghost").setExecutor(new GhostCMD());
		Main.plugin.getCommand("pixels").setExecutor(new PixelsCMD());
		Main.plugin.getCommand("forcekit").setExecutor(new ForceKitCMD());
		Main.plugin.getCommand("cleardrops").setExecutor(new ClearDropsCMD());
		Main.plugin.getCommand("togglekit").setExecutor(new ToggleKitCMD());
		Main.plugin.getCommand("skit").setExecutor(new SkitCMD());
		Main.plugin.getCommand("pvp").setExecutor(new PvPCMD());
		Main.plugin.getCommand("dano").setExecutor(new PvPCMD());
		Main.plugin.getCommand("help").setExecutor(new HelpCMD());
		Main.plugin.getCommand("conta").setExecutor(new ContaCMD());
		Main.plugin.getCommand("tell").setExecutor(new TellCMD());
		Main.plugin.getCommand("pm").setExecutor(new TellCMD());
		Main.plugin.getCommand("msg").setExecutor(new TellCMD());
		Main.plugin.getCommand("ignore").setExecutor(new TellCMD());
		Main.plugin.getCommand("w").setExecutor(new TellCMD());
		Main.plugin.getCommand("r").setExecutor(new TellCMD());
		Main.plugin.getCommand("reply").setExecutor(new TellCMD());
		Main.plugin.getCommand("tpall").setExecutor(new TpallCMD());
		Main.plugin.getCommand("teleport").setExecutor(new TeleportCMD());
		Main.plugin.getCommand("report").setExecutor(new ReportCMD());
		Main.plugin.getCommand("players").setExecutor(new PlayersCMD());
		Main.plugin.getCommand("connect").setExecutor(new ConnectCMD());
	}

	public boolean NaoEstaJogando(Player p) {
		return !jogadores.contains(p.getUniqueId());
	}

	public void RemoverJogador(Player p) {
		jogadores.remove(p.getUniqueId());
	}

	public ArrayList<BO3API> loadBO3(String path) {
		File file = new File(getDataFolder(), path + ".bo3");
		if (!file.exists()) {
			getLogger().log(Level.SEVERE, "Nao encontrado o arquivo " + path + ".bo3");
			return new ArrayList<>();
		}
		ArrayList<BO3API> blocks = new ArrayList<>();
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (!line.startsWith("Block"))
					continue;
				String[] bo3 = line.replace("Block(", "").replace(")", "").split(",");
				int x = Integer.valueOf(bo3[0]);
				int y = Integer.valueOf(bo3[1]);
				int z = Integer.valueOf(bo3[2]);
				String mat = bo3[3];
				byte data = (byte) 0;
				if (bo3[3].contains(":")) {
					String[] material = bo3[3].split(":");
					mat = material[0];
					data = Byte.valueOf(material[1]);
				}
				blocks.add(new BO3API(x, y, z, Material.valueOf(mat), data));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return blocks;
	}

	public void addContador(Player p) {
		PlayersPreJogo.add(p.getUniqueId());
	}

	public void resetContador(Player p) {
		PlayersPreJogo.remove(p.getUniqueId());
	}

	public void MandarParaAdmins(String hack) {
		for (World w : Bukkit.getWorlds()) {
			for (Player mods : w.getPlayers()) {
				if (mods.hasPermission("PixelHG.admin")) {
					mods.sendMessage(hack);
				}
			}
		}
	}

	// String msg = ChatColor.RED + "" + ChatColor.BOLD + "REPORT " +
	// ChatColor.RESET + ChatColor.RED + Main.plugin.IP2 + " " + p2 + " " +
	// report;

	public void MandarParaSpecs(String hack) {
		for (World w : Bukkit.getWorlds()) {
			for (Player mods : w.getPlayers()) {
				if (Main.plugin.admin.isSpectating(mods)) {
					mods.sendMessage(hack);
				}
			}
		}
	}

}
