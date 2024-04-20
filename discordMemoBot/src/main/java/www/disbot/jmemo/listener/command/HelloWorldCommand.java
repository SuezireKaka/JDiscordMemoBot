package www.disbot.jmemo.listener.command;

public class HelloWorldCommand implements Command {
	public static final String COMMAND = Command.PREFIX + "hello";

	public String command(String[] args) {
		if (args.length == 0) {
			return "Hello, world!";
		}
		return "";
	}

}
