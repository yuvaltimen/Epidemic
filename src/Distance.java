import java.util.function.BiFunction;

public class Distance implements BiFunction<Posn, Posn, Float> {
  /**
   * Applies this function to the given arguments.
   *
   * @param posn1  the first function argument
   * @param posn2 the second function argument
   * @return the function result
   */
  @Override
  public Float apply(Posn posn1, Posn posn2) {

    return (float)Math.sqrt(Math.pow(posn1.x - posn2.x, 2) + Math.pow(posn1.y - posn2.y, 2));

  }
}
