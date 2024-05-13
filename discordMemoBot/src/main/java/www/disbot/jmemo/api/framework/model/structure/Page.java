package www.disbot.jmemo.api.framework.model.structure;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(exclude = {"offset"})
@NoArgsConstructor
@JsonIgnoreProperties({
	"onePageNum",
	"pageTerminology",
	"pageForm",
	"contentsTerminology"})
public class Page<T> {
	public static final int ONE_PAGE_NUM = 10;
	
	public static final String PAGE_TERMINOLOGY = "page";
	public static final String PAGE_FORM = "%d / %d";
	
	public static final String CONTENTS_TERMINOLOGY = "contents";
	
	private int nowPage;
	private long offset;
	
	@Setter
	private int totalPage;
	
	private List<T> contents = new ArrayList<>();
	
	public Page(int nowPage) {
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
