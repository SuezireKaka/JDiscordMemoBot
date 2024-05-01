package www.disbot.jmemo.bot.command.api;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiRequester {
	@NonNull
	private String goalHost;
	@NonNull
	private String goalPort;
	@NonNull
	private String tokenPrefix;
	@NonNull
	private String tokenSeperator;
	
}
