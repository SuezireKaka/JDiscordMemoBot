package www.disbot.jmemo.bot.command.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.User;

@RequiredArgsConstructor
public class DiscordBotRequestStrategy implements RequestStrategy {
	@NonNull
	private String goalHost;
	@NonNull
	private Integer goalPort;
	@NonNull
	private String tokenPrefix;
	@NonNull
	private String tokenSeperator;

	private User user;

	public void save(User user) {
		this.user = user;
	}

	@Override
	public String requestTo(String urlTail, HttpMethod method, HttpEntity<?> requestBody) {
		RestTemplate template = new RestTemplate();

		String urlHead = "http://%s:%d".formatted(goalHost, goalPort);
		String url = urlHead + urlTail;

		String userId = user == null ? "" : user.getId();

		HttpHeaders headers = new HttpHeaders();

		headers.add("x-auth-token", tokenPrefix + tokenSeperator + userId);

		ResponseEntity<String> accessTokenResponse = template.exchange(url,
				HttpMethod.POST, requestBody, String.class);

		return accessTokenResponse.getBody();
	}

}
