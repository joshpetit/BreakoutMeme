package breakout;

/**
 * The interface used to create events once {@link breakout.GameObject}s are hit.
 *
 * @author Joshua Petitma
 */
@FunctionalInterface
public interface HitCommand {

  /**
   * Runs the command that handles occurences after a HitEvent is emitted.
   *
   * @param e - The event describing the collision.
   */
  void execute(HitEvent e);
}
