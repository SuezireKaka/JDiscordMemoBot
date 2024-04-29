package www.disbot.jmemo.bot.model.discord;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.jmemo.bot.exception.TooLongKeyStateException;
import www.disbot.jmemo.bot.model.common.Pair;
import www.disbot.jmemo.bot.model.data.CommandVO;

@AllArgsConstructor
public class CommandListParser extends DiscordParser {
	private List<CommandVO> commandList = new ArrayList<>();

	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		List<Pair<ParseType, String>> lemma = new ArrayList<>();
		
		for (CommandVO cmd : commandList) {
			String keyString = cmd.getCommand();
			Pair<ParseType, String> key = new Pair<>(ParseType.KEY, keyString);
			
			String valString = cmd.getExplain();
			Pair<ParseType, String> val = new Pair<>(ParseType.VAL, valString);
			
			lemma.add(key);
			lemma.add(val);
			
			
		}
		
		return lemma;
	}
}
