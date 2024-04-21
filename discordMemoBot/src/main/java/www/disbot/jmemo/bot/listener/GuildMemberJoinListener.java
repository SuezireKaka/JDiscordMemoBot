package www.disbot.jmemo.bot.listener;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import www.disbot.jmemo.bot.ResponseCarrier;

@Slf4j
@RequiredArgsConstructor
public class GuildMemberJoinListener extends ListenerAdapter {
	private ResponseCarrier carrier = new ResponseCarrier();
	
	@NonNull
	private String mainChannelID;
	
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		User user = event.getUser();
        TextChannel textChannel = event.getGuild().getTextChannelById(mainChannelID);
        
        log.info("get user join : " + user.getName());
        
	}
    
}