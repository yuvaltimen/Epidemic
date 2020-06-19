/**
 * Strategy interface detailing how a EpidemicPerson object will move on each time tick.
 */
public interface MoveStrategy extends Strategy {

  /**
   * The method to update the EpidemicPerson's position and velocity.
   */
  void move();

  /**
   * Method to set a given MoveStrategy's EpidemicPerson field.
   */
  void setPerson(Person p);

}
