package www.disbot.jmemo.bot.view;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageEmbed;
import www.disbot.jmemo.bot.model.GreetingVO;
import www.disbot.jmemo.bot.view.user.AvatarCatcher;

@Getter
@AllArgsConstructor
public class GreetingView extends DiscordView {
	public static final String GREETINNG_TITLE = "%s%s 등장!";
	public static final String GREETINNG_DESCRIPTION = "새로운 %s을 환영합니다~!";
	
	public static final String NEW_MEMBER = "새로 들어온 %s";
	
	public static final String PERSON_SUFFIX = "님";
	public static final String PERSON_TERM_OF_ADDRESS = "유저분";
	
	public static final String BOT_SUFFIX = "";
	public static final String BOT_TERM_OF_ADDRESS = "봇";
	
	private GreetingVO rawVO;

	@Override
	public void initEmbed() {
		setEmbedBuilder(this.getEmbedBuilder()
				.setTitle(GREETINNG_TITLE.formatted(
						rawVO.getEffectiveName(),
						rawVO.isBot()
							? BOT_SUFFIX
							: PERSON_SUFFIX))
				.setDescription(GREETINNG_DESCRIPTION.formatted(
						rawVO.isBot()
							? BOT_TERM_OF_ADDRESS
							: PERSON_TERM_OF_ADDRESS))
				.setColor(SUCCESS_COLOR));
		
	}

	@Override
	public List<String> textify() {
		List<String> mappedVOList = new ArrayList<>();
		mappedVOList.add(String.join(LIST_SEPERATOR,
				rawVO.getAvatarUrl(), rawVO.getEffectiveName()));
		
		return rearrangeWithDiscordLimit(mappedVOList);
	}

	@Override
	public MessageEmbed closeWith(String value) throws Exception {
		String[] userInfoArray = value.split(LIST_SEPERATOR);
		String url = userInfoArray[0];
		String nick = userInfoArray[1];
		
		AvatarCatcher catcher = new AvatarCatcher();
		
		return this.getEmbedBuilder()
				.setImage(url)
				.addField(NEW_MEMBER
						.formatted(rawVO.isBot()
								? BOT_TERM_OF_ADDRESS
								: PERSON_TERM_OF_ADDRESS)
						, nick, false)
				.build();
	}

}
