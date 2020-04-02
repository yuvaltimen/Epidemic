
public class AbstractUpdate implements UpdateStrategy {

  Epidemic epidemic;


  AbstractUpdate(Epidemic epidemic) {
    this.epidemic = epidemic;
  }

  @Override
  public void update() {
    this.epidemic.background(50);
    for (Person p : this.epidemic.population) {
      p.render();
      p.update();
    }

  }
}
