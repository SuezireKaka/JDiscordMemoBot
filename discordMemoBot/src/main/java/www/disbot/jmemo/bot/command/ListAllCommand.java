package www.disbot.jmemo.bot.command;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import www.disbot.jmemo.bot.command.common.ArgsPacker;
import www.disbot.jmemo.bot.command.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.command.listAll.ClassScanner;
import www.disbot.jmemo.bot.model.CommandVO;
import www.disbot.jmemo.bot.view.ListAllView;
import www.disbot.jmemo.bot.view.View;

public class ListAllCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "listAll";
	public static final String EXPLAIN = "모든 커맨드를 설명할게요";
	
	public static final String[] ARGS_NAME_ARRAY = new String[]{};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);

	@Override
	public String[] getArgNameArray() {
		return new String[0];
	}
	
	@Override
	public View command(Map<String, String> argsMap) throws ArgsNumberDismatchException {
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
	   	
	   	return new ListAllView(result);
	}

}
