package www.disbot.jmemo.bot.view;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.jmemo.bot.command.HelloWorldCommand;
import www.disbot.jmemo.bot.model.HelloWorldVO;

@Getter
@RequiredArgsConstructor
public class HelloWorldView extends DiscordView {
	public static final Color HELLO_WORLD_COLOR = new Color(52, 200, 31);
	
	@NonNull
	private HelloWorldVO rawVO;
	
	@Override
	public List<String> textify() {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(HelloWorldCommand.USAGE)
				.setDescription(HelloWorldCommand.USAGE + RESULT_TITLE_SUFFIX)
				.setColor(HELLO_WORLD_COLOR));
		return Arrays.asList(new String[]{rawVO.getMessage()});
	}

	@Override
	public MessageEmbed closeWith(String value) {
		return this.getEmbedBuilder()
				.addField(HelloWorldVO.class.getDeclaredFields()[0].getName(),
						value, false)
				.build();
	}
}
