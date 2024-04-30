package www.disbot.jmemo.api.party.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.disbot.jmemo.api.framework.model.Entity;
import www.disbot.jmemo.api.party.model.act.Act;

@Getter
@SuperBuilder
@NoArgsConstructor
public class RoleVO extends Entity implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	public static final String AUTHORITY_SEPERATOR = "-";
	
	private GroupVO provider;
	private String name;
	private String info;
	private boolean isDefault;
	
	@Builder.Default
	private List<Act> allowedActList = new ArrayList<>();

	@Override
	public String getAuthority() {
		return provider.getId() + AUTHORITY_SEPERATOR + name;
	}
}
