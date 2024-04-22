package www.disbot.jmemo.bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class GreetingVO {
	private String effectiveName;
	private String avatarUrl;
	private boolean isBot;
}
