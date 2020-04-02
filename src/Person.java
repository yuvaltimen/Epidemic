import java.util.List;
import java.util.stream.Collectors;

/**
 * A person.
 */
public class Person {

  Epidemic epidemic;
  float r = Constants.RADIUS;
  Posn position;
  Posn velocity;
  int timeInfected;
  Constants.Status status;
  Posn lastLocation;
  MoveStrategy moveStrategy;


  Person(Epidemic sketch, MoveStrategy moveStrategy, float x, float y, float vx, float vy) {
    this.epidemic = sketch;
    this.position = new Posn(x, y);
    this.lastLocation = new Posn(x, y);
    this.velocity = new Posn(vx, vy);
    boolean s = Constants.random.nextDouble() < Constants.INITIAL_INFECTED_PROPORTION;
    if (s) {
      this.status = Constants.Status.INFECTED;
    } else {
      this.status = Constants.Status.SUCCEPTIBLE;
    }
    this.timeInfected = 0;
    this.moveStrategy = moveStrategy;
    this.moveStrategy.setPerson(this);
  }


  /**
   * Draws the image of the given person.
   * @return WorldImage representing the person.
   */
  void render() {
    switch (this.status) {
      case SUCCEPTIBLE:
        this.epidemic.fill(0, 255, 0);
        break;
      case INFECTED:
        this.epidemic.fill(255, 0, 0);
        break;
      case REMOVED:
        this.epidemic.fill(128,128,128);
        break;
      default:
        throw new IllegalStateException(String.format("Invalid state: {}", this.status));
    }
    this.epidemic.ellipse(this.position.x, this.position.y, Constants.RADIUS, Constants.RADIUS);

    if (this.status == Constants.Status.INFECTED) {
      epidemic.noFill();
      this.epidemic.ellipse(this.position.x, this.position.y, Constants.INFECTION_RADIUS, Constants.INFECTION_RADIUS);
    }
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
   * SUCCEPTIBLE - gets infected with probability q if in some radius R of a sick person.
   * INFECTED - gets removed after some time T.
   * REMOVED - stays that way.
   */
  void updateStatus() {

    switch (this.status) {

      // If you are succeptible, then each person near you has a Q chance of infecting you.
      case SUCCEPTIBLE:

        List<Person> infectedNearby = this.epidemic.population.stream()
                .filter(person -> new WithinRadius(Constants.INFECTION_RADIUS, this.position).test(person.position))
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

