package fr.unreal852.sunrealcore.messages;

public enum SpecialMessageAction
{
    RUN_COMMAND("run_command"),
    OPEN_URL("open_url"),
    OPEN_FILE("open_file"),
    SUGGEST_COMMAND("suggest_command");

    private String m_action;

    SpecialMessageAction(String action)
    {
        m_action = action;
    }

    public String getAction()
    {
        return m_action;
    }
}
