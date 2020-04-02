public abstract class AbstractMove implements MoveStrategy {

  Person person;

  AbstractMove(Person person) {
    this.person = person;
  }

  /**
   * The method to update the Person's position and velocity.
   */
  @Override
  public abstract void move();
}
