import static processing.core.PConstants.RADIUS;

/**
 * Strategy for updating the Epidemic with a central market location.
 * Each time tick, all Persons in the epidemic's population have some non-zero
 * probability of visiting the center for a given amount of time, then returning
 * to their last location.
 */
public class UpdateWithMarket extends AbstractUpdate {

  UpdateWithMarket(Epidemic epidemic) {
    super(epidemic);
  }

  /**
   * Updates and draws the Epidemic state on each time tick.
   * Draws the market as an orange square in the center.
   */
  @Override
  public void update() {
    this.epidemic.background(50);
    this.epidemic.fill(204, 100, 0);
    this.epidemic.rectMode(RADIUS);
    this.epidemic.rect(Constants.HALF_WIDTH, Constants.HALF_HEIGHT, 10, 10);
    this.epidemic.noFill();
    this.epidemic.ellipse(Constants.HALF_WIDTH, Constants.HALF_HEIGHT, 40, 40);
    for (Person p : this.epidemic.population) {
      p.renderStrategy.render();
      p.update();
    }
  }

}
