package www.disbot.jmemo.bot.parser;

import java.util.List;

import www.disbot.jmemo.bot.model.structure.Pair;

public interface ContentsParser {
	public static final String OMIT_SUFFIX = "(...";
	public static final String OMIT_PREFIX = "...)";
	
	public static final int MAX_ONE_VALUE_LENGTH = 800;
	
	public static final String LIST_SEPERATOR = "\n";
	
	public enum ParseType {
		KEY, VAL
	}
	
	public List<List<Pair<ParseType, String>>> parse() throws Exception;
	
	public List<Pair<ParseType, String>> parseLemma();
}
