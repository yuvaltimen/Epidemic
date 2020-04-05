/**
 * Abstract base class for a RenderStrategy.
 */
public abstract class AbstractRender implements RenderStrategy {

  Person person;

  AbstractRender() {

  }

  /**
   * Renders this Strategy's Person object.
   */
  @Override
  public abstract void render();

  /**
   * Sets this RenderStrategy's Person object/
   * @param p the Person to set.
   */
  @Override
  public void setPerson(Person p) {
    this.person = p;
  }
}
