/**
 * Implement a Random Walk strategy, where the velocity is randomly sampled from a set of possibilities
 * on each time step.
 */
public class RandomWalk extends AbstractMove {

  RandomWalk(Person person) {
    super(person);
  }

  RandomWalk() {
    super();
  }

  /**
   * Updates the Person's position and velocity.
   */
  @Override
  public void move() {

    float vx;
    float vy;

    if (this.person.position.x >= Constants.WIDTH - this.person.r) {
      vx = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (-Constants.MIN_VELOCITY));
    } else if (this.person.position.x <= this.person.r) {
      vx = (Constants.random.nextFloat() * Constants.MAX_VELOCITY);
    } else {
      vx = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
    }

    if (this.person.position.y >= Constants.HEIGHT - this.person.r) {
      vy = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (-Constants.MIN_VELOCITY));
    } else if (this.person.position.y <= this.person.r) {
      vy = (Constants.random.nextFloat() * Constants.MAX_VELOCITY);
    } else {
      vy = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
    }

    this.person.position.add(this.person.velocity);
    this.person.velocity.update(vx, vy);
    this.person.lastLocation.update(this.person.position);

  }
}
