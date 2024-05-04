package www.disbot.jmemo.bot.command.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.User;

@RequiredArgsConstructor
public class WebClientRequestStrategy implements RequestStrategy {
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
		String urlHead = "http://%s:%d".formatted(goalHost, goalPort);
		String url = urlHead + urlTail;

		String userId = user == null ? "" : user.getId();
		
		WebClient webClient = WebClient.builder()
				.baseUrl(urlHead) 
				.defaultCookie("cookieKey", "cookieValue")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				.build();
		
		String result = "";
		
		if (method.equals(HttpMethod.GET)) {
			result = webClient.get()
					.uri(url)
					.header("x-auth-token", tokenPrefix + answerToken + tokenSeperator + userId)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		}
		
		if (method.equals(HttpMethod.POST)) {
			result = webClient.post()
					.uri(url)
					.header("x-auth-token", tokenPrefix + answerToken + tokenSeperator + userId)
					.bodyValue(body)
					.retrieve()
					.bodyToMono(String.class)
					.block();
		}
		
		return result;
	}

}
