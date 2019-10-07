/**
 * Runner ship extends the SpaceSHip class
 * as a ship that always runs from a fight
 *
 * @author oop
 */
public class RunnerShip extends PursuitShip {

    /** the distance the ship feels threatened */
    private final double THREATENING_DISTANCE = 0.25;

    /** the radius the ship feels threatened */
    private final double THREATENING_ANGLE = 0.23;

    /*----=  Instance Methods  =-----*/

    /**
     * extends and overrides the Spaceship classes do action class
     * @param game the game object to which this ship belongs.
     */
    // does the action
    @Override
    public void doAction(SpaceWars game) {

        // check if we are in need to teleport
        if (this.getDistanceFromNearestShip() < THREATENING_DISTANCE &&
                this.getNearestShipToMeRadians() < THREATENING_ANGLE){
            this.teleport();
        }

        super.doAction(game);

        // adds to the current energy level
        this.finishDoAction();
    }

    /**
     * calculates the direction the spaceship should turn in
     * @param angle calculates the angle from our closest ship
     * @return the direction number we should move in
     */
    @Override
    protected int getTurnDirection(double angle){

        int returnInt;

        // checks what direction direction the spaceship should go in
        if(angle < 0){
            returnInt = this.LEFT_TURN;
        }
        else if(angle > 0){
            returnInt = this.RIGHT_TURN;
        }
        else {
            returnInt = this.NO_TURN;
        }

        // returns the direction the spaceship should turn
        return returnInt;
    }

    /**
     * gets the type of ship
     * @return the ship type represented as an integer
     */
    @Override
    int getType() {
        return SpaceWars.RUNNER_SHIP;
    }
}
