/**
 * Abstract base class for a RenderStrategy.
 */
public abstract class AbstractRender implements RenderStrategy {

  Person person;

  AbstractRender() {

  }

  /**
   * Renders this Strategy's EpidemicPerson object.
   */
  @Override
  public abstract void render();

  /**
   * Sets this RenderStrategy's EpidemicPerson object/
   * @param p the EpidemicPerson to set.
   */
  @Override
  public void setPerson(Person p) {
    this.person = p;
  }
}
