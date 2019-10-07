import oop.ex2.GameGUI;
import java.awt.*;

/**
 * a class that extends the spaceship class its made for the computer players
 */
public abstract class PursuitShip extends SpaceShip {

    /** number to turn right */
    final int RIGHT_TURN = -1;

    /** number to turn left */
    final int LEFT_TURN = 1;

    /** number to not turn */
    final int NO_TURN = 0;

    /** my distance from the nearest ship */
    private double distanceFromNearestShip;

    /** the nearest ship radians towards me */
    private double nearestShipToMeRadians;

    /** my radians towards the nearest ship */
    private double meToNearestShipRadians;

    /** find my nearest ship */
    private SpaceShip nearestShip;

    /*----=  Instance Methods  =-----*/

    /**
     * set a base for other classes in do action
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {

        // uses the action from the super class
        super.doAction(game);

        // get our nearest ship
        nearestShip = game.getClosestShipTo(this);

        // find the distance and radius from the nearest ship
        this.distanceFromNearestShip = this.getPhysics().distanceFrom(nearestShip.getPhysics());
        this.nearestShipToMeRadians  =  Math.toRadians(nearestShip.getPhysics().angleTo(this.getPhysics()));
        this.meToNearestShipRadians = Math.toRadians(this.getPhysics().angleTo(nearestShip.getPhysics()));

        // get our angle from the closest ship
        double angle = this.getPhysics().angleTo(nearestShip.getPhysics());

        // calculates the direction the ship need to move towards
        int directionNumber = this.getTurnDirection(angle);

        // moves the ship based on the direction number
        this.getPhysics().move(true, directionNumber);

    }

    /**
     * calculates what direction to turn to move towards the closets ship
     * @param angle calculates the angle from our closest ship
     * @return the direction number we should move in
     */
    protected int getTurnDirection(double angle){

        int returnInt;

        // checks what direction direction the spaceship should go in
        if(angle < 0){
            returnInt = this.RIGHT_TURN;
        }
        else if(angle > 0){
            returnInt = this.LEFT_TURN;
        }
        else {
            returnInt = this.NO_TURN;
        }

        // returns the direction the spaceship should turn
        return returnInt;
    }

    /**
     * gets the distance of the nearest ship
     * @return the distance of the nearest ship
     */
    public double getDistanceFromNearestShip() {
        return distanceFromNearestShip;
    }

    /**
     * gets my radians to the nearest ship
     * @return the angle degree in radians of the nearst ship towards this ship
     */
    public double getNearestShipToMeRadians() {
        return nearestShipToMeRadians;
    }

    /**
     * get the ships radians towards the nearest ship
     * @return this ships angle in radians towards the nearest ship
     */
    public double getMeToNearestShipRadians() {
        return meToNearestShipRadians;
    }


    /**
     * gets the computer ship image
     * @return the Computer spaceship image
     */
    @Override
    Image getShipImage() {
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * get the computers spaceship with shield image
     * @return the image with the shield
     */
    @Override
    Image getShipWithShieldImage() {
        return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    }
}
