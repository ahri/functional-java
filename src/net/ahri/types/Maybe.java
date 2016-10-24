package net.ahri.types;

import java.util.function.Function;

public class Maybe<T> implements Monoid, Monad<T>
{
    @Override
    public Object identity()
    {
        throw new UnsupportedOperationException("Unimplemented: net.ahri.types.Maybe.identity"); // TODO
    }

    @Override
    public Object mappend(Object other)
    {
        throw new UnsupportedOperationException("Unimplemented: net.ahri.types.Maybe.mappend"); // TODO
    }

    @Override
    public <b extends Functor<T>> b pure(T... vals)
    {
        throw new UnsupportedOperationException("Unimplemented: net.ahri.types.Maybe.pure"); // TODO
    }

    @Override
    public <b> Functor<b> sequentialApply(Functor<Function<T, b>> f)
    {
        throw new UnsupportedOperationException("Unimplemented: net.ahri.types.Maybe.sequentialApply"); // TODO
    }

    @Override
    public <b> Monad<b> sequentialCompose(Function<T, Monad<b>> f)
    {
        throw new UnsupportedOperationException("Unimplemented: net.ahri.types.Maybe.sequentialCompose"); // TODO
    }
}
