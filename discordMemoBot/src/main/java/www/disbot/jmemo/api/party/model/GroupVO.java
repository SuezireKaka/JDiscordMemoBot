package www.disbot.jmemo.api.party.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class GroupVO extends PartyVO {
	private static final long serialVersionUID = 1L;
	
	private boolean isOpen;
	private String introduce;
}
