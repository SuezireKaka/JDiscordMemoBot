package www.disbot.jmemo.bot.model.discord;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.jmemo.bot.model.common.Pair;
import www.disbot.jmemo.bot.model.data.HelloWorldVO;

@AllArgsConstructor
public class HelloWorldParser implements ContentsParser {
	private HelloWorldVO vo;
	
	@Override
	public List<List<Pair<ParseType, String>>> parse() throws Exception {
		List<List<Pair<ParseType, String>>> result = new ArrayList<>();
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();

		String keyString = vo.getClass().getDeclaredFields()[0].getName();
		Pair<ParseType, String> key = new Pair<>(ParseType.KEY, keyString);
		
		String valString = vo.getMessage();
		Pair<ParseType, String> val = new Pair<>(ParseType.VAL, valString);
		
		lemma.add(key);
		lemma.add(val);
		
		result.add(lemma);
		
		return result;
	}
}
