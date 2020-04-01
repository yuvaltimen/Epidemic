
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

  void update(Posn other) {
    this.x = other.x;
    this.y = other.y;
  }


  @Override
  public int hashCode() {
    return (int)Math.floor(this.x + this.y);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Posn) {
      Posn p = (Posn)other;
      return (this.x == p.x) && (this.y == p.y);
    }
    return false;

  }
}

