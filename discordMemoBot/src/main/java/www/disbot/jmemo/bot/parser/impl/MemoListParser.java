package www.disbot.jmemo.bot.parser.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import www.disbot.jmemo.api.framework.model.structure.Page;
import www.disbot.jmemo.api.memo.model.MemoVO;
import www.disbot.jmemo.bot.model.structure.Pair;
import www.disbot.jmemo.bot.parser.DiscordParser;

@AllArgsConstructor
public class MemoListParser extends DiscordParser {
	private Page<MemoVO> dto;
	
	@Override
	public List<Pair<ParseType, String>> parseLemma() {
		
		List<Pair<ParseType, String>> lemma = new ArrayList<>();

		String pageKeyString = Page.PAGE_TERMINOLOGY;
		Pair<ParseType, String> key = new Pair<>(ParseType.KEY, pageKeyString);
		
		String pageValString = Page.PAGE_FORM.formatted(dto.getNowPage(), dto.getTotalPage());
		Pair<ParseType, String> val = new Pair<>(ParseType.VAL, pageValString);
		
		lemma.add(key);
		lemma.add(val);
		
		String keyString = Page.CONTENTS_TERMINOLOGY;
		key = new Pair<>(ParseType.KEY, keyString);
		
		List<MemoVO> contents = dto.getContents();
		
		String valString = contents.stream()
				.map(this :: extractMemo)
				.reduce("", (f, s) -> {
					return f.length() > 1
							? f + LIST_SEPERATOR + s
							: s;
				});
		
		val = new Pair<>(ParseType.VAL, valString);
		
		lemma.add(key);
		lemma.add(val);

		return lemma;
	}
	
	private String extractMemo(MemoVO memo) {
		return String.join(SUMMERY_SEPERATOR, 
				memo.getId(),
				memo.getWriter().getName(),
				memo.getTitle(),
				DATE_FORMAT.format(memo.getUptDt()));
	}
}
