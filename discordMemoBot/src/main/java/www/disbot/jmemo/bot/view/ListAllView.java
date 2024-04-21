package www.disbot.jmemo.bot.view;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.jmemo.bot.command.HelloWorldCommand;
import www.disbot.jmemo.bot.model.CommandVO;
import www.disbot.jmemo.bot.model.HelloWorldVO;

@Getter
@RequiredArgsConstructor
public class ListAllView extends DiscordView {
	@NonNull
	private List<CommandVO> rawVOList;
	
	@Override
	public List<String> textify() {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(HelloWorldCommand.USAGE)
				.setDescription(HelloWorldCommand.USAGE + RESULT_TITLE_SUFFIX)
				.setColor(SUCCESS_COLOR));
		return null;
	}

	@Override
	public MessageEmbed closeWith(String value) {
		return this.getEmbedBuilder()
				.addField(HelloWorldVO.class.getDeclaredFields()[0].getName(),
						value, false)
				.build();
	}

}
