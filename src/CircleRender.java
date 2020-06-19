/**
 * Simple rendering to draw each person as a circle.
 */
public class CircleRender extends AbstractRender {

  CircleRender() {

  }

  /**
   * Draw each EpidemicPerson as a circle centered at x, y.
   * Colors the circle according to their infection status,
   * and if infected, also includes an infection radius around them.
   */
  public void render() {

    switch (this.person.status) {
      case SUSCEPTIBLE:
        this.person.epidemic.fill(0, 255, 0);
        break;
      case INFECTED:
        this.person.epidemic.fill(255, 0, 0);
        break;
      case REMOVED:
        this.person.epidemic.fill(128,128,128);
        break;
      default:
        throw new IllegalStateException(String.format("Invalid state: {}", this.person.status));
    }
    this.person.epidemic.ellipse(this.person.position.x, this.person.position.y, Constants.RADIUS, Constants.RADIUS);

    if (this.person.status == Constants.Status.INFECTED) {
      this.person.epidemic.noFill();
      this.person.epidemic.ellipse(this.person.position.x, this.person.position.y, Constants.INFECTION_RADIUS, Constants.INFECTION_RADIUS);
    }

  }
}
