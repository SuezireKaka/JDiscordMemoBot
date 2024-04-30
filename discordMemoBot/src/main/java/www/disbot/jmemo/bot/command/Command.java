package www.disbot.jmemo.bot.command;

import java.util.Map;

import www.disbot.jmemo.bot.view.View;

public interface Command {
	public static final String PREFIX = "$";
	
	public String[] getArgNameArray();
	
	public View command(Map<String, String> argsMap) throws Exception;
}
