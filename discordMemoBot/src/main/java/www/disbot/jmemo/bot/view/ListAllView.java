package www.disbot.jmemo.bot.view;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.jmemo.bot.command.ListAllCommand;
import www.disbot.jmemo.bot.model.CommandVO;
import www.disbot.jmemo.bot.view.common.ListTypeProvider;

@Getter
@AllArgsConstructor
public class ListAllView extends DiscordView {
	private List<CommandVO> rawVOList;
	
	@Override
	public void initEmbed() {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(ListAllCommand.USAGE)
				.setDescription(ListAllCommand.USAGE + RESULT_TITLE_SUFFIX)
				.setColor(SUCCESS_COLOR));
	}
	
	@Override
	public List<String> textify() {	
		List<String> mappedVOList = rawVOList.stream()
				.map(CommandVO::toString)
				.collect(Collectors.toList());
		
		return rearrangeWithDiscordLimit(mappedVOList);
	}

	@Override
	public MessageEmbed closeWith(String value) {
		String type = ListTypeProvider
				.provideListFieldName(CommandVO.class.getSimpleName());
				
		return this.getEmbedBuilder()
				.addField(type, value, false)
				.build();
	}

}
