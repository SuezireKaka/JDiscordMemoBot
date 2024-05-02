package www.disbot.jmemo.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.jmemo.api.security.model.SignUpResultDTO;
import www.disbot.jmemo.bot.model.structure.Pair;
import www.disbot.jmemo.bot.parser.DiscordParser;

@AllArgsConstructor
public class SignUpParser extends DiscordParser {
	private SignUpResultDTO dto;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();

		String keyString = dto.getClass().getDeclaredFields()[1].getName();
		Pair<ParseType, String> key = new Pair<>(ParseType.KEY, keyString);
		
		String valString = dto.getMsg();
		Pair<ParseType, String> val = new Pair<>(ParseType.VAL, valString);
		
		lemma.add(key);
		lemma.add(val);

		return lemma;
	}
}
