package a01b.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class EventSequenceProducerHelpersImpl implements EventSequenceProducerHelpers{

    @Override
    public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
        return new EventSequenceProducer<E>() {

            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                if(iterator.hasNext()) {
                    return iterator.next();
                } else {
                    throw new NoSuchElementException();
                }
            }
            
        };
    }

    @Override
    public <E> List<E> window(EventSequenceProducer<E> sequence, double fromTime, double toTime) {
        int start = (int)fromTime;
        int end = (int)toTime;
        List<E> list = new ArrayList<>();
        if(toTime-fromTime < 1) {
            return List.of();
        }
        for (int i = 0; i < start-1; i++) {
            sequence.getNext();
        }
        for (int i = start; i < end; i++) {
            list.add(sequence.getNext().get2());
        }

        return list;
    }

    @Override
    public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {
        return new Iterable<E>() {

            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {

                    E value;

                    @Override
                    public boolean hasNext() {
                        try {
                            
                            value = sequence.getNext().get2();
                        } catch(NoSuchElementException e) {
                            return false;    
                        }
                        return true;
                    }

                    @Override
                    public E next() {
                        return value;
                    }
                    
                };
            }
            
        };
    }

    @Override
    public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {
        int var = (int) time;
        Pair<Double, E> value = new Pair<Double,E>(null, null);
        try{
            for (int i = 0; i < var; i++) {
                value = sequence.getNext();
            }
            return Optional.of(value);
        } catch (NoSuchElementException e){
            return Optional.empty();
        }
    }

    @Override
    public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
        // TODO Auto-generated method stub
        return null;
    }

}
