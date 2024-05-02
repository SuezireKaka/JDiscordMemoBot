package www.disbot.jmemo.api.framework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class Entity {
	public static final String ANONYMOUS_ID = "----";
	
	private String id;
}
