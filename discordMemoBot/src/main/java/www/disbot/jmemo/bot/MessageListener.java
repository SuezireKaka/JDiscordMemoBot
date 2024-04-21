package www.disbot.jmemo.bot;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import www.disbot.jmemo.bot.command.common.ArgsPacker;
import www.disbot.jmemo.bot.controller.CommandController;
import www.disbot.jmemo.bot.view.View;

@Slf4j
public class MessageListener extends ListenerAdapter {
	@Value("${discord.bot.allow-channel.id}")
	private String mainChannelId;
	
	private CommandController controller = new CommandController();

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

        String[] messageArray = message.getContentDisplay()
        		.split(ArgsPacker.SEPERATOR);
        
        String commandKey = messageArray[0];
        String[] commandArgs = Arrays.copyOfRange(messageArray, 1, messageArray.length);
        
		try {
			View resultView = controller.execute(commandKey, commandArgs);
			
			if (resultView != null) {
				List<String> resultTextList = resultView.textify();
				
				for (String text : resultTextList) {
					textChannel.sendMessage(text)
						.setEmbeds(resultView.closeWith(text))
						.queue();
				}
			}
		}
		catch (Exception e) {
			String errorMessage = "에러 발생 : " + e.getMessage();
			
			textChannel.sendMessage(errorMessage).queue();
			
			log.error(errorMessage);
		}
        
        
    }
    
}