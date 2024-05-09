package www.disbot.jmemo.bot.exception;

import java.math.BigInteger;

public class UnexpectedArgsException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static final String COMMON_MESSAGE = "Unexpected argument found: ";

	public UnexpectedArgsException(String[] expected, String cause) {
		super(COMMON_MESSAGE
				+ "expected \"%s\" but found \"%s\"."
					.formatted(String.join(" or ", expected), cause));
	}
	
	public UnexpectedArgsException(BigInteger min, BigInteger max, String cause) {
		super(COMMON_MESSAGE
				+ "expected number between %s and %s but found \"%s\"."
					.formatted(min.toString(), max.toString(), cause));
	}
}
