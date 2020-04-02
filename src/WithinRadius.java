import java.util.function.Predicate;

/**
 * Distance metric which measures if a given Posn is within some finite distance 'radius'.
 */
public class WithinRadius implements Predicate<Posn> {


  float radius;
  Posn current;

  WithinRadius(float radius, Posn curr) {
    this.radius = radius;
    this.current = curr;
  }

  /**
   * Determines if the given Posn's distance from this Posn is at most 'radius' away.
   *
   * @param posn The given Posn.
   * @return {@code true} if posn is at most 'radius' close to this Posn.
   * otherwise {@code false}.
   */
  @Override
  public boolean test(Posn posn) {
    return Math.pow(this.radius, 2) >= (Math.pow(this.current.x - posn.x, 2) + Math.pow(this.current.y - posn.y, 2));
  }
}