/**
 * UpdateStrategy for determining hpw PApplet.draw() gets executed at runtime.
 */
public interface UpdateStrategy extends Strategy {

  /**
   * Updates the state of Epidemic on each time tick by the PApplet.draw() method.
   */
  void update();

}
