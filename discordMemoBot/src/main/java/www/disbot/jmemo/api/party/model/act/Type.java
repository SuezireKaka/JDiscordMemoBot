package www.disbot.jmemo.api.party.model.act;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
	Create("를 만들 수 있습니다"),
	Manage("를 관리할 수 있습니다"),
	
	Proxy("을 대표해 메모를 남길 수 있습니다"),
	Announce("의 멤버들에게 공지할 수 있습니다"),
	
	Invite("를 초대할 수 있습니다"),
	Ban("를 차단할 수 있습니다"),
	Grant("에게 역할을 부여할 수 있습니다"),
	Suspend("에게서 역할을 정지시킬 수 있습니다");
	
	private String info;
}
