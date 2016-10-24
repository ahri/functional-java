package net.ahri.types;

public interface Monoid<a>
{
    a identity();
    a mappend(a other);
}
