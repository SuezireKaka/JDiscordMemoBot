package www.disbot.jmemo.bot.command;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import lombok.AllArgsConstructor;
import lombok.Setter;
import www.disbot.jmemo.bot.command.api.RequestStrategy;

@AllArgsConstructor
public abstract class ApiCommand implements Command {
	@Setter
	private RequestStrategy requestStrategy;
	
	protected String requestTo(String urlTail, HttpMethod method, HttpEntity<?> requestBody) {
		String result = requestStrategy.requestTo(urlTail, method, requestBody);
		
		return result;
	}
}
