import oop.ex2.GameGUI;
import java.awt.*;

/**
 * a special ship made to fire only at human ships
 */
public class SpecialShip extends SpaceShip {

    /**
     * the distance this ship should fire at
     */
    private final double FIRE_DISTANCE = 0.20;

    /*----=  Instance Methods  =-----*/

    /**
     * extends the do action function made for this ship
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {

        super.doAction(game);

        // gets the nearest
        SpaceShip nearestShip = game.getClosestShipTo(this);

        // checks if that ship is human and within firing range
        if(nearestShip.getType() == SpaceWars.HUMAN_CONTROLLED_SHIP &&
                this.getPhysics().angleTo(nearestShip.getPhysics()) < FIRE_DISTANCE){
            this.fire(game);
        }

        this.finishDoAction();
    }

    /**
     * get the type of the ship
     * @return the ships type
     */
    @Override
    int getType() {
        return SpaceWars.SPECIAL_SHIP;
    }

    /**
     * gets the image of the ship
     * @return returns the ship image
     */
    @Override
    Image getShipImage() {
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * gets the image of the ship with the shield
     * @return the image of the ship with the shield
     */
    @Override
    Image getShipWithShieldImage() {
        return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    }
}
