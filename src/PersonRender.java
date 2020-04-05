import processing.core.PShape;

/**
 * Rendering Strategy for drawing each person as.
 */
// TODO:
public class PersonRender extends AbstractRender {

  /**
   *
   */
  @Override
  public void render() {

    PShape shape = person.epidemic.loadShape("icon_1.svg");
    this.person.epidemic.noStroke();
    this.person.epidemic.shape(shape, this.person.position.x, this.person.position.y, Constants.RADIUS, Constants.RADIUS);


  }

}
