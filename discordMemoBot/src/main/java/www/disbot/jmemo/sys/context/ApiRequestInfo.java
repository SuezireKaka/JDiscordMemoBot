package www.disbot.jmemo.sys.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ApiRequestInfo {
	@Value("${api.request.source.host}")
    private String sourceHost;
    
    @Value("${api.request.source.port}")
    private String sourcePort;
    
    @Value("${api.request.goal.host}")
    private String goalHost;
    
    @Value("${api.request.goal.port}")
    private String goalPort;
}
