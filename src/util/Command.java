package util;

public interface Command {

	/**
	 * Executes the command
	 */
	void execute();

	/**
	 * Determines whether the command is a legal move in the game
	 *
	 * @return if command is legal
	 */
	boolean isLegal();
}
