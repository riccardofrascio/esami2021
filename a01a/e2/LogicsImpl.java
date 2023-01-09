package a01a.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LogicsImpl implements Logics{

    Optional<Pair<Integer, Integer>> firstCell = Optional.empty();
    Optional<Pair<Integer, Integer>> secondCell = Optional.empty();

    public boolean setFirstCell(Pair<Integer, Integer> cell) {
        if(firstCell.equals(Optional.empty())) {
            firstCell=Optional.of(cell);
            return true;
        }
        return false;

    }

    public boolean setSecondCell(Pair<Integer, Integer> cell) {
        if(secondCell.equals(Optional.empty())) {
            secondCell=Optional.of(cell);
            return true;
        } 
        return false;
    }

    @Override
    public Optional<Set<Pair<Integer, Integer>>> getCells() {
        
        if(firstCell.equals(Optional.empty()) || secondCell.equals(Optional.empty())){
            return Optional.empty();
        }
        Set<Pair<Integer, Integer>> cells = new HashSet<>();
        var first = firstCell.get();
        var second = secondCell.get();
        int minX;
        int minY;
        int maxX;
        int maxY;
        if(first.getX()> second.getX()) {
            minX = second.getX();
            maxX = first.getX();
        } else {
            maxX = second.getX();
            minX = first.getX();
        }
        if(first.getY() > second.getY()) {
            minY = second.getY();
            maxY = first.getY();
        } else {
            maxY = second.getY();
            minY = first.getY();
        }
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                cells.add(new Pair<Integer,Integer>(j, i));
            }
            
        }
        firstCell = Optional.empty();
        secondCell = Optional.empty();
        return Optional.of(cells);
    }

    @Override
    public void click(Pair<Integer, Integer> cellClicked) {
        if(!setFirstCell(cellClicked)) {
            setSecondCell(cellClicked);
        }
    }
    
}
