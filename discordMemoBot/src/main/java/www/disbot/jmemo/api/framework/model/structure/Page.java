package www.disbot.jmemo.api.framework.model.structure;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(exclude = {"offset"})
public class Page<T> {
	public static final int ONE_PAGE_NUM = 10;
	
	private long nowPage;
	private long offset;
	
	@Setter
	private long totalPage;
	
	private List<T> contents = new ArrayList<>();
	
	public Page(long nowPage) {
		this.nowPage = nowPage;
		this.offset = (nowPage - 1) * ONE_PAGE_NUM;
	}
	
	public Page<T> addAll(List<T> addList) {
		contents.addAll(addList);
		return this;
	}
	
	public int getOnePageNum() {
		return ONE_PAGE_NUM;
	}
	
}
