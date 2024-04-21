package www.disbot.jmemo.bot.command.common;

import java.util.HashMap;
import java.util.Map;

import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.exception.ArgsNumberDismatchException;

public class ArgsPacker<C extends Command> {
	public static final String SEPERATOR = "/";
	public static final String ARG_WRAPPER = "{%s}";
	
	public static String usagePack(String key, String[] args) {
		String result = key;
		
		for (String arg : args) {
			result += SEPERATOR + ARG_WRAPPER.formatted(arg);
		}
		
		return result;
	}
	
	public Map<String, String> mapPack(C cmd, String[] args) throws ArgsNumberDismatchException {
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
