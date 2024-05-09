package www.disbot.jmemo.bot.controller.args;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.model.structure.Pair;

public abstract class ArgsSaver {
	private static Map<String, Pair<String, List<String>>> savedAsyncArgsMap = new HashMap<>();
	
	public static void init(User user, String command) {
		String id = user.getId();
		
		if (! savedAsyncArgsMap.containsKey(id)) {
			savedAsyncArgsMap.put(id, new Pair<>(command, new ArrayList<>()));
		}
	}
	
	public static void save(User user, String command, String asyncArg) {
		String id = user.getId();
		
		if (! savedAsyncArgsMap.containsKey(id)) {
			init(user, command);
		}
		
		if (asyncArg.length() > 0) {
			savedAsyncArgsMap.get(id).getSecond().add(asyncArg);
		}
	}
	
	public static String getCommandKeyOf(User user) {
		return savedAsyncArgsMap.get(user.getId()).getFirst();
	}
	
	public static String[] getSavedArgsOf(User user) {
		if (! hasSaved(user)) {
			return new String[]{};
		}
		return savedAsyncArgsMap.get(user.getId()).getSecond().toArray(new String[]{});
	}
	
	public static boolean hasSaved(User user) {
		return savedAsyncArgsMap.containsKey(user.getId());
	}
}
