package www.disbot.jmemo.bot.command.impl;

import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpMethod;

import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.api.memo.model.MemoDTO;
import www.disbot.jmemo.api.memo.model.MemoDetailsVO;
import www.disbot.jmemo.bot.command.ApiCommand;
import www.disbot.jmemo.bot.command.ArgsHoldingCommand;
import www.disbot.jmemo.bot.command.Command;
import www.disbot.jmemo.bot.command.api.RequestStrategy;
import www.disbot.jmemo.bot.controller.args.ArgsPacker;
import www.disbot.jmemo.bot.controller.args.ArgsSaver;
import www.disbot.jmemo.bot.exception.ArgsNumberDismatchException;
import www.disbot.jmemo.bot.exception.OutOfStringLimitArgException;
import www.disbot.jmemo.bot.exception.UnexpectedArgsException;
import www.disbot.jmemo.bot.parser.ContentsParser;
import www.disbot.jmemo.bot.parser.DiscordContents;
import www.disbot.jmemo.bot.parser.impl.ArgRequireParser;
import www.disbot.jmemo.bot.parser.impl.MemoDetailsParser;
import www.disbot.jmemo.bot.view.View;
import www.disbot.jmemo.bot.view.impl.CommandResultView;

public class MemoCommand extends ApiCommand implements ArgsHoldingCommand {
	public MemoCommand(RequestStrategy requestStrategy, String asyncArg) {
		super(requestStrategy);
		this.asyncArg = asyncArg;
	}

	public static final String COMMAND = Command.PREFIX + "memo";
	public static final String EXPLAIN = "하고 싶은 메모를 남겨요";
	
	private static final String[] ARGS_NAME_ARRAY = new String[]{"공개" + OR + "비공개"};
	
	private static final String PUBLIC = "공개";
	
	private static final String[] ASYNC_ARGS_INFO_ARRAY = new String[]{
			"제목을 입력하세요(최대 %d자)",
			"내용을 입력하세요(최대 %d자)"};
	
	private static final int[] ASYNC_ARGS_LIMIT_ARRAY = new int[]{255, DISCORD_MAX_LENGTH};
	
	public static final String USAGE = ArgsPacker.usagePack(COMMAND, ARGS_NAME_ARRAY);
	
	private String asyncArg;

	@Override
	public String[] getArgsNameArray() {
		return ARGS_NAME_ARRAY.clone();
	}
	
	@Override
	public String[] getAsyncArgsInfoArray() {
		return ASYNC_ARGS_INFO_ARRAY.clone();
	}
	
	@Override
	public int[] getAsyncArgsLimitArray() {
		return ASYNC_ARGS_LIMIT_ARRAY.clone();
	}
	
	@Override
	public View command(User user, Map<String, String> argsMap) throws Exception {	
		ContentsParser parser;
		
		if (! ArgsSaver.hasSaved(user)) {
			String key = ARGS_NAME_ARRAY[0];
			String arg = argsMap.get(key);
			
			String[] expectedArgArray = key.split(REGEX_ESCAPE + OR);
			
			if (! Arrays.asList(expectedArgArray).contains(arg)) {
				throw new UnexpectedArgsException(expectedArgArray, arg);
			}
			
			ArgsSaver.init(user, setupCommand(argsMap));
		}
		else {
			ArgsSaver.save(user, setupCommand(argsMap), asyncArg);
		}
		
		String[] savedArgs = ArgsSaver.getSavedArgsOf(user);
		
		// 현재 들어온 것 개수에서 처음부터 들어와야 하는 것 개수를 빼면 대기 중인 인덱스
		int nowIndex = argsMap.size() + savedArgs.length - ARGS_NAME_ARRAY.length;
		
		if (nowIndex > 0) {
			int limit = ASYNC_ARGS_LIMIT_ARRAY[nowIndex - 1];
			
			if (asyncArg.length() > limit) {
				ArgsSaver.cancelLastSave(user);
				throw new OutOfStringLimitArgException(limit, asyncArg);
			}
		}
		
		if (! isArgsComplete(argsMap, savedArgs)) {
			String msg = ASYNC_ARGS_INFO_ARRAY[nowIndex]
					.formatted(ASYNC_ARGS_LIMIT_ARRAY[nowIndex]);
			
			parser = new ArgRequireParser(msg);
		}
		else { // 둘이 같아야 여기 들어온다
			MemoDTO memoBody = MemoDTO.builder()
					.isPublic(argsMap.get(ARGS_NAME_ARRAY[0]).equals(PUBLIC))
					.title(savedArgs[0])
					.memo(savedArgs[1])
					.build();
			
			MemoDetailsVO response = requestTo(MEMO + CREATE, HttpMethod.POST, memoBody, MemoDetailsVO.class);
			
			parser = new MemoDetailsParser(response);
			
			ArgsSaver.remove(user);
		}
		
	   	DiscordContents contents = new DiscordContents(parser);
	   	
	   	contents.parse();
	   	
	   	return CommandResultView.builder()
	   			.title(MemoCommand.USAGE)
	   			.contents(contents)
	   			.build();
	}

	private String setupCommand(Map<String, String> argsMap) {
		return String.join(ArgsPacker.SEPERATOR,
				COMMAND, argsMap.get(ARGS_NAME_ARRAY[0]));
	}
	
	@Override
	public int calcTotalArgsNumber() {
		return ARGS_NAME_ARRAY.length + ASYNC_ARGS_INFO_ARRAY.length;
	}

	@Override
	public boolean isArgsComplete(Map<String, String> argsMap, String[] asyncArgsArray) throws Exception {
		int requiredSize = calcTotalArgsNumber();
		
		int currentSize = calcCurrentSize(argsMap, asyncArgsArray);
		
		if (currentSize < ARGS_NAME_ARRAY.length || currentSize > requiredSize) {
			throw new ArgsNumberDismatchException(
					argsMap.values().toArray(new String[0]),
					ARGS_NAME_ARRAY);
		}
		
		return currentSize == requiredSize;
	}
	
	private int calcCurrentSize(Map<String, String> argsMap, String[] asyncArgsArray) {
		return argsMap.size() + asyncArgsArray.length;
	}

}
