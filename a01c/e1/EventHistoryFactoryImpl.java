package a01c.e1;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class EventHistoryFactoryImpl implements EventHistoryFactory{

    

    @Override
    public <E> EventHistory<E> fromMap(Map<Double, E> map) {
        return new EventHistory<E>() {

            Set<Double> keyset = map.keySet();

            @Override
            public double getTimeOfEvent() {
                return keyset.iterator().next();
            }

            @Override
            public E getEventContent() {
                return map.get(keyset);
            }

            @Override
            public boolean moveToNextEvent() {
                return keyset.iterator().hasNext();
            }
            
        };
    }

    @Override
    public <E> EventHistory<E> fromIterators(Iterator<Double> times, Iterator<E> content) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> EventHistory<E> fromListAndDelta(List<E> content, double initial, double delta) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> EventHistory<E> fromRandomTimesAndSupplier(Supplier<E> content, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EventHistory<String> fromFile(String file) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

}
