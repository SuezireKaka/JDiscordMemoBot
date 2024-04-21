package www.disbot.jmemo.bot.view;

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
	@NonNull
	private HelloWorldVO rawVO;
	
	@Override
	public void initEmbed() {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(HelloWorldCommand.USAGE)
				.setDescription(HelloWorldCommand.USAGE + RESULT_TITLE_SUFFIX)
				.setColor(SUCCESS_COLOR));
	}
	
	@Override
	public List<String> textify() {
		return Arrays.asList(new String[]{rawVO.getMessage()});
	}

	@Override
	public MessageEmbed closeWith(String value) {
		String type = HelloWorldVO.class.getDeclaredFields()[0].getName();
		
		return this.getEmbedBuilder()
				.addField(type, value, false)
				.build();
	}
}
