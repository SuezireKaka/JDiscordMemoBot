package www.disbot.jmemo.listener.command;

public interface Command {
	public static final String PREFIX = "!";
	
	public String[] command(String[] args);
}
