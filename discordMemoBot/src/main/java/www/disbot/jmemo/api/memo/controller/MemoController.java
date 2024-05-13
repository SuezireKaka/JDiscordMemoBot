package www.disbot.jmemo.api.memo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.disbot.jmemo.api.framework.exception.BusinessException;
import www.disbot.jmemo.api.framework.model.structure.Page;
import www.disbot.jmemo.api.memo.model.MemoDTO;
import www.disbot.jmemo.api.memo.model.MemoDetailsVO;
import www.disbot.jmemo.api.memo.model.MemoVO;
import www.disbot.jmemo.api.memo.service.MemoService;
import www.disbot.jmemo.api.party.model.UserVO;
import www.disbot.jmemo.api.security.anno.MemberCheck;
import www.disbot.jmemo.bot.command.ApiCommand;

@RestController
@CrossOrigin
@RequestMapping(ApiCommand.MEMO)
public class MemoController {
	@Autowired
	private MemoService memoService;
	
	@GetMapping(ApiCommand.LIST_ALL + ApiCommand.PATH_PAGE)
	public ResponseEntity<Page<MemoVO>> listAllMemoes(@PathVariable int page) {
		Page<MemoVO> result = memoService.listAllMemoes(page);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(ApiCommand.BY_ID + ApiCommand.PATH_ID)
	public ResponseEntity<MemoDetailsVO> getMemoById(@PathVariable String id) {
		MemoDetailsVO result = memoService.getMemoById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(ApiCommand.CREATE)
	@MemberCheck
	public ResponseEntity<MemoDetailsVO> createMemo(
			@AuthenticationPrincipal UserVO user, @RequestBody MemoDTO memo)
			throws BusinessException {
		MemoDetailsVO result = memoService.createMemo(user, memo);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
