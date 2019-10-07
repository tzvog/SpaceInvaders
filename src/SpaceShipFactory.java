/**
 * a factory class to build ships
 */
public class SpaceShipFactory {

    /**
     *  the main function to build a list of ships
     * @param args the command line arguments
     * @return a list with the ships
     */
    public static SpaceShip[] createSpaceShips(String[] args) {

        // creates a list of the ships to return
        SpaceShip [] shipList =  new SpaceShip[args.length];

        // goes through all the arguments
        for (int index = 0; index < args.length; index++)
        {
            // checks what type of ship was needed to be made
            switch(args[index]){
                case "a":
                    shipList[index] = new AggressiveShip();
                    break;
                case "b":
                    shipList[index] = new BasherShip();
                    break;
                case "r":
                    shipList[index] = new RunnerShip();
                    break;
                case "h":
                    shipList[index] = new HumanShip();
                    break;
                case "s":
                    shipList[index] = new SpecialShip();
                    break;
                case "d":
                    shipList[index] = new DrunkShip();
                    break;
                default:

            }
        }

        return shipList;
    }
}
