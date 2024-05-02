package www.disbot.jmemo.bot.command;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.view.View;

public interface Command {
	public static final String PREFIX = "$";
	
	public String[] getArgNameArray();
	
	public View command(User user, Map<String, String> argsMap) throws Exception;
}
