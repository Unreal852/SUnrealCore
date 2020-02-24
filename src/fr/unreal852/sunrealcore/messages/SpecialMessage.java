package fr.unreal852.sunrealcore.messages;

public class SpecialMessage
{
    private static final String END_TEXT = "]}";

    private StringBuilder m_builder = new StringBuilder();
    private boolean       m_closed  = false;

    public SpecialMessage()
    {
        this("");
    }

    public SpecialMessage(String text)
    {
        m_builder.append("{\"text\":\"" + "" + "\"" + ",\"extra\":[" + "{\"text\":\"").append(text).append("\"}");
    }

    public boolean isClosed()
    {
        return m_closed;
    }

    public void addText(String text)
    {
        if (!m_closed)
            m_builder.append(",{\"text\":\" ").append(text).append("\"}");
    }

    public void addHoverableText(String text, String hoverText)
    {
        if (!m_closed)
            m_builder.append(",{\"text\":\"").append(text).append("\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"").append(hoverText).append("\"}}");
    }

    public void addClickableText(String text, SpecialMessageAction action, String actionArgument)
    {
        if (!m_closed)
            m_builder.append(",{\"text\":\"").append(text).append("\",\"clickEvent\":{\"action\":\"").append(action.getAction()).append("\",\"value\":\"").append(actionArgument).append("\"}}");
    }

    public void addClickableAndHoverableText(String text, String hoverText, SpecialMessageAction action, String actionArgument)
    {
        if (!m_closed)
            m_builder.append(",{\"text\":\"").append(text).append("\",\"clickEvent\":{\"action\":\"").append(action.getAction()).append("\",\"value\":\"").append(actionArgument).append("\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"").append(hoverText).append("\"}}");
    }

    private void close()
    {
        m_closed = true;
        m_builder.append("]}");
    }

    @Override
    public String toString()
    {
        return m_builder.toString();
    }
}
