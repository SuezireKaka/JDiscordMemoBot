package www.disbot.jmemo.bot.command.impl;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.controller.args.ArgsPacker;
import www.disbot.jmemo.bot.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.model.data.HelloWorldVO;
import www.disbot.jmemo.bot.parser.DiscordContents;
import www.disbot.jmemo.bot.parser.impl.HelloWorldParser;
import www.disbot.jmemo.bot.view.View;
import www.disbot.jmemo.bot.view.impl.CommandResultView;

public class HelloWorldCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "hello";
	public static final String EXPLAIN = "봇이 헬로월드를 시전해요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	@Override
	public String[] getArgsNameArray() {
		return ARGS_NAME_ARRAY.clone();
	}
	
	@Override
	public View command(User user, Map<String, String> argsMap) throws Exception {
		if (argsMap.size() != ARGS_NAME_ARRAY.length) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		HelloWorldVO result = new HelloWorldVO("Hello, world!");
		
		DiscordContents contents = new DiscordContents(new HelloWorldParser(result));
	   	
		contents.parse();
		
	   	return CommandResultView.builder()
	   			.title(ListAllCommand.USAGE)
	   			.contents(contents)
	   			.build();
	}
}
