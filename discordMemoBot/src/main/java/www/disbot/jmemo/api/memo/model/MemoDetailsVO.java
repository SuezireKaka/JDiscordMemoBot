package www.disbot.jmemo.api.memo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class MemoDetailsVO extends MemoVO {
	private String memo;
}
