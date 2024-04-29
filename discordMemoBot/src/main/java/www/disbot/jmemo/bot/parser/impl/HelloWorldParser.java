package www.disbot.jmemo.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.jmemo.bot.model.data.HelloWorldVO;
import www.disbot.jmemo.bot.model.structure.Pair;
import www.disbot.jmemo.bot.parser.ContentsParser;
import www.disbot.jmemo.bot.parser.DiscordParser;
import www.disbot.jmemo.bot.parser.ContentsParser.ParseType;

@AllArgsConstructor
public class HelloWorldParser extends DiscordParser {
	private HelloWorldVO vo;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();

		String keyString = vo.getClass().getDeclaredFields()[0].getName();
		Pair<ParseType, String> key = new Pair<>(ParseType.KEY, keyString);
		
		String valString = vo.getMessage();
		Pair<ParseType, String> val = new Pair<>(ParseType.VAL, valString);
		
		lemma.add(key);
		lemma.add(val);

		return lemma;
	}
}
