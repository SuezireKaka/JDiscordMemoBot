package www.disbot.jmemo;

import javax.security.auth.login.LoginException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import www.disbot.jmemo.listener.DiscordListener;
import www.disbot.jmemo.sys.DiscordBotToken;

@SpringBootApplication
public class DiscordMemoBotApplication {

	public static void main(String[] args) throws LoginException {
		ApplicationContext context = SpringApplication.run(DiscordMemoBotApplication.class, args);

        DiscordBotToken discordBotTokenEntity = context.getBean(DiscordBotToken.class);
        String discordBotToken = discordBotTokenEntity.getDiscordBotToken();

        JDA jda = JDABuilder.createDefault(discordBotToken)
                .setActivity(Activity.playing("자바로 동작을"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new DiscordListener())
                .build();
	}

}
