package www.disbot.jmemo.bot.command;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.view.View;

public interface Command {
	public static final String PREFIX = "$";
	public static final String OR = "|";
	
	public static final int DISCORD_MAX_LENGTH = 2000;
	
	public String[] getArgsNameArray();
	
	public View command(User user, Map<String, String> argsMap) throws Exception;
}
