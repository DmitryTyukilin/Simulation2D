package main.java.org.simulation.entity;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;


public class Hare extends Herbivore {

    public Hare(int HP) {
        super(HP);
    }


    private void reduceHP() {
        HP = HP - 1;
    }

    private void eatGrass() {
        HP = HP + 2;
    }

    @Override
    public void takeDamage(int damage) {
        super.HP = HP - damage;
        System.out.println(" hare hp "  + super.HP);
    }

    @Override
    protected void eat(Coordinate coordinateEat, WordMap wordMap) {
        Entity entity = wordMap.getEntityByCoordinate(coordinateEat);
        if (entity instanceof Grass) {
            Grass grass = wordMap.getGrass(coordinateEat);
            wordMap.deleteEntityMap(grass);
            eatGrass();

        }
    }

    @Override
    public void makeMove(WordMap wordMap) {
        super.makeMove(wordMap);
        reduceHP();
        System.out.println("сделал ход потерял хп" + super.HP);
    }
}
