import java.awt.*;
import oop.ex2.*;

/**
 * a class the extends spaceship made for the human player
 */
public class HumanShip extends SpaceShip {

    /** number to turn right */
    final int RIGHT_TURN = -1;

    /** number to left */
    final int LEFT_TURN = 1;


    /*----=  Instance Methods  =-----*/

    /**
     * does the action for the human ship
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        super.doAction(game);

        int direction = 0;
        boolean acceletate = false;

        // checks if a teleport was called
        if (game.getGUI().isTeleportPressed()){
            this.teleport();
        }

        // checks if going left was called
        if (game.getGUI().isLeftPressed()){
            direction += this.LEFT_TURN;
        }

        // checks if going right was called
        if (game.getGUI().isRightPressed()){
            direction += this.RIGHT_TURN;
        }

        // checks if accelerate was called
        if (game.getGUI().isUpPressed()){
            acceletate = true;
        }

        // moves the ship
        this.getPhysics().move(acceletate, direction);

        // checks if sheild was turned on
        if (game.getGUI().isShieldsPressed()){
            this.shieldOn();
        }

        // checks if a shot was pressed
        if(game.getGUI().isShotPressed()){
            this.fire(game);
        }

        this.finishDoAction();
    }

    /**
     * gets the type of ship
     * @return the ship type represented as an integer
     */
    @Override
    int getType() {
        return SpaceWars.HUMAN_CONTROLLED_SHIP;
    }

    /**
     * gets ship image
     * @return the ship image
     */
    @Override
    Image getShipImage() {
        return GameGUI.SPACESHIP_IMAGE;
    }

    /**
     * gets the ship image with shield
     * @return the sheild image with shield
     */
    @Override
    Image getShipWithShieldImage() {
        return GameGUI.SPACESHIP_IMAGE_SHIELD;
    }
}
