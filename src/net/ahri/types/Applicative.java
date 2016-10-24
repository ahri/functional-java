package net.ahri.types;

import java.util.function.Function;

public interface Applicative<a>
{
    <b extends Functor<a>> b pure(a... vals);

    <b> Functor<b> sequentialApply(Functor<Function<a, b>> f);
}
