/**
 * a Class of a basher ship its a type of computer ship
 * so it always runs into the nearest ship
 */
public class BasherShip extends PursuitShip {

    /** the distance to turn on the shield for */
    private final Double SHIELD_ON_DISTANCE = 0.20;

    /*----=  Instance Methods  =-----*/

    /**
     * expands the do action
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {

        super.doAction(game);

        // checks if close enough to turn on shield
        if (this.getDistanceFromNearestShip() < this.SHIELD_ON_DISTANCE){
            this.shieldOn();
        }

        this.finishDoAction();
    }

    /**
     * gets the type of ship
     * @return the ship type represented as an integer
     */
    @Override
    int getType() {
        return SpaceWars.BASHER_SHIP;
    }
}
