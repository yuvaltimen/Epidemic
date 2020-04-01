
public class Posn {

  float x;
  float y;

  Posn(float x, float y) {
    this.x = x;
    this.y = y;
  }

  void add(Posn other) {
    this.x += other.x;
    this.y += other.y;
  }

  void update(float newX, float newY) {
    this.x = newX;
    this.y = newY;
  }
}

