package www.disbot.jmemo.bot.listener;

import java.util.Arrays;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import www.disbot.jmemo.bot.ResponseCarrier;
import www.disbot.jmemo.bot.command.api.WebClientRequestStrategy;
import www.disbot.jmemo.bot.controller.CommandController;
import www.disbot.jmemo.bot.controller.args.ArgsPacker;
import www.disbot.jmemo.bot.view.View;

@Slf4j
@RequiredArgsConstructor
public class MessageListener extends ListenerAdapter {
	@NonNull
	private String makerID;

	private CommandController controller = new CommandController();

	private ResponseCarrier carrier = new ResponseCarrier();

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		WebClientRequestStrategy requester = new WebClientRequestStrategy();
		
		User user = event.getAuthor();
		requester.save(user);

		TextChannel textChannel = event.getChannel().asTextChannel();

		Message message = event.getMessage();

		log.info("get message : " + message.getContentDisplay());

		if (user.isBot()) {
			return;
		} else if (message.getContentDisplay().equals("")) {
			log.info("디스코드 Message 문자열 값 공백");
		}

		String[] messageArray = message.getContentDisplay().split(ArgsPacker.SEPERATOR);

		String commandKey = messageArray[0];
		String[] commandArgs = Arrays.copyOfRange(messageArray, 1, messageArray.length);

		try {
			View resultView = controller.execute(user, commandKey, commandArgs, requester);

			if (resultView != null) {
				carrier.carryResponseToChannel(textChannel, resultView);
			}
		} catch (Exception e) {
			carrier.carryErrorToChannel(textChannel, e, makerID);
		}

	}

}