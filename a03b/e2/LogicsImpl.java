package a03b.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics{

    private enum Directions {
        UP(0, -1), RIGTH(1,0), LEFT(-1,0), DOWN(0,1);

        int x;
        int y;
        Directions(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    private int size;
    private Set<Pair<Integer, Integer>> obstacles = new HashSet<>();
    private Pair<Integer, Integer> position;
    private Directions direction;
    private Directions prevDirection;

    public LogicsImpl(int size) {
        this.size = size;
        Random rand = new Random();
        position = new Pair<>(rand.nextInt(2, size-2), rand.nextInt(2, size-2));
        direction=Directions.UP;
    }

    @Override
    public void click() {
        obstacles.add(position);
        position=new Pair<Integer,Integer>(position.getX()+direction.x, position.getY()+direction.y);
        nextMove();
        if(obstacles.contains(new Pair<>(position.getX()+direction.x, position.getY()+direction.y))) {
            direction=prevDirection;
        }
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    @Override
    public Set<Pair<Integer, Integer>> getObstacles() {
        return obstacles;
    }

    private void nextMove() {
        prevDirection=direction;
        switch(direction) {
            case DOWN:
                direction=Directions.LEFT;
                break;
            case LEFT:
                direction=Directions.UP;
                break;
            case RIGTH:
                direction=Directions.DOWN;
                break;
            case UP:
                direction=Directions.RIGTH;
                break;
         }
        
    }

    @Override
    public boolean isOver() {
        return position.getX() < 0
                || position.getX() == size
                || position.getY() == size
                || position.getY()<0;
    }
    
}
