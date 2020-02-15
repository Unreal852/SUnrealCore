package fr.unreal852.sunrealcore.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

import static fr.unreal852.sunrealcore.utils.StringUtils.substringWithColor;

//Todo: Unregister Team et recreate it for texttop
public class UnrealScoreboardRow
{
    private UnrealScoreboard m_scoreboard;
    private Team             m_team;
    private String           m_prefix;
    private String           m_text;
    private String           m_suffix;
    private int              m_row;

    public UnrealScoreboardRow(UnrealScoreboard scoreboard, int row, String text)
    {
        m_scoreboard = scoreboard;
        m_row = row;
        m_text = text;
        m_team = scoreboard.getBukkitScoreboard().registerNewTeam(scoreboard.getName() + ".row." + row);
        m_team.addEntry(ChatColor.values()[row] + "");
        scoreboard.getScoreboardObjective().getScore(ChatColor.values()[row] + "").setScore(row);
    }

    public UnrealScoreboard getScoreboard()
    {
        return m_scoreboard;
    }

    public Team getTeam()
    {
        return m_team;
    }

    public int getRowIndex()
    {
        return m_row;
    }

    public String getValue()
    {
        return m_prefix + m_text + m_suffix;
    }

    public String getPrefix()
    {
        return m_prefix;
    }

    public String getText()
    {
        return m_text;
    }

    public String getSuffix()
    {
        return m_suffix;
    }

    public void setPrefix(String value)
    {
        if (value.length() > 16)
            return;
        m_team.setPrefix(value);
        m_prefix = value;
    }

    public void setSuffix(String value)
    {
        if (value.length() > 16)
            return;
        m_team.setSuffix(value);
        m_suffix = value;
    }

    public void setValue(String value)
    {
        if (value.length() <= 16)
        {
            setPrefix(value);
            setSuffix("");
        }
        else if (value.length() <= 32)
        {
            setPrefix(value.substring(0, 16));
            setSuffix(substringWithColor(value, 16));
        }
        else
            new IllegalArgumentException("Too long value ! Max 32 characters, value was " + value.length() + " !").printStackTrace();
    }
}
