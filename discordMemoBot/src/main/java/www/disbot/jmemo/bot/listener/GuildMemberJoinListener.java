package www.disbot.jmemo.bot.listener;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import www.disbot.jmemo.bot.ResponseCarrier;
import www.disbot.jmemo.bot.controller.GreetingController;
import www.disbot.jmemo.bot.view.View;

@Slf4j
@RequiredArgsConstructor
public class GuildMemberJoinListener extends ListenerAdapter {
	private GreetingController controller = new GreetingController();
	
	private ResponseCarrier carrier = new ResponseCarrier();
	
	@NonNull
	private String makerID;
	@NonNull
	private String mainChannelID;
	
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		User user = event.getUser();
        TextChannel textChannel = event.getGuild().getTextChannelById(mainChannelID);
        
        log.info("get user join : " + user.getName());
        
        
		try {
			View resultView = controller.execute(user);
			
			if (resultView != null) {
				carrier.carryResponseToChannel(textChannel, resultView);
			}
		}
		catch (Exception e) {
			carrier.carryErrorToChannel(textChannel, e, makerID);
		}
        
        
        
	}
    
}