/**
 * Random walk adjusted for the UpdateWithMarket strategy.
 * At each time tick, the person has some non-zero probability of
 * visiting the center for a given amount of time, then returning
 * to their last location.
 */
public class ShoppingRandomWalk extends RandomWalk {

  int stationaryDuration;
  Posn lastLocation;
  Posn shoppingLocation;

  ShoppingRandomWalk() {
    super();
    this.stationaryDuration = 0;
    this.lastLocation = null;
    this.shoppingLocation = new Posn(Constants.HALF_WIDTH, Constants.HALF_HEIGHT);
  }


  /**
   * Has a nonzero random probability of sending the person to the shopping center for a given amount of time.
   */
  @Override
  public void move() {

    // Stay stationary
    if (this.stationaryDuration > 0) {
      this.stationaryDuration--;

      // Send the person back to where they were
    } else if ((this.lastLocation != null) && (!this.lastLocation.equals(this.person.position))) {
      this.person.position.update(this.lastLocation);
      this.lastLocation = null;

      // Decide if they should go shopping
    } else if (Constants.random.nextFloat() < Constants.PROBABILITY_OF_SHOPPING) {
      this.lastLocation = new Posn(0, 0);
      this.lastLocation.update(this.person.position);
      this.person.position.update(this.shoppingLocation);
      this.stationaryDuration = Constants.SHOPPING_TIME;

      // Move regularly
    } else {
      super.move();
    }
  }
}
