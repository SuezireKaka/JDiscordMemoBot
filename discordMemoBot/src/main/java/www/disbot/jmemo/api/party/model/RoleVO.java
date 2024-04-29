package www.disbot.jmemo.api.party.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.disbot.jmemo.api.framework.model.Entity;

@Getter
@SuperBuilder
@NoArgsConstructor
public class RoleVO extends Entity {
	private GroupVO provider;
	private String name;
	private String info;
}
