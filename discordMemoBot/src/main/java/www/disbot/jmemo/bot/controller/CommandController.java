package www.disbot.jmemo.bot.controller;

import java.util.Map;

import www.disbot.jmemo.bot.command.HelloWorldCommand;
import www.disbot.jmemo.bot.command.ListAllCommand;
import www.disbot.jmemo.bot.command.common.ArgsPacker;
import www.disbot.jmemo.bot.view.View;

public class CommandController {
	
	public View execute(String key, String[] args) throws Exception {
		Map<String, String> packedArgs;
		
		
		if (key.equalsIgnoreCase(HelloWorldCommand.COMMAND)
				&& args.length == new HelloWorldCommand().getArgNameArray().length) {
			
            packedArgs = new ArgsPacker<HelloWorldCommand>()
            		.mapPack(new HelloWorldCommand(), args);
            
            return new HelloWorldCommand().command(packedArgs);
        }
		
		else if (key.equalsIgnoreCase(ListAllCommand.COMMAND)
        		&& args.length == ListAllCommand.ARGS_NAME_ARRAY.length) {
			
        	packedArgs = new ArgsPacker<ListAllCommand>()
                	.mapPack(new ListAllCommand(), args);

            return new ListAllCommand().command(packedArgs);
        }
		
		return null;
	}
}