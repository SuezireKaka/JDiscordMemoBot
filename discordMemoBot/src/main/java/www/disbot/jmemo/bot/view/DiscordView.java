package www.disbot.jmemo.bot.view;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.EmbedBuilder;

@Getter
@Setter
public abstract class DiscordView implements View {
	private EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor("JMemo");
	
	public List<String> rearrangeWithDiscordLimit(List<String> mappedVOList) {
		List<String> result = new ArrayList<>();
		
		String lemma = "";
		
		for (String curString : mappedVOList) {
			String sample = lemma.length() == 0
					? curString
					: lemma + LIST_SEPERATOR + curString;
			
			if (sample.length() > MAX_ONE_VALUE_LENGTH) {
				result.add(lemma);
				
				while (curString.length() > MAX_ONE_VALUE_LENGTH) {
					String capture = curString.substring(0, MAX_ONE_VALUE_LENGTH)
							+ OMIT_SUFFIX;
					
					result.add(capture);
					
					curString = OMIT_PREFIX + curString.substring(MAX_ONE_VALUE_LENGTH);
				}
				
				lemma = curString;
			}
			else {
				lemma = sample;
			}
		}
		
		result.add(lemma);
		
		return result;
	}
}
