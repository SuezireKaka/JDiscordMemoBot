package www.disbot.jmemo.listener.command.common;

import java.util.ArrayList;
import java.util.List;

public class MessageOrganizer {
	public static final int MAX_MESSAGE_LENGTH = 1000;
	
	private static final MessageOrganizer INSTANCE = new MessageOrganizer();
	
	public static MessageOrganizer getInstance() {
		return INSTANCE;
	}
	
	private MessageOrganizer() {};
	
	public String[] organizeForDiscord(String[] rawArray) {
		List<String> lemma = new ArrayList<>();
		
		String currentResult = "";
		
		for (String msg : rawArray) {
			
    		if (msg.length() > MAX_MESSAGE_LENGTH) {
    			// 한 결과가 최대 길이보다 길다면? 일단 무시
    			continue;
    		}
    		
    		// 나중에 전략패턴으로 어떻게 합칠지 정할 것 - 당장은 엔터로 합치기
    		if ((currentResult + msg).length() > MAX_MESSAGE_LENGTH) {
    			lemma.add(currentResult);
    			currentResult = "";
    		}
    		else {
    			currentResult += msg;
    		}
    	}
		
		lemma.add(currentResult);
		
		return lemma.toArray(new String[0]);
	}
}
