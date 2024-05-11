package www.disbot.jmemo.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.jmemo.api.memo.model.MemoDetailsVO;
import www.disbot.jmemo.bot.model.structure.Pair;
import www.disbot.jmemo.bot.parser.DiscordParser;

@AllArgsConstructor
public class MemoDetailsParser extends DiscordParser {
	private MemoDetailsVO vo;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();
		
		String titleKeyString = vo.getClass().getSuperclass().getDeclaredFields()[1].getName();
		Pair<ParseType, String> titleKey = new Pair<>(ParseType.KEY, titleKeyString);
		
		String titleValString = vo.getTitle();
		Pair<ParseType, String> titleVal = new Pair<>(ParseType.VAL, titleValString);

		String writerKeyString = vo.getClass().getSuperclass().getDeclaredFields()[0].getName();
		Pair<ParseType, String> writerKey = new Pair<>(ParseType.KEY, writerKeyString);
		
		String writerValString = vo.getWriter().getName();
		Pair<ParseType, String> writerVal = new Pair<>(ParseType.VAL, writerValString);
		
		String memoKeyString = vo.getClass().getDeclaredFields()[0].getName();
		Pair<ParseType, String> memoKey = new Pair<>(ParseType.KEY, memoKeyString);
		
		String memoValString = vo.getMemo();
		Pair<ParseType, String> memoVal = new Pair<>(ParseType.VAL, memoValString);
		
		lemma.add(titleKey);
		lemma.add(titleVal);
		lemma.add(writerKey);
		lemma.add(writerVal);
		lemma.add(memoKey);
		lemma.add(memoVal);

		return lemma;
	}
}
