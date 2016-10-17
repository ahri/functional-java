package net.ahri.types;

import java.util.function.Function;

public interface Functor<T>
{
    <R> Functor<R> fmap(Function<T, R> f);

    T unwrap();
}
