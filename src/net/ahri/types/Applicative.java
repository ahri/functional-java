package net.ahri.types;

import java.util.function.Function;

public interface Applicative<T>
{
    <R extends Functor<T>> R pure(T... vals);

    <R> Functor<R> sequentialApply(Functor<Function<T, R>> fs);
}
