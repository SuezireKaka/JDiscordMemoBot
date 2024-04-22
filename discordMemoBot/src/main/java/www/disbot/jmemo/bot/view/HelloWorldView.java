package www.disbot.jmemo.bot.view;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.jmemo.bot.command.HelloWorldCommand;
import www.disbot.jmemo.bot.model.HelloWorldVO;

@Getter
@AllArgsConstructor
public class HelloWorldView extends DiscordView {
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
		List<String> mappedVOList = new ArrayList<>();
		mappedVOList.add(rawVO.getMessage());
		
		return rearrangeWithDiscordLimit(mappedVOList);
	}

	@Override
	public MessageEmbed closeWith(String value) {
		String type = HelloWorldVO.class.getDeclaredFields()[0].getName();
		
		return this.getEmbedBuilder()
				.addField(type, value, false)
				.build();
	}
}
