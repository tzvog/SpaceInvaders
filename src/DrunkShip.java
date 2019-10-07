import java.util.Random;

/**
 * this ship is a computer ship its autopilot is set to chase closer ships
 * but cant tell distances therefore shoots at random disatances
 */
public class DrunkShip extends PursuitShip {

    private int roundCounter = 0;
    private final int WAITING_ROUNDS = 50;

    /*----=  Instance Methods  =-----*/
    /**
     * does the do action and fires
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {

        super.doAction(game);

        // creates an instance of random
        Random random = new Random();

        // waits if this round is a counter round
        if(this.roundCounter % WAITING_ROUNDS == 0)
        {
            // to drunk to calculate distance so wil randomly take a shot
            if(random.nextBoolean()){
                this.fire(game);
            }
        }

        this.roundCounter ++;

        this.finishDoAction();
    }

    /**
     * gets the type of ship
     * @return the ship type represented as an integer
     */
    @Override
    int getType() {
        return SpaceWars.DRUNKARD_SHIP;
    }
}
