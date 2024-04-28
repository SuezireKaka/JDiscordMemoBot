package www.disbot.jmemo.bot.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class HelloWorldVO {
	private String message;
}
