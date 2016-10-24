package net.ahri.types;

import java.util.function.Function;

public interface Monad<a> extends Applicative<a>
{
    <b> Monad<b> sequentialCompose(Function<a, Monad<b>> f);
}
