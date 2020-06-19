/**
 * Abstract base class for the Update strategy for an Epidemic class.
 * Determines how the PApplet.draw() method gets executed at runtime.
 * Subclassed by UpdateWithMarket and UpdateWithoutMarket, to differentiate between
 * simulating an epidemic with a central marketplace or not.
 */
public abstract class AbstractUpdate implements UpdateStrategy {

  Epidemic epidemic;


  AbstractUpdate(Epidemic epidemic) {
    this.epidemic = epidemic;
  }

  /**
   * Update method for the epidemic.
   */
  @Override
  public void update() {
    this.epidemic.background(50);
    for (EpidemicPerson p : this.epidemic.population) {
      p.renderStrategy.render();
      p.update();
    }

  }
}
