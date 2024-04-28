package www.disbot.jmemo.bot.model.discord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import www.disbot.jmemo.bot.model.common.Pair;
import www.disbot.jmemo.bot.model.discord.ContentsParser.ParseType;

@Getter
@RequiredArgsConstructor
public class DiscordContents {
	private List<List<Pair<ParseType, String>>> contents = new ArrayList<>();
	
	@NonNull
	private ContentsParser parser;
	
	public void parse() throws Exception {
		this.contents = parser.parse();
	}
}