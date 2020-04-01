import java.util.function.Predicate;

public class WithinRadius implements Predicate<Posn> {


  float radius;
  Posn current;

  WithinRadius(float radius, Posn curr) {
    this.radius = radius;
    this.current = curr;
  }

  /**
   * Evaluates this predicate on the given argument.
   *
   * @param posn the input argument
   * @return {@code true} if the input argument matches the predicate,
   * otherwise {@code false}
   */
  @Override
  public boolean test(Posn posn) {
    return Math.pow(this.radius, 2) >= (Math.pow(this.current.x - posn.x, 2) + Math.pow(this.current.y - posn.y, 2));
  }
}