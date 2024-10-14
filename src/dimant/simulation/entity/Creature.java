package dimant.simulation.entity;


import dimant.simulation.Coordinate;
import dimant.simulation.service.Navigator;
//import dimant.simulation.service.SearchRoute;

public class Creature extends Entity  {
    private Navigator navigator;

    public Creature() {
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void makeMove() {
        System.out.println(navigator.getTypeNextStep(this));

    }
}
