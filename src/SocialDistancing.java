import java.util.List;
import java.util.stream.Collectors;

/**
 * Moving Strategy that seeks to maximize distance from neighbors.
 */
public class SocialDistancing extends AbstractMove {

  /**
   * The method to update the Person's position and velocity.
   */
  @Override
  public void move() {

    Posn current = this.person.position;
    // Idea would be to find all neighbors within some avoidanceRadius,
    // and to find the sum of the velocities of all those people.
    // That would be this person's new velocity.
    Posn newVelocity = this.person.epidemic.population.stream()
            // All Person's within the given radius
            .filter(p -> new WithinDistance(Constants.AVOIDANCE_RADIUS, this.person.position).test(p.position))
            // Get their positions
            .map(p -> p.position)
            // Remove this Person's position
            .filter(p -> !p.equals(current))
            // Find vector distances to each from this Person
            .map(p -> Posn.add(current, Posn.negate(Posn.copy(p))))
            // Negate all vectors and scale by (1/abs) where abs = |magnitude|
            .map(p -> Posn.scale(Posn.negate(p), 1/p.magnitude()))
            .reduce(
                    new Posn(0, 0),
                    (p1, p2) -> Posn.add(p1, p2)
            );


    super.move();
    this.person.velocity.update(newVelocity);

  }
}
