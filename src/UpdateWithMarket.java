import static processing.core.PConstants.RADIUS;

public class UpdateWithMarket extends AbstractUpdate {

  UpdateWithMarket(Epidemic epidemic) {
    super(epidemic);
  }


  @Override
  public void update() {
    this.epidemic.background(50);
    this.epidemic.fill(204, 100, 0);
    this.epidemic.rectMode(RADIUS);
    this.epidemic.rect(this.epidemic.market.x, this.epidemic.market.y, 10, 10);
    this.epidemic.noFill();
    this.epidemic.ellipse(this.epidemic.market.x, this.epidemic.market.y, 40, 40);
    for (Person p : this.epidemic.population) {
      p.render();
      p.update();
    }
  }

}
