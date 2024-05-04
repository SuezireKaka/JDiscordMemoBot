package www.disbot.jmemo.bot.controller;

import java.util.Map;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.api.WebClientRequestStrategy;
import www.disbot.jmemo.bot.command.impl.HelloWorldCommand;
import www.disbot.jmemo.bot.command.impl.ListAllCommand;
import www.disbot.jmemo.bot.command.impl.SignUpCommand;
import www.disbot.jmemo.bot.controller.args.ArgsPacker;
import www.disbot.jmemo.bot.exception.NoCommandFoundException;
import www.disbot.jmemo.bot.view.View;

public class CommandController {
	
	public View execute(User user, String key, String[] args, WebClientRequestStrategy requester) throws Exception {
		Map<String, String> packedArgs;
		
		View result = null;
		
		if (key.equalsIgnoreCase(HelloWorldCommand.COMMAND)
				&& args.length == new HelloWorldCommand().getArgNameArray().length) {
			
            packedArgs = new ArgsPacker<HelloWorldCommand>()
            		.mapPack(new HelloWorldCommand(), args);
            
            result = new HelloWorldCommand().command(user, packedArgs);
            result.init(HelloWorldCommand.class);
        }
		
		else if (key.equalsIgnoreCase(ListAllCommand.COMMAND)
        		&& args.length == new ListAllCommand().getArgNameArray().length) {
			
        	packedArgs = new ArgsPacker<ListAllCommand>()
                	.mapPack(new ListAllCommand(), args);

        	result = new ListAllCommand().command(user, packedArgs);
        	result.init(ListAllCommand.class);
        }
		
		else if (key.equalsIgnoreCase(SignUpCommand.COMMAND)
        		&& args.length == new SignUpCommand(null).getArgNameArray().length) {
			
        	packedArgs = new ArgsPacker<SignUpCommand>()
                	.mapPack(new SignUpCommand(null), args);

        	result = new SignUpCommand(requester).command(user, packedArgs);
        	result.init(SignUpCommand.class);
        }
		
		else if (key.startsWith(Command.PREFIX) && ! key.equals(Command.PREFIX)) {
			throw new NoCommandFoundException(key, args);
		}
		
		return result;
	}
}
