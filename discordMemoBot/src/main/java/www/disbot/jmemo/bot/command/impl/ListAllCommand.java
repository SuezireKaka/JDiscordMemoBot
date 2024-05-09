package www.disbot.jmemo.bot.command.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.impl.listAll.ClassScanner;
import www.disbot.jmemo.bot.controller.args.ArgsPacker;
import www.disbot.jmemo.bot.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.model.data.CommandVO;
import www.disbot.jmemo.bot.parser.DiscordContents;
import www.disbot.jmemo.bot.parser.impl.CommandListParser;
import www.disbot.jmemo.bot.view.View;
import www.disbot.jmemo.bot.view.impl.CommandResultView;

public class ListAllCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "listAll";
	public static final String EXPLAIN = "모든 커맨드를 설명할게요";
	
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
		
		ClassScanner scanner = new ClassScanner();
		
		Package pack = this.getClass().getPackage();

	   	List<Class<?>> commandClassList = scanner.findAllCommands(pack);
	   	
	   	List<CommandVO> result = new ArrayList<>();
	   	
	   	for (Class<?> c : commandClassList) {
	   		try {
				Field usage = c.getDeclaredField("USAGE");
				Field explain = c.getDeclaredField("EXPLAIN");
				result.add(new CommandVO((String) usage.get(null), (String) explain.get(null)));
			}
	   		catch (Exception e) {
				e.printStackTrace();
			}
	   	}
	   	
	   	DiscordContents contents = new DiscordContents(new CommandListParser(result));
	   	
	   	contents.parse();
	   	
	   	return CommandResultView.builder()
	   			.title(ListAllCommand.USAGE)
	   			.contents(contents)
	   			.build();
	}

}
