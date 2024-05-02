package www.disbot.jmemo.bot.command.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
	private String answerToken;
	@NonNull
	private String tokenPrefix;
	@NonNull
	private String tokenSeperator;

	private User user;

	public void save(User user) {
		this.user = user;
	}

	@Override
	public <B> String requestTo(String urlTail, HttpMethod method, B body) {
		RestTemplate template = new RestTemplate();

		String urlHead = "http://%s:%d".formatted(goalHost, goalPort);
		String url = urlHead + urlTail;

		String userId = user == null ? "" : user.getId();

		MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();

		headerMap.add("x-auth-token",
				tokenPrefix + answerToken + tokenSeperator + userId);
		
		HttpEntity<B> requestBody = new HttpEntity<>(body, headerMap);

		ResponseEntity<String> accessTokenResponse = template.exchange(url,
				HttpMethod.POST, requestBody, String.class);

		return accessTokenResponse.getBody();
	}

}
