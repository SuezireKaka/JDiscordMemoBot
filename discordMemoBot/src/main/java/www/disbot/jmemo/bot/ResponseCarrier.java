package www.disbot.jmemo.bot;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import www.disbot.jmemo.bot.view.ErrorView;
import www.disbot.jmemo.bot.view.View;

@Slf4j
public class ResponseCarrier {
	public void carryResponseToChannel(TextChannel textChannel, View resultView) throws Exception {
		resultView.initEmbed();
		
		List<String> resultTextList = resultView.textify();
		
		for (String text : resultTextList) {
			textChannel.sendMessage("")
				.setEmbeds(resultView.closeWith(text))
				.queue();
		}
	}
	
	public void carryErrorToChannel(TextChannel textChannel, Exception e, String makerID) {
		String errorMessage = "에러 발생 : " + e.getMessage();
		log.error(errorMessage);
		
		View errorView = new ErrorView(e, makerID);

		try {
			carryResponseToChannel(textChannel, errorView);
		}
		catch (Exception fatalError) {
			fatalError.printStackTrace();
		}
	}
}
