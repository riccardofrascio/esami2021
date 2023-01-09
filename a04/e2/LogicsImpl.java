package a04.e2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics{

    private Map<Pair<Integer, Integer>, String> operations = new HashMap<>();
    private Map<Pair<Integer, Integer>, Integer> values = new HashMap<>();
    private List<String> operators = List.of("-", "+", "*");
    private Optional<String> operator;
    private int value = 0;

    public LogicsImpl(int size) {
        operator=Optional.empty();
        Random rand = new Random();
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++) {
                var switcher = rand.nextInt(2); 
                if(switcher == 0) {
                    values.put(new Pair<Integer,Integer>(j,i),rand.nextInt(10));
                } else {
                    operations.put(new Pair<Integer,Integer>(j,i), operators.get(rand.nextInt(3)));
                }
            }
        }
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        Optional<Integer> temp = Optional.empty();
        if(values.keySet().contains(position)) {
            temp = Optional.of(values.get(position));
        }
        if(operations.keySet().contains(position)) {
            operator=Optional.of(operations.get(position));
        }
        if(value==0 && !temp.equals(Optional.empty())) {
            value=temp.get();
        } else {
            if(!operator.equals(Optional.empty()) && !temp.equals(Optional.empty())) {
                compute(temp.get(), operator.get());
            }
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> getNear(Pair<Integer, Integer> position) {
        Set<Pair<Integer, Integer>> neighbours = new HashSet<>();
        neighbours.add(near(position, 0, 1));
        neighbours.add(near(position, 1, 0));
        neighbours.add(near(position, 0, -1));
        neighbours.add(near(position, -1, 0));
        return neighbours;
    }

    private Pair<Integer, Integer> near(Pair<Integer, Integer> position, int x, int y) {
        return new Pair<Integer,Integer>(position.getX()+x, position.getY()+y);
    }

    @Override
    public Map<Pair<Integer, Integer>, String> getOperations() {
        return operations;
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getValues() {
        return values;
    }

    private void compute(int value2, String op) {
        switch(op) {
            case "*":
                this.value=value*value2;
                break;
            case "-":
                this.value=value-value2;
                break;
            case "+":
                this.value=value+value2;
                break;
            default:
            break;
        }
    }
    
}
