package www.disbot.jmemo.api.party.model.act;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Range {
	System("시스템 전체"), Group("그룹"), Role("역할"), User("유저"), Private("개인 정보");
	
	private String str;
}
