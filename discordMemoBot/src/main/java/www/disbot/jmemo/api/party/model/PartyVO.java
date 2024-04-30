package www.disbot.jmemo.api.party.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.disbot.jmemo.api.framework.model.TimeEntity;

@Getter
@SuperBuilder
@NoArgsConstructor
public class PartyVO extends TimeEntity {
	private String name;
}
