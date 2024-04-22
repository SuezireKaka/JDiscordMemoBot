package www.disbot.jmemo.bot.command;

import java.util.Map;

import www.disbot.jmemo.bot.command.common.ArgsPacker;
import www.disbot.jmemo.bot.command.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.model.HelloWorldVO;
import www.disbot.jmemo.bot.view.HelloWorldView;
import www.disbot.jmemo.bot.view.View;

public class HelloWorldCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "hello";
	public static final String EXPLAIN = "봇이 헬로월드를 시전해요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	@Override
	public String[] getArgNameArray() {
		return ARGS_NAME_ARRAY.clone();
	}
	
	@Override
	public View command(Map<String, String> argsMap) throws Exception {
		if (argsMap.size() != ARGS_NAME_ARRAY.length) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		HelloWorldVO result = new HelloWorldVO("Hello, world!");
		
		return new HelloWorldView(result);
	}	
}
