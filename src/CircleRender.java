public class CircleRender implements RenderStrategy {

  Person person;

  CircleRender() {

  }

  public void setPerson(Person p) {
    this.person = p;
  }

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
