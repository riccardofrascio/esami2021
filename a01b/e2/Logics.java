package a01b.e2;

import java.util.Optional;
import java.util.Set;

public interface Logics {

    /**
     * @param click
     * @return true if your click was in one rigth cell
     */
    public boolean click(Pair<Integer, Integer> click);    


    /**
     * @return set of positions where you can click
     */
    public Set<Pair<Integer, Integer>> getPositions();

    public int getNumber();

    public Set<Pair<Integer, Integer>> getTriangle();
}
