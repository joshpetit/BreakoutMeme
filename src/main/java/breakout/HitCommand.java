package breakout;
@FunctionalInterface
interface HitCommand {
	void execute(HitEvent e);
}
