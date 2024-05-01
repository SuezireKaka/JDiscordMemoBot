package www.disbot.jmemo.sys.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class BotChannelID {
	@Value("${discord.bot.allow-channel.id}")
    private String botChannelID;
}
