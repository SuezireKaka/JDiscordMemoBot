package www.disbot.jmemo.bot.model.discord;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.jmemo.bot.exception.TooLongKeyStateException;
import www.disbot.jmemo.bot.model.common.Pair;
import www.disbot.jmemo.bot.model.data.CommandVO;

@AllArgsConstructor
public class CommandListParser implements ContentsParser {
	private List<CommandVO> commandList = new ArrayList<>();
	
	@Override
	public List<List<Pair<ParseType, String>>> parse() throws Exception {
		List<List<Pair<ParseType, String>>> result = new ArrayList<>();

		List<Pair<ParseType, String>> lemma = parseLemma();
		List<Pair<ParseType, String>> cash = new ArrayList<>();
		
		String currentState = "";
		
		for (Pair<ParseType, String> elem : lemma) {
			String realContents = elem.getSecond();
			
			if (elem.getFirst() == ParseType.KEY) {
				if (realContents.length() > MAX_ONE_VALUE_LENGTH) {
					throw new TooLongKeyStateException(realContents.length());
				}
				
				currentState += realContents;
				
				if (currentState.length() > MAX_ONE_VALUE_LENGTH) {
					result.add(cash);
					currentState = "";
				}
				else {
					currentState += LIST_SEPERATOR;
				}
				
				cash.add(elem);
			}
			
			if (elem.getFirst() == ParseType.VAL) {
				int currentLength = currentState.length();
				
				currentState += realContents;
				
				while (true) {
					if (currentState.length() > MAX_ONE_VALUE_LENGTH) {
						int pos = MAX_ONE_VALUE_LENGTH - currentLength;
						
						String earlier = realContents.substring(0, pos) + OMIT_SUFFIX;
						
						elem.setSecond(earlier);
						cash.add(elem);
						result.add(cash);
						cash.clear();
						
						realContents = OMIT_PREFIX + realContents.substring(pos);
						currentState = realContents + LIST_SEPERATOR;
					}
					else {
						currentState += LIST_SEPERATOR;
						cash.add(elem);
						break;
					}
				}
			}
		}
		
		result.add(cash);
		
		return result;
	}

	private List<Pair<ParseType, String>> parseLemma() {
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
