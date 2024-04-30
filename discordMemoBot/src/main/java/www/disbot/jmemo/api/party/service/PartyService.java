package www.disbot.jmemo.api.party.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.disbot.jmemo.api.party.mapper.PartyMapper;
import www.disbot.jmemo.api.security.model.SignUpDTO;
import www.disbot.jmemo.api.security.model.SignUpResultDTO;

@Service
public class PartyService {
	@Autowired
	PartyMapper partyMapper;

	public SignUpResultDTO createUser(SignUpDTO dto) {
		SignUpResultDTO result = new SignUpResultDTO();
		
		boolean success = partyMapper.createUser(dto);
		
		result.setSuccess(success);
		
		result.setMsg(success ? "등록이 완료되었어요!" : "등록에 실패했어요...");
		
		return result;
	}
	
	
}
