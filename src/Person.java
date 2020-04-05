import java.util.List;
import java.util.stream.Collectors;

/**
 * A person.
 */
public class Person {
  // TODO: override equals

  Epidemic epidemic;
  Posn position;
  Posn velocity;
  int timeInfected;
  Constants.Status status;
  Posn lastLocation;
  MoveStrategy moveStrategy;
  RenderStrategy renderStrategy;

  Person(Epidemic sketch, MoveStrategy moveStrategy, RenderStrategy renderStrategy, float x, float y, float vx, float vy) {
    this.epidemic = sketch;
    this.position = new Posn(x, y);
    this.lastLocation = new Posn(x, y);
    this.velocity = new Posn(vx, vy);
    boolean s = Constants.random.nextDouble() < Constants.INITIAL_INFECTED_PROPORTION;
    if (s) {
      this.status = Constants.Status.INFECTED;
    } else {
      this.status = Constants.Status.SUSCEPTIBLE;
    }
    this.timeInfected = 0;
    this.moveStrategy = moveStrategy;
    this.moveStrategy.setPerson(this);
    this.renderStrategy = renderStrategy;
    this.renderStrategy.setPerson(this);
  }


  /**
   * Updates the Person's fields on each tick.
   */
  void update() {
    this.moveStrategy.move();
    // TODO: UpdateStatus --> strategy
    this.updateStatus();
  }


  /**
   * SUSCEPTIBLE - gets infected with probability q if in some radius R of a sick person.
   * INFECTED - gets removed after some time T.
   * REMOVED - stays that way.
   */
  void updateStatus() {

    switch (this.status) {

      // If you are succeptible, then each person near you has a Q chance of infecting you.
      case SUSCEPTIBLE:

        List<Person> infectedNearby = this.epidemic.population.stream()
                .filter(person -> new WithinDistance(Constants.INFECTION_RADIUS, this.position).test(person.position))
                .filter(person -> (person.status == Constants.Status.INFECTED))
                .collect(Collectors.toList());


        float probOfInfection;
        // The number of people near you increase your odds of infection:
        // 0  -> 0
        // 1  -> 0.02
        // 2  -> 0.07
        // 3  -> 0.2
        // 4  -> 0.4
        // 5+ -> 1
        switch(infectedNearby.size()) {
          case 0:
            probOfInfection = 0f;
            break;
          case 1:
            probOfInfection = 0.02f;
            break;
          case 2:
            probOfInfection = 0.07f;
            break;
          case 3:
            probOfInfection = 0.2f;
            break;
          case 4:
            probOfInfection = 0.4f;
            break;
          default:
            probOfInfection = 1f;
        }

        if (Constants.random.nextFloat() <= probOfInfection) {
          this.status = Constants.Status.INFECTED;
        }

        break;

      case INFECTED:
        this.timeInfected++;
        if (this.timeInfected > Constants.RECOVERY_TIME) {
          this.status = Constants.Status.REMOVED;
        }
        break;
      case REMOVED:
        break;
      default:
        throw new IllegalStateException(String.format("Invalid state: {}", this.status));

    }
  }


}

