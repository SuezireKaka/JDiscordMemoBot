package www.disbot.jmemo.api.party.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.disbot.jmemo.api.framework.mapper.GeneralMapper;
import www.disbot.jmemo.api.party.model.PartyVO;
import www.disbot.jmemo.api.security.model.SignUpDTO;

@Mapper
public interface PartyMapper extends GeneralMapper {
	
	public PartyVO getUserByName(String name);

	public boolean createUser(@Param("dto") SignUpDTO dto);
	
}
