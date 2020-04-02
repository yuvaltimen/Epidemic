/**
 * Strategy interface detailing how a Person object will move on each time tick.
 */
public interface MoveStrategy extends Strategy {

  /**
   * The method to update the Person's position and velocity.
   */
  void move();

}
