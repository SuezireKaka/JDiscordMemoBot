package www.disbot.jmemo.api.memo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.disbot.jmemo.api.framework.model.TimeEntity;
import www.disbot.jmemo.api.party.model.PartyVO;

@Getter
@SuperBuilder
@NoArgsConstructor
public class MemoVO extends TimeEntity {
	private PartyVO writer;
	private String title;
}
