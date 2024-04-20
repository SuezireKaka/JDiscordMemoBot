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

@Slf4j
public class DiscordListener extends ListenerAdapter {
	@Value("${discord.bot.allow-channel.id}")
	private String channelId;

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

        if (messageArray[0].equalsIgnoreCase(HelloWorldCommand.COMMAND)) {
            String[] messageArgs = Arrays.copyOfRange(messageArray, 1, messageArray.length);

            String returnMessage = new HelloWorldCommand().command(messageArgs);
            textChannel.sendMessage(returnMessage).queue();
        }
        else if (messageArray[0].equalsIgnoreCase(ListAllCommand.COMMAND)) {
            String[] messageArgs = Arrays.copyOfRange(messageArray, 1, messageArray.length);

            String returnMessage = new ListAllCommand().command(messageArgs);
            textChannel.sendMessage(returnMessage).queue();
        }
    }
    
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
    	User newUser = event.getUser();
    	TextChannel textChannel = event.getGuild().getTextChannelById(channelId);
    	
    	String returnMessage = "**%s**님, 어서 오세요!";
    	
    	textChannel.sendMessage(returnMessage.formatted(newUser.getName()))
    		.queue();
    }
    
}