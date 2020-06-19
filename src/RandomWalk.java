/**
 * Implement a Random Walk strategy, where the velocity is randomly sampled from a set of possibilities
 * on each time step.
 */
public class RandomWalk extends AbstractMove {

  RandomWalk() {
    super();
  }

  /**
   * Updates the EpidemicPerson's position and velocity.
   */
  @Override
  public void move() {

    float vx;
    float vy;

    if (this.person.position.x >= Constants.SCREEN_WIDTH - Constants.RADIUS) {
      vx = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (-Constants.MIN_VELOCITY));
    } else if (this.person.position.x <= Constants.RADIUS) {
      vx = (Constants.random.nextFloat() * Constants.MAX_VELOCITY);
    } else {
      vx = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
    }

    if (this.person.position.y >= Constants.SCREEN_HEIGHT - Constants.RADIUS) {
      vy = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (-Constants.MIN_VELOCITY));
    } else if (this.person.position.y <= Constants.RADIUS) {
      vy = (Constants.random.nextFloat() * Constants.MAX_VELOCITY);
    } else {
      vy = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
    }

    super.move();
    this.person.velocity.update(vx, vy);
    this.person.lastLocation.update(this.person.position);

  }
}
