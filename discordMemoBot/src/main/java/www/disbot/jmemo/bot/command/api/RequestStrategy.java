package www.disbot.jmemo.bot.command.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public interface RequestStrategy {
	public String requestTo(String urlTail, HttpMethod method, HttpEntity<?> requestBody);

}
