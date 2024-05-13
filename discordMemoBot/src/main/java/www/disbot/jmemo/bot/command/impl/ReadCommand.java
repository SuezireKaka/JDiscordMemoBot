package www.disbot.jmemo.bot.command.impl;

import java.util.Map;

import org.springframework.http.HttpMethod;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.api.memo.model.MemoDetailsVO;
import www.disbot.jmemo.bot.command.ApiCommand;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.api.RequestStrategy;
import www.disbot.jmemo.bot.command.impl.idChecker.IdChacker;
import www.disbot.jmemo.bot.controller.args.ArgsPacker;
import www.disbot.jmemo.bot.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.exception.UnexpectedArgsException;
import www.disbot.jmemo.bot.parser.DiscordContents;
import www.disbot.jmemo.bot.parser.impl.MemoDetailsParser;
import www.disbot.jmemo.bot.view.View;
import www.disbot.jmemo.bot.view.impl.CommandResultView;

public class ReadCommand extends ApiCommand {
	public ReadCommand(RequestStrategy requestStrategy) {
		super(requestStrategy);
	}

	public static final String COMMAND = Command.PREFIX + "read";
	public static final String EXPLAIN = "읽을 수 있는 메모를 읽어요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{"(id: 숫자 또는 로마자 4자리)"};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);

	@Override
	public String[] getArgsNameArray() {
		return ARGS_NAME_ARRAY.clone();
	}
	@Override
	public View command(User user, Map<String, String> argsMap) throws Exception {	
		if (argsMap.size() != ARGS_NAME_ARRAY.length) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		String id = argsMap.get(ARGS_NAME_ARRAY[0]).toLowerCase();
		
		if (! IdChacker.isValidId(id)) {
			throw new UnexpectedArgsException(id);
		}

		MemoDetailsVO result = requestTo(MEMO + BY_ID + STR_VARIABLE.formatted(id),
				HttpMethod.GET, "", MemoDetailsVO.class);
		
		DiscordContents contents = new DiscordContents(new MemoDetailsParser(result));
	   	
		contents.parse();
		
	   	return CommandResultView.builder()
	   			.title(MemoListCommand.USAGE)
	   			.contents(contents)
	   			.build();
	}

}
