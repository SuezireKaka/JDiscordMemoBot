package www.disbot.jmemo.listener;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import www.disbot.jmemo.listener.command.HelloWorldCommand;
import www.disbot.jmemo.listener.command.ListAllCommand;
import www.disbot.jmemo.listener.command.common.MessageOrganizer;

@Slf4j
public class MessageListener extends ListenerAdapter {
	@Value("${discord.bot.allow-channel.id}")
	private String mainChannelId;
	
	private final MessageOrganizer organizer = MessageOrganizer.getInstance();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        TextChannel textChannel = event.getChannel().asTextChannel();
        Message message = event.getMessage();

        log.info("get message : " + message.getContentDisplay());

        if (user.isBot()) {
            return;
        }
        else if (message.getContentDisplay().equals("")) {
            log.info("디스코드 Message 문자열 값 공백");
        }

        String[] messageArray = message.getContentDisplay().split("/");

        String[] returnMessageArray = classifyCommand(textChannel, messageArray);
        
        if (returnMessageArray.length != 0) {
        	
        	String[] organizedArray = organizer.organizeForDiscord(returnMessageArray);
        	
        	for (String organizedMessage : organizedArray) {
        		textChannel.sendMessage(organizedMessage)
					.queue();
        	}
        }
    }

	private String[] classifyCommand(TextChannel textChannel, String[] messageArray) {
		
		if (messageArray[0].equalsIgnoreCase(HelloWorldCommand.COMMAND)) {
            String[] messageArgs = Arrays.copyOfRange(messageArray, 1, messageArray.length);

            return new HelloWorldCommand().command(messageArgs);
            
        }
        else if (messageArray[0].equalsIgnoreCase(ListAllCommand.COMMAND)) {
            String[] messageArgs = Arrays.copyOfRange(messageArray, 1, messageArray.length);

            return new ListAllCommand().command(messageArgs);
        }
		return new String[]{};
	}
    
}