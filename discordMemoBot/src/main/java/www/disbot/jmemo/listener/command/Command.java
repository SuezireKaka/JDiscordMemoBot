package www.disbot.jmemo.listener.command;

import java.util.Map;

public interface Command {
	public static final String PREFIX = "!";
	
	public String[] getArgNameArray();
	
	public String[] command(Map<String, String> argsMap);
}
