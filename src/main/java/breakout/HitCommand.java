package breakout;
@FunctionalInterface
interface HitCommand {
	/**
	 * Runs the command that handles occurences after a HitEvent is emitted.
	 *
	 * @param e - The event describing the collision.
	 */
	void execute(HitEvent e);
}
