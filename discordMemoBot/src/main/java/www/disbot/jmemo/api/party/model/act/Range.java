package www.disbot.jmemo.api.party.model.act;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Range {
	Group("그룹"), Role("역할"), User("유저");
	
	private String str;
}
