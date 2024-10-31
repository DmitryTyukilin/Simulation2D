package dimant.simulation.service;


import dimant.simulation.Coordinate;
import dimant.simulation.entity.Creature;
import dimant.simulation.entity.Hare;

public class Navigator {
    private SearchRoute searchRoute;
    private ScannerType scannerType;
    private EntityService entityService;
    private SearchHerbivoreService searchHerbivoreService;
    private Coordinate nextCoordinateCreature;

    public Navigator(SearchRoute searchRoute, ScannerType scannerType, EntityService entityService, SearchHerbivoreService searchHerbivoreService) {
        this.searchRoute = searchRoute;
        this.scannerType = scannerType;
        this.entityService = entityService;
        this.searchHerbivoreService = searchHerbivoreService;

    }

    /*
Идея сейчас такая: SearchRoute возвращает координату  - Radar возвращает тип для
Navigator- Navigator содержит методы get для каждого типа и ретёрнит тип для makeMove -
makeMove ретёрнит тип для CreatureService, который делает действие
( перемещает, оставляет на месте, перемещает в другую сторону) в зависимости от полученного типа.
 */


    public String getTypeNextStep(Creature creature) {
        Coordinate result;
        if (creature instanceof Hare) {
            Coordinate coordinateHare = entityService.getCoordinateCreature(creature);
             result = searchHerbivoreService.getNextCoordinateCreature(coordinateHare, creature);
            nextCoordinateCreature = result;
        } else {
            result = nextCoordinateAboutCurrent(creature);
            nextCoordinateCreature = result;
        }
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
