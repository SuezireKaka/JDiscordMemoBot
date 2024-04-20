package www.disbot.jmemo.listener;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import www.disbot.jmemo.listener.command.HelloWorldCommand;

@Slf4j
public class DiscordListener extends ListenerAdapter {

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
    }
}