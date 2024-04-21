package www.disbot.jmemo.bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class CommandVO {
	public static final String DICT_SEPERATOR = ": ";
	
	private String command;
	private String explain;
	
	@Override
	public String toString() {
		return command + DICT_SEPERATOR + explain;
	}
}
