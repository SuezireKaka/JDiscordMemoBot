package www.disbot.jmemo.bot.command;

import java.util.Arrays;
import java.util.Map;

import www.disbot.jmemo.bot.model.data.GreetingVO;
import www.disbot.jmemo.bot.view.GreetingView;
import www.disbot.jmemo.bot.view.View;

public class GreetingCommand implements Command {
	public static final String USAGE = "(새 유저 등장시)";
	public static final String EXPLAIN = "새로 들어온 유저를 환영해요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{
			"effectiveName", "username", "avatarUrl", "isBot"};
	
	@Override
	public String[] getArgNameArray() {
		return Arrays.copyOf(ARGS_NAME_ARRAY, ARGS_NAME_ARRAY.length);
	}
	
	@Override
	public View command(Map<String, String> argsMap) throws Exception {
		GreetingVO result = GreetingVO.builder()
				.effectiveName(argsMap.get(ARGS_NAME_ARRAY[0]))
				.avatarUrl(argsMap.get(ARGS_NAME_ARRAY[2]))
				.isBot(Boolean.parseBoolean(argsMap.get(ARGS_NAME_ARRAY[3])))
				.build();
		
		return new GreetingView(result);
	}
	
	
}
