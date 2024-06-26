package www.disbot.jmemo;

import javax.security.auth.login.LoginException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

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
	public static WebClient webClient;

	public static void main(String[] args) throws LoginException {
		context = SpringApplication.run(DiscordMemoBotApplication.class, args);

		DiscordBotToken discordBotTokenEntity = context.getBean(DiscordBotToken.class);

		String discordBotToken = discordBotTokenEntity.getDiscordBotToken();

		MakerDiscordID makerDiscordIDEntity = context.getBean(MakerDiscordID.class);

		String makerDiscordID = makerDiscordIDEntity.getMakerDiscordID();

		BotChannelID botChannelIDEntity = context.getBean(BotChannelID.class);

		String botChannelID = botChannelIDEntity.getBotChannelID();

		main = JDABuilder.createDefault(discordBotToken).setActivity(Activity.playing("자바로 동작"))
				.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
				.addEventListeners(
						new MessageListener(makerDiscordID),
						new GuildMemberJoinListener(makerDiscordID, botChannelID))
				.build();
		
		ApiRequestInfo apiRequestInfoEntity = context.getBean(ApiRequestInfo.class);
		
		String goalHost = apiRequestInfoEntity.getGoalHost();
		int goalPort = apiRequestInfoEntity.getGoalPort();
		
		String urlHead = "http://%s:%d".formatted(goalHost, Integer.valueOf(goalPort));
		
		webClient = WebClient.builder()
				.baseUrl(urlHead) 
				.defaultCookie("cookieKey", "cookieValue")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				.build();
	}

}
