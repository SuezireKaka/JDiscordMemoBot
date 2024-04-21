package www.disbot.jmemo.bot.view;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.entities.MessageEmbed;

public interface View {
	public static final String ITALIC = "*%s*";
	public static final String BOLD = "**%s**";
	public static final String UNDERLINE = "__%s__";
	public static final String CANCEL = "~~%s~~";
	
	public static final String OMIT_SUFFIX = "(...";
	public static final String OMIT_PREFIX = "...)";
	
	public static final String LIST_SEPERATOR = "\n";
	
	public static final String RESULT_TITLE_SUFFIX = "의 결과물";
	
	public static final Color SUCCESS_COLOR = new Color(52, 200, 31);
	public static final Color ERROR_COLOR = new Color(200, 31, 52);
	
	public static final int MAX_ONE_VALUE_LENGTH = 1000;
	
	public void initEmbed();
	
	public List<String> textify();
	
	public MessageEmbed closeWith(String value); // 이거 타입명 정할 때 전략패턴 쓰면 좋을 듯
}
