package a01b.e2;

import java.lang.StackWalker.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LogicsImpl implements Logics{

    private Set<Pair<Integer, Integer>> positions = new HashSet<>();
    private Optional<Pair<Integer, Integer>> first;
    private Optional<Pair<Integer, Integer>> second;
    private Optional<Pair<Integer, Integer>> third;
    private int size; 
    private int counter;

    public LogicsImpl(int size) {
        this.size = size;
        this.first=Optional.empty();
        this.second=Optional.empty();
        this.third=Optional.empty();
    }

    @Override
    public boolean click(Pair<Integer, Integer> pos) {
        if(first.equals(Optional.empty())) {
            first=Optional.of(pos);
            counter++;
            setPositions();
            return true;
        }
        if(positions.contains(pos)) {
            counter++;
            if(counter == 2) {
                second = Optional.of(pos);
                removePositions(pos);
            }
            if(counter == 3) {
                third=Optional.of(pos);
            }
            return true;
        }
        return false;
    }

    @Override
    public Set<Pair<Integer, Integer>> getPositions() {
        return positions;
    }

    private void setPositions() {
        int y = first.get().getY();
        int x = first.get().getX();
        for (int i = 0; i < size; i++) {
            this.positions.add(new Pair<>(i, y));
            this.positions.add(new Pair<>(x, i));
        }
        /*while(positions.contains(first.get())) {
            this.positions.remove(first.get());
        }*/
    }

    private void removePositions(Pair<Integer, Integer> pos) {
        if(pos.getX().equals(first.get().getX())) {
            for (int i = 0; i < size; i++) {
                this.positions.remove(new Pair<>(pos.getX(), i));
            }
        } else {
            for (int i = 0; i < size; i++) {
                this.positions.remove(new Pair<>(i, pos.getY()));
            }
        }
    }

    @Override
    public int getNumber() {
        return counter;
    }

    @Override
    public Set<Pair<Integer, Integer>> getTriangle() {
        if(third.equals(Optional.empty())) {
            return Set.of();
        }
        Set<Pair<Integer, Integer>> triangle = new HashSet<>();
        if(first.get().getX().equals(second.get().getX())) {
            if(first.get().getY() < second.get().getY()) {
                triangle.addAll(getCol(first.get().getY(),second.get().getY(), first.get().getX()));
            } else {
                triangle.addAll(getCol(second.get().getY(),first.get().getY(), first.get().getX()));
            }
            if(first.get().getX() < third.get().getX()) {
                triangle.addAll(getLine(first.get().getX(),third.get().getX(), first.get().getY()));
            } else {
                triangle.addAll(getLine(third.get().getX(),first.get().getX(), first.get().getY()));
            }
        } else {
            if(first.get().getY() < third.get().getY()) {
                triangle.addAll(getCol(first.get().getY(),third.get().getY(), first.get().getX()));
            } else {
                triangle.addAll(getCol(third.get().getY(),first.get().getY(), first.get().getX()));
            }
            if(first.get().getX() < second.get().getX()) {
                triangle.addAll(getLine(first.get().getX(),second.get().getX(), first.get().getY()));
            } else {
                triangle.addAll(getLine(second.get().getX(),first.get().getX(), first.get().getY()));
            }
        }

        return triangle;
    }
    
    private Set<Pair<Integer, Integer>> getCol(int min, int max, int x) {
        Set<Pair<Integer, Integer>> line = new HashSet<>();
        for (int i = min+1; i < max; i++) {
            line.add(new Pair<Integer,Integer>(x, i));
        }

        return line;
    }

    private Set<Pair<Integer, Integer>> getLine(int min, int max, int x) {
        Set<Pair<Integer, Integer>> line = new HashSet<>();
        for (int i = min+1; i < max; i++) {
            line.add(new Pair<Integer,Integer>(i, x));
        }

        return line;
    }
}
