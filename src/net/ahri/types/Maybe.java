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
}
