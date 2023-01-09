package a01a.e2;

import java.util.Optional;
import java.util.Set;

public interface Logics {

    public Optional<Set<Pair<Integer, Integer>>> getCells();

    public void click(Pair<Integer, Integer> cellClicked);

}
