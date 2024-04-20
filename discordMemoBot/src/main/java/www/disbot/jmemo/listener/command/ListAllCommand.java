package www.disbot.jmemo.listener.command;

import java.lang.reflect.Field;
import java.util.List;

import www.disbot.jmemo.listener.command.listAll.ClassScanner;

public class ListAllCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "listAll";
	public static final String EXPLAIN = "모든 커맨드를 설명할게요";

	public String command(String[] args) {
		if (args.length == 0) {
			ClassScanner scanner = new ClassScanner();
			
			Package pack = this.getClass().getPackage();

		   	List<Class<?>> commandClassList = scanner.findAllCommands(pack);
		   	
		   	String result = "";
		   	
		   	for (Class<?> c : commandClassList) {
		   		try {
					Field command = c.getDeclaredField("COMMAND");
					Field explain = c.getDeclaredField("EXPLAIN");
					result += "%s : %s\n".formatted(command.get(null), explain.get(null));
				}
		   		catch (Exception e) {
					e.printStackTrace();
					result += "";
				}
		   	}
		   	
		   	return result;
		}
		return "";
	}

}
