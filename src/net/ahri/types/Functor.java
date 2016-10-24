package net.ahri.types;

import java.util.function.Function;

public interface Functor<a>
{
    <b> Functor<b> fmap(Function<a, b> f);
}
