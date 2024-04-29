package www.disbot.jmemo.api.party.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.disbot.jmemo.api.framework.model.TimeEntity;

@Getter
@SuperBuilder
@NoArgsConstructor
public class PartyVO extends TimeEntity {
	public String name;
	
	@Builder.Default
	private List<RoleVO> rolesList = new ArrayList<>();
}