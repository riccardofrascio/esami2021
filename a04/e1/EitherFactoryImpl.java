package a04.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EitherFactoryImpl implements EitherFactory{

    @Override
    public <A, B> Either<A, B> success(B b) {
        return new Either<A,B>() {

            @Override
            public boolean isFailure() {
                return false;
            }

            @Override
            public boolean isSuccess() {
                return true;
            }

            @Override
            public Optional<A> getFailure() {
                return Optional.empty();
            }

            @Override
            public Optional<B> getSuccess() {
                return Optional.of(b);
            }

            @Override
            public B orElse(B other) {
                if(getSuccess().equals(Optional.empty())) {
                    return other;
                } else {
                    return b;
                }
            }

            @Override
            public <B1> Either<A, B1> map(Function<B, B1> function) {
                var value = function.apply(b);
                return success(value);
            }

            @Override
            public <B1> Either<A, B1> flatMap(Function<B, Either<A, B1>> function) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <A1> Either<A1, B> filterOrElse(Predicate<B> predicate, A1 failure) {
                if(predicate.test(b)) {
                    return success(b);
                } else {
                    return failure(failure);
                }
            }

            @Override
            public <C> C fold(Function<A, C> funFailure, Function<B, C> funSuccess) {
                C value;
                if(!getSuccess().equals(Optional.empty())) {
                    value = funSuccess.apply(getSuccess().get());
                } else {
                    value = funFailure.apply(getFailure().get());
                }
                return value;
            }
            
        };
    }

    @Override
    public <A, B> Either<A, B> failure(A a) {
        return new Either<A,B>() {

            @Override
            public boolean isFailure() {
                return true;
            }

            @Override
            public boolean isSuccess() {
                return false;
            }

            @Override
            public Optional<A> getFailure() {
                return Optional.of(a);
            }

            @Override
            public Optional<B> getSuccess() {
                return Optional.empty();
            }

            @Override
            public B orElse(B other) {
                if(isSuccess()) {
                    return getSuccess().get();
                } else {
                    return other;
                }
            }

            @Override
            public <B1> Either<A, B1> map(Function<B, B1> function) {
                if(isSuccess()) {
                    var value = function.apply(getSuccess().get());
                    return success(value);    
                } else {
                    return failure(a);
                }
            }

            @Override
            public <B1> Either<A, B1> flatMap(Function<B, Either<A, B1>> function) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <A1> Either<A1, B> filterOrElse(Predicate<B> predicate, A1 failure) {
                if(!isSuccess()) {
                    return failure(failure);
                }
                if(predicate.test(getSuccess().get())) {
                    return success(getSuccess().get());
                } else {
                    return failure(failure);
                }
            }

            @Override
            public <C> C fold(Function<A, C> funFailure, Function<B, C> funSuccess) {
                var value = funFailure.apply(a);
                return value;
            }
            
        };
    }

    @Override
    public <A> Either<Exception, A> of(Supplier<A> computation) {
        try {
            computation.get();
        } catch(Exception e) {
            return failure(e);
        }
        return success(computation.get());
    }

    @Override
    public <A, B, C> Either<A, List<C>> traverse(List<B> list, Function<B, Either<A, C>> function) {
        List<C> listC = new ArrayList<>();
        for (B b : list) {
            var value = function.apply(b);
            if(value.isFailure()) {
                return failure(value.getFailure().get());
            } else {
                listC.add(value.getSuccess().get());
            }
        }
        return success(listC);
    }

}
