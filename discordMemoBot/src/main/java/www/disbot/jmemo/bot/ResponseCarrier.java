package www.disbot.jmemo.bot;

import java.util.List;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import www.disbot.jmemo.bot.view.View;

public class ResponseCarrier {
	public void carryResponseToChannel(
			TextChannel textChannel, View resultView) {
		resultView.initEmbed();
		
		List<String> resultTextList = resultView.textify();
		
		for (String text : resultTextList) {
			textChannel.sendMessage("")
				.setEmbeds(resultView.closeWith(text))
				.queue();
		}
	}
}
