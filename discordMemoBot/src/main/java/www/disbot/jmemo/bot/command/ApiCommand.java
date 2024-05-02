package www.disbot.jmemo.bot.command;

import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import www.disbot.jmemo.api.security.model.EntryPointErrorResponse;
import www.disbot.jmemo.bot.command.api.RequestStrategy;

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
			EntryPointErrorResponse error = mapper.readValue(responseString, EntryPointErrorResponse.class);
			throw new Exception(error.getMsg());
		}
		
		return result;
	}
}
