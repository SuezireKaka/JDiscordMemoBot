package www.disbot.jmemo.api.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.User;
import www.disbot.jmemo.DiscordMemoBotApplication;
import www.disbot.jmemo.api.framework.model.Entity;
import www.disbot.jmemo.api.party.mapper.PartyMapper;
import www.disbot.jmemo.api.party.model.RoleVO;
import www.disbot.jmemo.api.party.model.UserVO;

@Component
@RequiredArgsConstructor
public class TokenExaminater {
	private final PartyMapper partyMapper;
	
	@Value("${discord.bot.token-prefix}")
	private String tokenPrefix;
	
	@Value("${discord.bot.token-seperator}")
	private String tokenSeperator;
	
	@Value("${discord.bot.token}")
	private String answerToken;
	
	public String resolveToken(HttpServletRequest request) {
        String requestHeader = request.getHeader("x-auth-token");
        String token = null;

        if (requestHeader != null
        		&& requestHeader.startsWith(tokenPrefix)
        		&& requestHeader.split(tokenPrefix).length == 2) {
            //looking good
            token = requestHeader.substring(tokenPrefix.length());
        }
        return token; 
	}

	public boolean validateToken(String token) {
		String[] splitedToken = token.split(tokenSeperator);
		return splitedToken[0].equals(answerToken);
	}

	public Authentication getAuthentication(String token) {
		String userName = DiscordMemoBotApplication.main.retrieveUserById(getUserID(token))
			.map(User::getName)
			.complete();
		
		UserVO partyDetails = partyMapper.getUserByName(userName);
		
		// 여기까지 왔으면 미등록 유저라고 해도 Anonym View는 줘야 함
		if (partyDetails == null) {
			List<RoleVO> anonymRoleList = partyMapper.listAllAnonymRoles();
			
			partyDetails = UserVO.builder()
					.id(Entity.ANONYMOUS_ID)
					.name(userName)
					.roleList(anonymRoleList)
					.build();
		}
		
		return new UsernamePasswordAuthenticationToken(
				partyDetails, "", partyDetails.getAuthorities());
	}

	private String getUserID(String token) {
		return token.split(tokenSeperator)[1];
	}

}