package www.disbot.jmemo.listener.command.common;

import java.util.HashMap;
import java.util.Map;

import www.disbot.jmemo.listener.command.Command;
import www.disbot.jmemo.listener.command.exception.ArgsNumberDismatchException;

public class ArgsPacker<C extends Command> {
	public static final String SEPERATOR = "/";
	
	public Map<String, String> pack(C cmd, String[] args) throws ArgsNumberDismatchException {
		Map<String, String> result = new HashMap<>();
		
		String[] argNamesArray = cmd.getArgNameArray();
		
		if (args.length == argNamesArray.length) {
			for (int i = 0; i < args.length; i++) {
				result.put(argNamesArray[i], args[i]);
			}
			
			return result;
		}
		else {
			throw new ArgsNumberDismatchException(argNamesArray, args);
		}
	}
}
