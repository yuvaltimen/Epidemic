/**
 * Posn class for representing a 2-D vector in space.
 * Can represent position, velocity, or any other 2-D coordinate.
 */
public class Posn {

  float x;
  float y;

  Posn(float x, float y) {
    this.x = x;
    this.y = y;
  }


  /**
   * =========== STATIC ===========
   */

  /**
   * Scales the given Posn by the given scalar.
   */
  static Posn scale(Posn p, float scalar) {
    return new Posn(p.x * scalar, p.y * scalar);
  }

  /**
   * Static method for adding 2 Posns.
   */
  static Posn add(Posn p1, Posn p2) {
    return new Posn(p1.x + p2.x, p1.y + p2.y);
  }

  /**
   * Copy this Posn.
   */
  static Posn copy(Posn p) {
    return new Posn(p.x, p.y);
  }

  /**
   * Negation of this Posn.
   */
  static Posn negate(Posn p) {
    return new Posn(-p.x, -p.y);
  }


  /**
   * ========= NON-STATIC =========
   */

  /**
   * Scales this Posn by the given scalar.
   */
  void scale(float scalar) {
    this.x *= scalar;
    this.y *= scalar;
  }

  /**
   * Adds the given Posn's fields to this one's.
   * @param other
   */
  void add(Posn other) {
    this.x += other.x;
    this.y += other.y;
  }

  /**
   * Calculates the magnitude of this Posn.
   * @return The distance to zero as a float.
   */
  float magnitude() {
    return new Distance().apply(new Posn(0, 0), this);
  }


  /**
   * Negation of this Posn.
   */
  void negate() {
    this.x *= -1;
    this.x *= -1;
  }

  /**
   * Overwrites this Posn's fields with the ones supplied.
   * @param newX The new X coordinate.
   * @param newY The new Y coordinate.
   */
  void update(float newX, float newY) {
    this.x = newX;
    this.y = newY;
  }

  /**
   * Overwrites this Posn's fields with the ones of the Posn supplied.
   * @param other The new Posn.
   */
  void update(Posn other) {
    this.x = other.x;
    this.y = other.y;
  }

  /**
   * Must override hashCode due to override equals.
   * @return int - hashCode.
   */
  @Override
  public int hashCode() {
    return (int)Math.floor(this.x + this.y);
  }

  /**
   * Compares the given object to this Posn to see if they are equal.
   * @param other the Object to be compared.
   * @return true if the Object is equal to this Posn.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Posn) {
      Posn p = (Posn)other;
      return (this.x == p.x) && (this.y == p.y);
    }
    return false;

  }
}

