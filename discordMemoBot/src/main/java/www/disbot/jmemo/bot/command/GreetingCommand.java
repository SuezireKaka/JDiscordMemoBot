package www.disbot.jmemo.bot.command;

import java.util.Map;

import www.disbot.jmemo.bot.command.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.view.View;

public class GreetingCommand implements Command {
	public static final String USAGE = "(새 유저 등장시)";
	public static final String EXPLAIN = "새로 들어온 유저를 환영해요";
	
	@Override
	public String[] getArgNameArray() {
		return new String[0];
	}
	
	@Override
	public View command(Map<String, String> argsMap) throws ArgsNumberDismatchException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
