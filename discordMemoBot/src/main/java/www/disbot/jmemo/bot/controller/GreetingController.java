package www.disbot.jmemo.bot.controller;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.GreetingCommand;
import www.disbot.jmemo.bot.view.View;

public class GreetingController {

	public View execute(User newUser) throws Exception {
		Map<String, String> packedArgs = new HashMap<>();
		
		Command command = new GreetingCommand();
		
		String[] requireArgsNameArray = command.getArgNameArray();
		
		packedArgs.put(requireArgsNameArray[0], newUser.getEffectiveName());
		packedArgs.put(requireArgsNameArray[1], newUser.getName());
		packedArgs.put(requireArgsNameArray[2], newUser.getEffectiveAvatarUrl());
		packedArgs.put(requireArgsNameArray[3], String.valueOf(newUser.isBot()));
		
		return command.command(packedArgs);
	}

}
