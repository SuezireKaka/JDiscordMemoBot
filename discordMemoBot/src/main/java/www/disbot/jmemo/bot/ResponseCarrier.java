package www.disbot.jmemo.bot;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import www.disbot.jmemo.bot.view.View;
import www.disbot.jmemo.bot.view.impl.ErrorView;

@Slf4j
public class ResponseCarrier {
	public void carryResponseToChannel(TextChannel textChannel, View resultView) throws Exception {		
		List<MessageEmbed> resultEmbedList = resultView.close();
		
		for (MessageEmbed embed : resultEmbedList) {
			textChannel.sendMessage("")
				.setEmbeds(embed)
				.queue();
		}
	}
	
	public void carryErrorToChannel(TextChannel textChannel, Exception e, String makerID) {
		String errorMessage = "에러 발생 : " + e.getMessage();
		log.error(errorMessage);
		
		View errorView = new ErrorView(e, makerID);
		
		try {
			errorView.init(Exception.class);
			
			carryResponseToChannel(textChannel, errorView);
		}
		catch (Exception fatalError) {
			fatalError.printStackTrace();
		}
	}
}
