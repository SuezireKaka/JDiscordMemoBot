package www.disbot.jmemo.bot.view;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.jmemo.bot.command.ListAllCommand;
import www.disbot.jmemo.bot.model.common.Pair;
import www.disbot.jmemo.bot.model.discord.ContentsParser.ParseType;
import www.disbot.jmemo.bot.model.discord.DiscordContents;

@Getter
@Builder
public class CommandResultView extends DiscordView {
	private String title;
	private DiscordContents contents;
	
	@Override
	public void init() {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(ListAllCommand.USAGE)
				.setDescription(ListAllCommand.USAGE + RESULT_TITLE_SUFFIX)
				.setColor(SUCCESS_COLOR));
	}

	@Override
	public List<MessageEmbed> close() throws Exception {
		List<List<Pair<ParseType, String>>> parsedContents = contents.getContents();
		
		List<MessageEmbed> result = new ArrayList<>();
		
		for (List<Pair<ParseType, String>> lemma : parsedContents) {
			EmbedBuilder safeBuilder = this.getEmbedBuilder();
			
			for (int i = 0; i < lemma.size(); i++) {
				String key = lemma.size() % 2 == i + 1
						? ""
						: lemma.get(i).getSecond();
				
				i++;
				
				String val = lemma.get(i).getSecond();
				
				safeBuilder.addField(key, val, false);
			}
			
			result.add(safeBuilder.build());
		}
		
		return result;
	}

}
