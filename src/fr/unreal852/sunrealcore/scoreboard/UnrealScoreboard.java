package fr.unreal852.sunrealcore.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UnrealScoreboard
{
    private String m_name;
    private String m_displayName;
    private String m_criterion;

    private Scoreboard m_bukkitScoreboard;

    private Objective m_scoreboardObjective;

    private UnrealScoreboardRow[] m_rows = new UnrealScoreboardRow[15];

    private List<Player> m_viewers = new ArrayList<>();

    public UnrealScoreboard(String name)
    {
        this(name, name, "");
    }

    public UnrealScoreboard(String name, String displayName, String criterion)
    {
        m_name = name;
        m_displayName = displayName;
        m_criterion = criterion;
        if (Bukkit.getScoreboardManager() == null)
        {
            new NullPointerException("Bukkit.getScoreboardManager() is null").printStackTrace();
            return;
        }
        m_bukkitScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        m_scoreboardObjective = m_bukkitScoreboard.registerNewObjective(m_name, m_criterion, m_displayName, RenderType.INTEGER);
        m_scoreboardObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public String getName()
    {
        return m_name;
    }

    public String getDisplayName()
    {
        return m_displayName;
    }

    public String getCriterion()
    {
        return m_criterion;
    }

    public Scoreboard getBukkitScoreboard()
    {
        return m_bukkitScoreboard;
    }

    public Objective getScoreboardObjective()
    {
        return m_scoreboardObjective;
    }

    public Collection<Player> getViewers()
    {
        return m_viewers;
    }

    public void addViewer(Player player)
    {
        player.setScoreboard(m_bukkitScoreboard);
        m_viewers.add(player);
    }

    public void removeViewer(Player player)
    {
        if (!m_viewers.contains(player))
            return;
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        m_viewers.remove(player);
    }

    public UnrealScoreboardRow getRow(int row)
    {
        if (row < 0 || row > 14)
            return null;
        if (m_rows[row] == null)
            m_rows[row] = new UnrealScoreboardRow(this, row, "row@" + row);
        return m_rows[row];
    }

    public void setRow(int row, String text)
    {
        removeRow(row);
        UnrealScoreboardRow scRow = getRow(row);
        if (scRow == null)
            return;
        scRow.setValue(text);
    }

    public void removeRow(int row)
    {
        UnrealScoreboardRow scRow = m_rows[row];
        if (scRow == null)
            return;
        scRow.getTeam().removeEntry(ChatColor.values()[scRow.getRowIndex()] + "");
        scRow.getTeam().unregister();
        m_rows[row] = null;
    }
}
