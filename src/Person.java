public class Person {
  Posn position;
  Posn velocity;
  MoveStrategy moveStrategy;
  RenderStrategy renderStrategy;

  Person(MoveStrategy moveStrategy, RenderStrategy renderStrategy, float x, float y, float vx, float vy) {
    this.moveStrategy = moveStrategy;
    this.moveStrategy.setPerson(this);
    this.renderStrategy = renderStrategy;
    this.renderStrategy.setPerson(this);
  }

}
