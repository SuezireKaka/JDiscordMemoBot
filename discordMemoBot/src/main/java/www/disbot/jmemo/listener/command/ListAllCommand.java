package www.disbot.jmemo.listener.command;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import www.disbot.jmemo.listener.command.listAll.ClassScanner;

public class ListAllCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "listAll";
	public static final String EXPLAIN = "모든 커맨드를 설명할게요";

	@Override
	public String[] getArgNameArray() {
		return new String[0];
	}
	@Override
	public String[] command(Map<String, String> argsMap) {
		ClassScanner scanner = new ClassScanner();
		
		Package pack = this.getClass().getPackage();

	   	List<Class<?>> commandClassList = scanner.findAllCommands(pack);
	   	
	   	List<String> result = new ArrayList<>();
	   	
	   	for (Class<?> c : commandClassList) {
	   		try {
				Field command = c.getDeclaredField("COMMAND");
				Field explain = c.getDeclaredField("EXPLAIN");
				result.add("%s : %s\n".formatted(command.get(null), explain.get(null)));
			}
	   		catch (Exception e) {
				e.printStackTrace();
			}
	   	}
	   	
	   	return result.toArray(new String[0]);
	}

}