package www.disbot.jmemo.bot.command.impl;

import java.util.Arrays;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.model.data.GreetingVO;
import www.disbot.jmemo.bot.view.View;
import www.disbot.jmemo.bot.view.impl.GreetingView;

public class GreetingCommand implements Command {
	public static final String USAGE = "(새 유저 등장시)";
	public static final String EXPLAIN = "새로 들어온 유저를 환영해요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{};
	
	@Override
	public String[] getArgNameArray() {
		return Arrays.copyOf(ARGS_NAME_ARRAY, ARGS_NAME_ARRAY.length);
	}
	
	@Override
	public View command(User user, Map<String, String> argsMap) throws Exception {
		GreetingVO result = GreetingVO.builder()
				.effectiveName(user.getEffectiveName())
				.avatarUrl(user.getAvatarUrl())
				.isBot(user.isBot())
				.build();
		
		return new GreetingView(result);
	}
	
	
}
