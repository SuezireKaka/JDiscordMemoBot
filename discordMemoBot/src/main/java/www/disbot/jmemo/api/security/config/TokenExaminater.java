package www.disbot.jmemo.api.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.DiscordMemoBotApplication;

@Component
public class TokenExaminater {
	@Value("{discord.bot.token-prefix}")
	private String tokenPrefix;
	
	@Value("{discord.bot.token}")
	private String answerToken;
	
	//@Autowired
	//private final PartyMapper partyMapper; 
	
	public String resolveToken(HttpServletRequest request) {
        String requestHeader = request.getHeader("x-auth-token");
        String token = null;

        if (requestHeader != null && requestHeader.startsWith(tokenPrefix)) {
            //looking good
            token = requestHeader.substring(tokenPrefix.length());
        }
        return token; 
	}

	public boolean validateToken(String token) {
		return token.split("-||-")[0].equals(answerToken);
	}

	public Authentication getAuthentication(String token) {
		String userName = DiscordMemoBotApplication.main.retrieveUserById(getUserID(token))
			.map(User::getName)
			.complete();
		
		return null;
		
		//PartyVO partyDetails = partyMapper.findByUserName(userName);
		
		//return new UsernamePasswordAuthenticationToken(
		//		partyDetails, "", partyDetails.getAuthorities());
	}

	private String getUserID(String token) {
		return token.split("-||-")[1];
	}

}