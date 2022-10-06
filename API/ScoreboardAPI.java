package me.ghost.API;

import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import me.ghost.Main;
import net.minecraft.util.com.mojang.authlib.GameProfile;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ScoreboardAPI {

	private Scoreboard scoreboard;

	private String title;
	private Map<String, Integer> scores;
	private List<Team> teams;

	public ScoreboardAPI(String title) {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.title = title;
		this.scores = Maps.newLinkedHashMap();
		this.teams = Lists.newArrayList();
	}

	public void blankLine() {
		add(" ");
	}

	public void add(String text) {
		add(text, null);
	}

	public void add(String text, Integer score) {
		Preconditions.checkArgument(text.length() < 48, "text cannot be over 48 characters in length");
		text = fixDuplicates(text);
		scores.put(text, score);
	}

	private String fixDuplicates(String text) {
		while (scores.containsKey(text))
			text += "�r";
		if (text.length() > 48)
			text = text.substring(0, 47);
		return text;
	}

	private Map.Entry<Team, String> createTeam(String text) {
		String result = "";
		if (text.length() <= 16)
			return new AbstractMap.SimpleEntry<>(null, text);
		Team team = scoreboard.registerNewTeam("text-" + scoreboard.getTeams().size());
		Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
		team.setPrefix(iterator.next());
		result = iterator.next();
		if (text.length() > 32)
			team.setSuffix(iterator.next());
		teams.add(team);
		return new AbstractMap.SimpleEntry<>(team, result);
	}

	@SuppressWarnings("deprecation")
	public void build() {
		Objective obj = scoreboard.registerNewObjective((title.length() > 16 ? title.substring(0, 15) : title), "dummy");
		obj.setDisplayName(title);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		int index = scores.size();

		for (Map.Entry<String, Integer> text : scores.entrySet()) {
			Map.Entry<Team, String> team = createTeam(text.getKey());
			Integer score = text.getValue() != null ? text.getValue() : index;
			OfflinePlayer player = ((CraftServer) Bukkit.getServer()).getOfflinePlayer(new GameProfile(UUID.randomUUID(), team.getValue()));
			if (team.getKey() != null)
				team.getKey().addPlayer(player);
			obj.getScore(player).setScore(score);
			index -= 1;

		}
	}

	public void reset() {
		title = null;
		scores.clear();
		for (Team t : teams)
			t.unregister();
		teams.clear();
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void send(Player... players) {
		for (Player p : players)
			p.setScoreboard(scoreboard);
	}

	public static void SetSemPlayers(Player p) {
		int millis = Main.TempoPrejogo * 1000;
		SimpleDateFormat df = new SimpleDateFormat("mm:ss");
		String time = df.format(Integer.valueOf(millis));
		ScoreboardAPI score = new ScoreboardAPI(ChatColor.AQUA + "" + ChatColor.BOLD + "PIXELHG");
		score.add(ChatColor.WHITE + "Iniciando em: " + ChatColor.RED + time);
		score.blankLine();
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "Kit: " + ChatColor.WHITE + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)));
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "Players: " + ChatColor.WHITE + Main.plugin.PlayersPreJogo.size() + "/" + Bukkit.getMaxPlayers());
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "IP: " + ChatColor.WHITE + Main.plugin.IP2);
		score.add(ChatColor.GRAY + "");
		score.add(ChatColor.GRAY + "   hg.mc-pixel.com");
		score.build();
		score.send(p);

	}

	public static void SetSemPlayers2(Player p) {
		ScoreboardAPI score = new ScoreboardAPI(ChatColor.AQUA + "" + ChatColor.BOLD + "PIXELHG");
		score.add(ChatColor.WHITE + "    Sem Players");
		score.blankLine();
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "Kit: " + ChatColor.WHITE + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)));
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "Players: " + ChatColor.WHITE + Main.plugin.PlayersPreJogo.size() + "/" + Bukkit.getMaxPlayers());
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "IP: " + ChatColor.WHITE + Main.plugin.IP2);
		score.add(ChatColor.GRAY + "");
		score.add(ChatColor.GRAY + "   hg.mc-pixel.com");
		score.build();
		score.send(p);

	}

	public static void Invencibilidade(Player p) {
		int millis = Main.TempoInvencibilidade * 1000;
		SimpleDateFormat df = new SimpleDateFormat("mm:ss");
		String time = df.format(Integer.valueOf(millis));
		ScoreboardAPI score = new ScoreboardAPI(ChatColor.AQUA + "" + ChatColor.BOLD + "PIXELHG");
		score.add(ChatColor.WHITE + "Invencibilidade: " + ChatColor.RED + time);
		score.blankLine();
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "Kit: " + ChatColor.WHITE + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)));
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "Players: " + ChatColor.WHITE + Main.plugin.jogadores.size());
		score.add(ChatColor.GRAY + "" + ChatColor.BOLD + ">" + ChatColor.RESET + ChatColor.AQUA + "IP: " + ChatColor.WHITE + Main.plugin.IP2);
		score.add(ChatColor.GRAY + "");
		score.add(ChatColor.GRAY + "   hg.mc-pixel.com");
		score.build();
		score.send(p);

	}

}