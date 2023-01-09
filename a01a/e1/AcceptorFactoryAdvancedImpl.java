package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AcceptorFactoryAdvancedImpl implements AcceptorFactory{

    @Override
    public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
        return new Acceptor<String,Integer>() {

            private int counter = 0;

            @Override
            public boolean accept(String e) {
                if(!e.equals("")) {
                    counter++;
                }
                return true;
            }

            @Override
            public Optional<Integer> end() {
                
                return Optional.of(counter);
            }
            
        };
    }

    @Override
    public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
        return new Acceptor<Integer,String>() {

            private int previousValue = 0;
            private List<Integer> values = new ArrayList<>();
            
            @Override
            public boolean accept(Integer e) {
                if(e>previousValue) {
                    previousValue=e;
                    values.add(e);
                    return true;
                }
                values.clear();
                return false;
            }

            @Override
            public Optional<String> end() {
                if(values.size()>0) {
                    String str = "";
                    for (Integer integer : values) {
                        if(integer.equals(values.get(values.size()-1))) {
                            str=str+Integer.toString(integer);
                        } else {
                            str=str+Integer.toString(integer)+":";
                        }
                    }
                    return Optional.of(str);
                }
                return Optional.empty();
            }
            
        };
    }

    @Override
    public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
        return new Acceptor<Integer,Integer>() {

            private int value;
            private int counter;

            @Override
            public boolean accept(Integer e) {
                counter++;
                if(counter<4) {
                    value=value+e;
                    return true;
                }
                return false;
            }

            @Override
            public Optional<Integer> end() {
                if(counter==3) {
                    counter=0;
                    return Optional.of(value);
                }
                return Optional.empty();
            }
            
        };
    }

    @Override
    public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
        return new Acceptor<E,Pair<O1,O2>>() {

            @Override
            public boolean accept(E e) {
                return a1.accept(e) && a2.accept(e);
            }

            @Override
            public Optional<Pair<O1, O2>> end() {
                var var1 = a1.end();
                var var2 = a2.end();
                if(var1.equals(Optional.empty()) || var2.equals(Optional.empty())) {
                    return Optional.empty();
                }
                return Optional.of(new Pair<>(var1.get(), var2.get()));
            }
            
        };
    }

    @Override
    public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
            Function<S, Optional<O>> outputFun) {
        // TODO Auto-generated method stub
        return null;
    }

}
