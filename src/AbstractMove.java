public abstract class AbstractMove implements MoveStrategy {

  Person person;

  AbstractMove(EpidemicPerson person) {
    this.person = person;
  }

  AbstractMove() {

  }

  /**
   * The method to update the EpidemicPerson's position and velocity.
   */
  @Override
  public  void move() {
    this.person.position.add(this.person.velocity);
  }


  /**
   * Method to set a given MoveStrategy's EpidemicPerson field.
   */
  public void setPerson(Person p) {
    this.person = p;
  }
}
