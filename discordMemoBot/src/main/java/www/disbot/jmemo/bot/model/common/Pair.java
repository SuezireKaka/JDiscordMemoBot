package www.disbot.jmemo.bot.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pair<F, S> {
	private F first;
	private S second;
}
