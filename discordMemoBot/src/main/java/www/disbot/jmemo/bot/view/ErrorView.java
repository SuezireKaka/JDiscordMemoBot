package www.disbot.jmemo.bot.view;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.jmemo.bot.model.HelloWorldVO;

@Getter
@RequiredArgsConstructor
public class ErrorView extends DiscordView {
	public static final String ERROR_TITLE = "에러 발생";
	
	public static final Color ERROR_COLOR = new Color(200, 31, 52);
	
	public static final String ERROR_FIELD_TYPENAME = "mention";
	
	@NonNull
	private Exception e;
	
	@NonNull
	private String makerUsername;
	
	@Override
	public void initEmbed() {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(ERROR_TITLE)
				.setDescription(e.getMessage())
				.setColor(ERROR_COLOR));
	}
	
	@Override
	public List<String> textify() {
		List<String> stackTraceTextList = Arrays.asList(
				new String[]{"일단 모르겠고 제작자를 호출합니다: "
						+ MENTION_FORMAT.formatted(makerUsername)});
		
		return rearrangeWithDiscordLimit(stackTraceTextList);
	}

	@Override
	public MessageEmbed closeWith(String value) {
		String type = HelloWorldVO.class.getDeclaredFields()[0].getName();
		
		return this.getEmbedBuilder()
				.addField(type, value, false)
				.build();
	}
}
