package www.disbot.jmemo.bot.view;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.jmemo.bot.model.CommandVO;

@Getter
@RequiredArgsConstructor
public class ListAllView extends DiscordView {
	@NonNull
	private List<CommandVO> rawVOList;

	@Override
	public MessageEmbed closeWith(String value) {
		return this.getEmbedBuilder()
				.addField("msg:", value, false)
				.build();
	}

	@Override
	public List<String> textify() {
		return null;
	}

}
