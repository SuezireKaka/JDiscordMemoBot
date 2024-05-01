package www.disbot.jmemo.bot.command;

import lombok.AllArgsConstructor;
import www.disbot.jmemo.bot.command.api.ApiRequester;

@AllArgsConstructor
public abstract class ApiCommand implements Command {
	private ApiRequester requester;
	
	protected String requestTo(String urlTail) {
		String result = "";
		
		return result;
	}
}
