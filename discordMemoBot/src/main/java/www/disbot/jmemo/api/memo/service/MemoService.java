package www.disbot.jmemo.api.memo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.disbot.jmemo.api.framework.exception.BusinessException;
import www.disbot.jmemo.api.framework.model.structure.Page;
import www.disbot.jmemo.api.memo.mapper.MemoMapper;
import www.disbot.jmemo.api.memo.model.MemoDTO;
import www.disbot.jmemo.api.memo.model.MemoDetailsVO;
import www.disbot.jmemo.api.memo.model.MemoVO;
import www.disbot.jmemo.api.party.model.UserVO;

@Service
public class MemoService {
	@Autowired
	private MemoMapper memoMapper;
	
	public Page<MemoVO> listAllMemoes(int pageNum) {
		Page<MemoVO> page = new Page<>(pageNum, new ArrayList<>());
		
		List<MemoVO> result = memoMapper.listAllMemoes(page);
		page.addAll(result);
		
		int foundNumber = memoMapper.getFoundRows();
		page.applyFoundNumber(foundNumber);
		
		return page;
	}
	
	public MemoDetailsVO getMemoById(String id) {
		return memoMapper.getMemoById(id);
	}

	public MemoDetailsVO createMemo(UserVO user, MemoDTO memo) throws BusinessException {
		memoMapper.createMemo(user, memo);
		
		return memoMapper.getLatestMemoOf(user);
	}

}
