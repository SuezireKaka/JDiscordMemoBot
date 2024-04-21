package www.disbot.jmemo.sys.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MakerDiscordID {
	
	@Value("${discord.bot.maker.discord-user.id}")
    private String makerDiscordID;

    public String getMakerDiscordID() {
        return makerDiscordID;
    }
}
