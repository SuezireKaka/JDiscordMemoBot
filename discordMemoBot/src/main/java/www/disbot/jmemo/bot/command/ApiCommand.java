package www.disbot.jmemo.bot.command;

import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import www.disbot.jmemo.api.framework.model.structure.Page;
import www.disbot.jmemo.api.security.model.EntryPointErrorResponse;
import www.disbot.jmemo.bot.command.api.RequestStrategy;
import www.disbot.jmemo.bot.exception.StrangeResponseException;

public abstract class ApiCommand implements Command {
	@Setter
	private RequestStrategy requestStrategy;
	
	public ApiCommand(RequestStrategy requestStrategy) {
		this.requestStrategy = requestStrategy;
	}
	
	protected <B, T> T requestTo(String urlTail, HttpMethod method, B body,
			Class<T> resultMapClass) throws Exception {
		String responseString = requestStrategy.requestTo(urlTail, method, body);
		
		ObjectMapper mapper = new ObjectMapper();
		
		T result = null;
		
		try {
			result = mapper.readValue(responseString, resultMapClass);
		}
		catch (JsonProcessingException jpe) {
			extractErrorMsg(resultMapClass, responseString, mapper, jpe);
		}
		
		return result;
	}
	
	protected <B, T> Page<T> requestTo(String urlTail, HttpMethod method, B body,
			Class<T> resultMapClass, TypeReference<Page<T>> ref) throws Exception {
		String responseString = requestStrategy.requestTo(urlTail, method, body);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Page<T> result = null;
		
		try {
			result = mapper.readValue(responseString, ref);
		}
		catch (JsonProcessingException jpe) {
			extractErrorMsg(resultMapClass, responseString, mapper, jpe);	
		}
		
		return result;
	}
	
	private <T> void extractErrorMsg(Class<T> resultMapClass, String responseString, ObjectMapper mapper,
			JsonProcessingException jpe) throws Exception, StrangeResponseException {
		try {
			jpe.printStackTrace();
			EntryPointErrorResponse error = mapper.readValue(responseString, EntryPointErrorResponse.class);
			throw new Exception(error.getMsg());
		}
		catch (JsonProcessingException fatalJpe) {
			throw new StrangeResponseException(resultMapClass.getName());
		}
	}
}
