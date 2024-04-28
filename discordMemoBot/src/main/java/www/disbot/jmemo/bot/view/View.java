package www.disbot.jmemo.bot.view;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.entities.MessageEmbed;

public interface View {
	public static final String ITALIC = "*%s*";
	public static final String BOLD = "**%s**";
	public static final String UNDERLINE = "__%s__";
	public static final String CANCEL = "~~%s~~";
	
	public static final String SPACE = " ";
	
	public static final String MENTION_FORMAT = "<@%s>";
	
	public static final String RESULT_TITLE_SUFFIX = "의 결과물";
	
	public static final Color SUCCESS_COLOR = new Color(52, 200, 31);
	
	public void init();
	
	public List<MessageEmbed> close() throws Exception;
}
