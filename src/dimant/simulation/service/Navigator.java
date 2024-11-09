package dimant.simulation.service;


import dimant.simulation.Coordinate;
import dimant.simulation.entity.Creature;


public class Navigator {
    private final SearchRoute searchRoute;
    private final ScannerType scannerType;
    private final EntityService entityService;
    private Coordinate nextCoordinateCreature;

    public Navigator(SearchRoute searchRoute, ScannerType scannerType, EntityService entityService) {
        this.searchRoute = searchRoute;
        this.scannerType = scannerType;
        this.entityService = entityService;
    }

    public String getTypeNextStep(Creature creature) {
        Coordinate result = nextCoordinateAboutCurrent(creature);
        nextCoordinateCreature = result;
        return scannerType.getEntityType(result);
    }

    public Coordinate nextCoordinateAboutCurrent(Creature creature) {
        Coordinate currentCreatureCoordinate = entityService.getCoordinateCreature(creature);
        return searchRoute.getNextCoordinate(currentCreatureCoordinate, creature);
    }

    public Coordinate getNextCoordinateCreature() {
        return nextCoordinateCreature;
    }
}
