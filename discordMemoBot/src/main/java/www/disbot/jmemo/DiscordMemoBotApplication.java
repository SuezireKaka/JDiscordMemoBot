package www.disbot.jmemo;

import javax.security.auth.login.LoginException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import www.disbot.jmemo.bot.listener.GuildMemberJoinListener;
import www.disbot.jmemo.bot.listener.MessageListener;
import www.disbot.jmemo.sys.context.ApiRequestInfo;
import www.disbot.jmemo.sys.context.BotChannelID;
import www.disbot.jmemo.sys.context.DiscordBotToken;
import www.disbot.jmemo.sys.context.MakerDiscordID;

@SpringBootApplication
public class DiscordMemoBotApplication {
	public static ApplicationContext context;
	public static JDA main;

	public static void main(String[] args) throws LoginException {
		context = SpringApplication.run(DiscordMemoBotApplication.class, args);

		DiscordBotToken discordBotTokenEntity = context.getBean(DiscordBotToken.class);

		String discordBotToken = discordBotTokenEntity.getDiscordBotToken();
		String tokenPrefix = discordBotTokenEntity.getTokenPrefix();
		String tokenSeperator = discordBotTokenEntity.getTokenSeperator();

		MakerDiscordID makerDiscordIDEntity = context.getBean(MakerDiscordID.class);

		String makerDiscordID = makerDiscordIDEntity.getMakerDiscordID();

		BotChannelID botChannelIDEntity = context.getBean(BotChannelID.class);

		String botChannelID = botChannelIDEntity.getBotChannelID();
		
		ApiRequestInfo apiRequestInfoEntity = context.getBean(ApiRequestInfo.class);
		
		String goalHost = apiRequestInfoEntity.getGoalHost();
		String goalPort = apiRequestInfoEntity.getGoalPort();

		main = JDABuilder.createDefault(discordBotToken).setActivity(Activity.playing("자바로 동작"))
				.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
				.addEventListeners(
						new MessageListener(makerDiscordID, goalHost, goalPort, tokenPrefix, tokenSeperator),
						new GuildMemberJoinListener(makerDiscordID, botChannelID))
				.build();
	}

}
