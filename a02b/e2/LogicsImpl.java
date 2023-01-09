package a02b.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics{

    private enum Direction{
        UP(0, -1), RIGTH(1,0), LEFT(-1, 0);

        int x;
        int y;
        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Pair<Integer, Integer> position;
    private Direction direction;
    private Set<Pair<Integer, Integer>> rigth = new HashSet<>();
    private Set<Pair<Integer, Integer>> left = new HashSet<>();
    private int size;


    public LogicsImpl(int size) {
        this.size = size;
        this.direction=Direction.UP;
        Random rand = new Random();
        this.position= new Pair<Integer,Integer>(rand.nextInt(size),size-1);
        while((rigth.size()+left.size()) < 20) {
            var pos = new Pair<>(rand.nextInt(size), rand.nextInt(size));
            System.out.println(pos);
            if(!position.equals(pos) && !rigth.contains(pos) && !left.contains(pos)) {
                int switcher=rand.nextInt(2);
                if(switcher==1) {
                    this.rigth.add(pos);
                } else {
                    this.left.add(pos);
                }
            }
        }

    }

    @Override
    public void click() {
        position=new Pair<>(position.getX()+direction.x, position.getY()+direction.y);
        nextDirection();
        if(isOver()) {
            System.exit(0);
        }
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
        
    }

    public void nextDirection() {
        var nextPos=new Pair<>(position.getX(), position.getY());
        if(rigth.contains(nextPos)) {
            direction=Direction.RIGTH;
            rigth.remove(nextPos);
        }
        if(left.contains(nextPos)) {
            direction=Direction.LEFT;
            left.remove(nextPos);
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> getLeft() {
        return this.left;
    }

    @Override
    public Set<Pair<Integer, Integer>> getRigth() {
        return this.rigth;
    }

    private boolean isOver() {
        if(position.getX()<0 || position.getX()==size || position.getY()<0 || position.getY()==size) {
            return true;
        }
        return false;
    }
    
}
