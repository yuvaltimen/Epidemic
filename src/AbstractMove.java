public abstract class AbstractMove implements MoveStrategy {

  Person person;

  AbstractMove(Person person) {
    this.person = person;
  }

  AbstractMove() {

  }

  /**
   * The method to update the Person's position and velocity.
   */
  @Override
  public abstract void move();


  /**
   * Method to set a given MoveStrategy's Person field.
   */
  public void setPerson(Person p) {
    this.person = p;
  }
}
