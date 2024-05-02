package www.disbot.jmemo.api.party.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.disbot.jmemo.api.framework.mapper.GeneralMapper;
import www.disbot.jmemo.api.party.model.RoleVO;
import www.disbot.jmemo.api.party.model.UserVO;

@Mapper
public interface PartyMapper extends GeneralMapper {
	public List<RoleVO> listAllAnonymRoles();
	
	public UserVO getUserByName(String name);

	public boolean createUser(@Param("user") UserVO user);
	
}
