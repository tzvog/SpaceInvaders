import java.awt.*;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip{

    /** the max energy the Spaceship starts with */
    private final int START_MAX_ENERGY = 210;

    /** the current energy the Spaceship starts with */
    private final int START_CURRENT_ENERGY = 190;

    /** the health the Spaceship starts with */
    private final int START_HEALTH = 22;

    /** the cost it takes to fire */
    private final int FIRE_ENERGY_COST = 19;

    /** the cost it takes to teleport */
    private final int TELEPORT_ENERGY_COST = 140;

    /** the amount of energy you gain when you bash */
    private final int BASHING_ENERGY_BOOST = 18;

    /** the cost of getting hit on energy levels */
    private final int GOT_HIT_ENERGY_REDUCE = 10;

    /** minimum rounds need to take a shot*/
    private final int DEFAULT_SECONDS_SINCE_SHOT = 7;

    /** the cost it takes to use a shield */
    private final int SHIELD_ENERGY_COST = 3;

    /** our instances SpaceShipPhysics */
    private SpaceShipPhysics spaceShipPhysics;

    /** our SpaceShips max energy */
    private int maxEnergy;

    /** our SpaceShips current energy */
    private int currentEnergy;

    /** our SpaceShips health */
    private int health;

    /** our SpaceShips rounds since shot */
    private int roundsSinceShot;

    /** is Spaceships our shield on */
    private boolean isShieldOn;

    /*----=  Instance Methods  =-----*/

    /**
     * The constructor for the SpaceShip object
     */
    public SpaceShip(){
        this.setDefaults();
    }

    /**
     * A function to set defaults on a Spaceship object
     */
    private void setDefaults(){
        this.health = START_HEALTH;
        this.maxEnergy = START_MAX_ENERGY;
        this.currentEnergy = START_CURRENT_ENERGY;
        this.spaceShipPhysics = new SpaceShipPhysics();
        this.roundsSinceShot = this.DEFAULT_SECONDS_SINCE_SHOT;
    }

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {

        this.isShieldOn = false;
        this.roundsSinceShot ++;
    }

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){

        // checks if the shield is not on and if so just use the the got hit function
        if(!this.isShieldOn){
            this.gotHit();
        }
        else{
            // the shields are up so the energy levels just get boosted
            this.maxEnergy += this.BASHING_ENERGY_BOOST;
            this.currentEnergy += this.BASHING_ENERGY_BOOST;
        }

    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        this.setDefaults();
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {

        // if the health is 0 the ship is dead
        if(this.health == 0)
            return true;

        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {

        return this.spaceShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {

        // checks if the shield was on
        if (this.isShieldOn)
            return;

        // reduces based on the shield amount
        this.health --;
        this.maxEnergy -= this.GOT_HIT_ENERGY_REDUCE;

        // makes sure the current energy is in our range and sets it if not
        if (this.currentEnergy > this.maxEnergy)
            this.currentEnergy = this.maxEnergy;
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){

        // checks if the shield is on and if so ship image with the shield
        if(this.isShieldOn){
            return this.getShipWithShieldImage();
        }

        // return image of ship without shield
        return this.getShipImage();
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {

        // checks that the current energy level is not too low or shoot to often
        if(this.currentEnergy < this.FIRE_ENERGY_COST || roundsSinceShot < DEFAULT_SECONDS_SINCE_SHOT)
            return;

        // fires a shot
        game.addShot(this.spaceShipPhysics);

        // reduces the energy level
        this.currentEnergy -= this.FIRE_ENERGY_COST;

        // resets the counter on rounds since shot
        this.roundsSinceShot = 0;

    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {

        // checks if the current energy is high enough
        if(this.currentEnergy < this.SHIELD_ENERGY_COST)
            return;

        // turns the shield on
        this.isShieldOn = true;

        // reduces the current energy
        this.currentEnergy -= this.SHIELD_ENERGY_COST;
    }

    /**
     * tests and recoil while attempting to teleport.
     */
    public void teleport() {

        // checks that the current energy level is not too low
        if(this.currentEnergy < this.TELEPORT_ENERGY_COST)
            return;

        // executes a teleportation
        this.spaceShipPhysics = new SpaceShipPhysics();

        // reduces the energy level
        this.currentEnergy -= this.TELEPORT_ENERGY_COST;

    }

    /**
     * last steps for do action
     */
    protected void finishDoAction() {

        // checks if we are not giving to much energy
        if (this.currentEnergy < this.maxEnergy)
        {
            this.currentEnergy ++;
        }
    }

    /*----=  Abstract Methods  =-----*/

    /**
     * gets the type of ship
     * @return the ship type represented as an integer
     */
    abstract int getType();

    /**
     * gets the ship image
     * @return the ship image
     */
    abstract Image getShipImage();

    /**
     * gets the ship with shield image
     * @return the ship with the shield
     */
    abstract Image getShipWithShieldImage();
}
