package www.disbot.jmemo.sys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MakerDiscordID {
	
	@Value("${discord.bot.maker.discord-user.id}")
    private String MakerDiscordID;

    public String getMakerDiscordID() {
        return MakerDiscordID;
    }
}
