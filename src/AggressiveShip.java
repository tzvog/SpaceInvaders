/**
 * this class extends the Computer ship and makes an aggressive ship
 */
public class AggressiveShip extends PursuitShip {

    /**
     * the angle to fire at
     */
    private final double FIRE_ANGLE = 0.21;

    /*----=  Instance Methods  =-----*/

    /**
     * does the action for the basher ship
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {

        // uses the parents do action
        super.doAction(game);

        // checks if the ship is in postion to fire
        if (this.getMeToNearestShipRadians() < FIRE_ANGLE){
            this.fire(game);
        }

        // finishes up the do action
        this.finishDoAction();
    }


    /**
     * gets the type of ship
     * @return the ship type represented as an integer
     */
    @Override
    int getType() {
        return SpaceWars.AGGRESSIVE_SHIP;
    }
}
