package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.MapBoard;
import dimant.simulation.Place;
import dimant.simulation.Wolf;

import java.util.HashMap;

public class EntityService {
    MapBoard mapBoard;
    SearchRoute searchRoute;

    public EntityService(MapBoard mapBoard, SearchRoute searchRoute) {
        this.mapBoard = mapBoard;
        this.searchRoute = searchRoute;
    }
// Определить текущую координату
    // Построить путь относительно новой координаты
    // Получить координату для хода из пути
    // на текущую записать Place
    // на новую записать волка
    // проверить сущест вокруг относительно новой записи при возможности атаковать
    // энергия есть для еще одного хода, да->
    // получить новую координату
    // иначе пропустить ход и восстоновить +1 энергии

    public void makeMoveWolf() {
        HashMap<Coordinate, Coordinate> currentPath;
        Coordinate currentCoordinateWolf = mapBoard.getCoordinateWolf();
        currentPath = searchRoute.getPath(currentCoordinateWolf);
        Coordinate newCoordinateWolf = currentPath.get(currentCoordinateWolf);
        Wolf wolf = mapBoard.getWolf(currentCoordinateWolf);
        vacatePlace(currentCoordinateWolf);
        mapBoard.addEntityMap(wolf,newCoordinateWolf);

    }

    public void vacatePlace(Coordinate currentCoordinate){
        mapBoard.addEntityMap(new Place(), currentCoordinate);
    }
}
