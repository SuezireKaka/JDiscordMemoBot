package www.disbot.jmemo.bot.view;

import java.util.List;

import net.dv8tion.jda.api.entities.MessageEmbed;

public interface View {
	public static final String ITALIC = "*%s*";
	public static final String BOLD = "**%s**";
	public static final String UNDERLINE = "__%s__";
	public static final String CANCEL = "~~%s~~";
	
	public static final String RESULT_TITLE_SUFFIX = "의 결과물";
	
	public MessageEmbed closeWith(String value);
	public List<String> textify();
}
