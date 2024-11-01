package dimant.simulation.service;


import dimant.simulation.Coordinate;
import dimant.simulation.entity.Creature;
import dimant.simulation.intarfaces.IHerbivoreTargetSearch;

public class SearchHerbivoreService {
    private Coordinate target;
    private Coordinate nextCoordinateAboutHerbivore;
    private IHerbivoreTargetSearch search;

    public SearchHerbivoreService(IHerbivoreTargetSearch search) {
        this.search = search;
    }

//    public Coordinate getNextCoordinateCreature(Coordinate creatureCoordinate, Creature creature) {
//        Coordinate result;
//        if (target != null && search.hasGrassByTargetCoordinate(target)) {
//            result = search.nextCoordinateToTargetHerbivore(creatureCoordinate, target);
//        } else {
//            target = search.getCoordinateTargetAboutHerbivore(creatureCoordinate, creature);
//            result = search.nextCoordinateToTargetHerbivore(creatureCoordinate, target);
//
//        }
//
//        return result;
//    }


}