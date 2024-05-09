package www.disbot.jmemo.api.memo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoDTO {
	private String title;
	private String memo;
	private boolean isPublic;
}
