package www.disbot.jmemo.bot.command.impl;

import java.util.Map;

import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.core.type.TypeReference;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.api.framework.model.structure.Page;
import www.disbot.jmemo.api.memo.model.MemoVO;
import www.disbot.jmemo.bot.command.ApiCommand;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.api.RequestStrategy;
import www.disbot.jmemo.bot.controller.args.ArgsPacker;
import www.disbot.jmemo.bot.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.exception.UnexpectedArgsException;
import www.disbot.jmemo.bot.parser.DiscordContents;
import www.disbot.jmemo.bot.parser.impl.MemoListParser;
import www.disbot.jmemo.bot.view.View;
import www.disbot.jmemo.bot.view.impl.CommandResultView;

public class MemoListCommand extends ApiCommand {
	public MemoListCommand(RequestStrategy requestStrategy) {
		super(requestStrategy);
	}

	public static final String COMMAND = Command.PREFIX + "memoList";
	public static final String EXPLAIN = "모든 공개된 메모를 가져와요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{"(페이지 수)"};
	
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
		
		String pageString = argsMap.get(ARGS_NAME_ARRAY[0]);
		
		int pageNum = Integer.valueOf(pageString);
		
		if (pageNum < 1) {
			throw new UnexpectedArgsException(1, Integer.MAX_VALUE, pageString);
		}
		
		Page<MemoVO> result = requestTo("/memo/listAllMemoes/%d".formatted(pageNum),
				HttpMethod.GET, "", MemoVO.class, new TypeReference<>() {});
		
		DiscordContents contents = new DiscordContents(new MemoListParser(result));
	   	
		contents.parse();
		
	   	return CommandResultView.builder()
	   			.title(MemoListCommand.USAGE)
	   			.contents(contents)
	   			.build();
	}
}

