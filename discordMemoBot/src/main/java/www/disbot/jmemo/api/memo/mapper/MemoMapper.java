package www.disbot.jmemo.api.memo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.disbot.jmemo.api.framework.mapper.GeneralMapper;
import www.disbot.jmemo.api.framework.model.structure.Page;
import www.disbot.jmemo.api.memo.model.MemoDTO;
import www.disbot.jmemo.api.memo.model.MemoDetailsVO;
import www.disbot.jmemo.api.memo.model.MemoVO;
import www.disbot.jmemo.api.party.model.UserVO;

@Mapper
public interface MemoMapper extends GeneralMapper {
	public List<MemoVO> listAllMemoes(Page<MemoVO> page);
	
	public MemoDetailsVO getMemoById(String id);
	
	public MemoDetailsVO getLatestMemoOf(UserVO user);

	public int createMemo(@Param("user") UserVO user, @Param("memo") MemoDTO memo);

}
