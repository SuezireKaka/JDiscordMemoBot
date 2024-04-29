package www.disbot.jmemo.api.party.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.disbot.jmemo.api.framework.model.Entity;
import www.disbot.jmemo.api.party.model.act.GroupAct;

@Getter
@SuperBuilder
@NoArgsConstructor
public class RoleVO extends Entity {
	private GroupVO provider;
	private String name;
	private String info;
	
	@Builder.Default
	private List<GroupAct> allowedActList = new ArrayList<>();
}
