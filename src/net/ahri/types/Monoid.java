package net.ahri.types;

public interface Monoid<T>
{
    T identity();
    T mappend(T other);
}
