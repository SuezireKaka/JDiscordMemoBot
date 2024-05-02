package www.disbot.jmemo.bot.controller;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.impl.GreetingCommand;
import www.disbot.jmemo.bot.view.View;

public class GreetingController {

	public View execute(User newUser) throws Exception {
		Map<String, String> packedArgs = new HashMap<>();
		
		Command command = new GreetingCommand();
		
		View result = command.command(newUser, packedArgs);
		result.init(null);
		
		return result;
	}

}
