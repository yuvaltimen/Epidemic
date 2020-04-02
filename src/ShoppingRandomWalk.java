/**
 * Random walk adjusted for the UpdateWithMarket strategy.
 * At each time tick, the person has some non-zero probability of
 * visiting the center for a given amount of time, then returning
 * to their last location.
 */
public class ShoppingRandomWalk extends RandomWalk {

  int stationaryDuration;
  Posn lastLocation;

  ShoppingRandomWalk(Person person) {
    super(person);
    this.stationaryDuration = 0;
    this.lastLocation = null;
  }

  ShoppingRandomWalk() {
    super();
    this.stationaryDuration = 0;
    this.lastLocation = null;
  }


  @Override
  public void move() {

    // If the person is in the city center, decrement stationaryDur and exit
    if (this.stationaryDuration > 0) {
      this.stationaryDuration--;
      return;
    } else if (this.lastLocation != null) {
      this.person.position.update(this.lastLocation);
      this.lastLocation = null;
    } else {
      super.move();
    }
  }
}
