package www.disbot.jmemo.bot.command.impl;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.api.security.model.SignUpDTO;
import www.disbot.jmemo.api.security.model.SignUpResultDTO;
import www.disbot.jmemo.bot.command.ApiCommand;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.api.RequestStrategy;
import www.disbot.jmemo.bot.controller.args.ArgsPacker;
import www.disbot.jmemo.bot.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.parser.DiscordContents;
import www.disbot.jmemo.bot.parser.impl.SignUpParser;
import www.disbot.jmemo.bot.view.View;
import www.disbot.jmemo.bot.view.impl.CommandResultView;

public class SignUpCommand extends ApiCommand {
	public SignUpCommand(RequestStrategy requestStrategy) {
		super(requestStrategy);
	}

	public static final String COMMAND = Command.PREFIX + "signup";
	public static final String EXPLAIN = "봇이 사용자 정보를 기억하게 해요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	@Override
	public String[] getArgNameArray() {
		return ARGS_NAME_ARRAY.clone();
	}
	
	@Override
	public View command(User user, Map<String, String> argsMap) throws Exception {
		if (argsMap.size() != ARGS_NAME_ARRAY.length) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		HttpEntity<SignUpDTO> body = new HttpEntity<>(new SignUpDTO(user.getName()));
		
		SignUpResultDTO result = requestTo("/party/createUser", HttpMethod.POST, body, SignUpResultDTO.class);
		
		if (! result.isSuccess()) {
			throw new Exception(result.getMsg());
		}
		
		DiscordContents contents = new DiscordContents(new SignUpParser(result));
	   	
		contents.parse();
		
	   	return CommandResultView.builder()
	   			.title(ListAllCommand.USAGE)
	   			.contents(contents)
	   			.build();
	}
}
