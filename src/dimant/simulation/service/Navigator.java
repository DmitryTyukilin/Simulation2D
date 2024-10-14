package dimant.simulation.service;


import dimant.simulation.Coordinate;
import dimant.simulation.entity.Creature;

public class Navigator {
    private SearchRoute searchRoute;
    private ScannerType scannerType;
    private EntityService entityService;

    public Navigator(SearchRoute searchRoute, ScannerType scannerType, EntityService entityService) {
        this.searchRoute = searchRoute;
        this.scannerType = scannerType;
        this.entityService = entityService;
    }

    /*
Идея сейчас такая: SearchRoute возвращает координату  - Radar возвращает тип для
Navigator- Navigator содержит методы get для каждого типа и ретёрнит тип для makeMove -
makeMove ретёрнит тип для CreatureService, который делает действие
( перемещает, оставляет на месте, перемещает в другую сторону) в зависимости от полученного типа.
 */


    public String getTypeNextStep(Creature creature) {
        Coordinate currentCreatureCoordinate = entityService.getCoordinateCreature(creature);
        Coordinate nextCoordinate = searchRoute.getNextCoordinate(currentCreatureCoordinate);
        return scannerType.getEntityType(nextCoordinate);
    }
}
