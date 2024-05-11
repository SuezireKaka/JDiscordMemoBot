package www.disbot.jmemo.bot.command.api;

import org.springframework.context.ApplicationContext;

import lombok.Getter;
import www.disbot.jmemo.sys.context.DiscordBotToken;

@Getter
public class ContextHandler {
	private String answerToken;
	private String tokenPrefix;
	private String tokenSeperator;
	
	public ContextHandler(ApplicationContext context) {
		DiscordBotToken discordBotTokenEntity = context.getBean(DiscordBotToken.class);
		
		answerToken = discordBotTokenEntity.getDiscordBotToken();
		tokenPrefix = discordBotTokenEntity.getTokenPrefix();
		tokenSeperator = discordBotTokenEntity.getTokenSeperator();
		
		
	}
}
