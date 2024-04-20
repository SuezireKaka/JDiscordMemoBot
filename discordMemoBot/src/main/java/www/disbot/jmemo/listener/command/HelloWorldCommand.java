package www.disbot.jmemo.listener.command;

import java.util.Map;

public class HelloWorldCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "hello";
	public static final String EXPLAIN = "봇이 헬로월드를 시전해요";
	
	@Override
	public String[] getArgNameArray() {
		return new String[0];
	}
	@Override
	public String[] command(Map<String, String> argsMap) {
		return new String[]{"Hello, world!"};
	}
	
}
