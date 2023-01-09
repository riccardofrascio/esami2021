package a03b.e2;

import java.util.Set;

public interface Logics {

    public void click();

    public Pair<Integer, Integer> getPosition();

    public Set<Pair<Integer, Integer>> getObstacles();

    public boolean isOver();
}
