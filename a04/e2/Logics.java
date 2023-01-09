package a04.e2;

import java.util.Map;
import java.util.Set;

public interface Logics {


    public int getValue();

    public void click(Pair<Integer, Integer> position);

    public Map<Pair<Integer, Integer>, String> getOperations();

    public Map<Pair<Integer, Integer>, Integer> getValues();

    public Set<Pair<Integer, Integer>> getNear(Pair<Integer, Integer> position);

}
