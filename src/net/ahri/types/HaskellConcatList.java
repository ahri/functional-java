package net.ahri.types;

import java.util.Arrays;
import java.util.function.Function;

public class HaskellConcatList<T> implements Monoid<HaskellConcatList<T>>, Functor<T>, Applicative<T>
{
    private final T[] list;

    public HaskellConcatList(T... list)
    {
        this.list = list;
    }

    public static <T> HaskellConcatList<T> identity_()
    {
        return new HaskellConcatList<T>();
    }

    @Override
    public HaskellConcatList<T> identity() // TODO: is this needed?
    {
        return identity_();
    }

    @Override
    public HaskellConcatList mappend(HaskellConcatList<T> other)
    {
        T[] concat = Arrays.copyOf(list, list.length + other.list.length);
        for (int i = 0; i < other.list.length; i++)
        {
            concat[i + list.length] = other.list[i];
        }

        return new HaskellConcatList(concat);
    }

    @Override
    public <R> Functor<R> fmap(Function<T, R> f)
    {
        R[] work = (R[]) new Object[list.length];
        for (int i = 0; i < list.length; i++)
        {
            work[i] = f.apply(list[i]);
        }

        return new HaskellConcatList<R>(work);
    }

    @Override
    public T unwrap()
    {
        return list[0];
    }

    @Override
    public String toString()
    {
        return "HaskellConcatList{" +
                "list=" + Arrays.toString(list) +
                '}';
    }

    public static <T, R extends Functor<T>> R pure_(T... vals)
    {
        return (R) new HaskellConcatList<T>(vals);
    }

    @Override
    public <R extends Functor<T>> R pure(T... vals)
    {
        return pure_(vals);
    }

    @Override
    public <R> Functor<R> sequentialApply(Functor<Function<T, R>> fs) // TODO: can move this impl up to HaskellList and inherit
    {
        if (!(fs instanceof HaskellConcatList))
        {
            throw new RuntimeException("Same type only please"); // TODO: it would be nice to do this in generics
        }

        final HaskellConcatList application = (HaskellConcatList) fs.fmap(f -> this.fmap(v -> f.apply(v)));

        HaskellConcatList<R> tmp = null;
        for (int i = 0; i < application.list.length; i++)
        {
            if (i == 0)
            {
                tmp = (HaskellConcatList<R>) application.list[i];
            }
            else
            {
                tmp = tmp.mappend((HaskellConcatList<R>) application.list[i]);
            }
        }

        return tmp;
    }
}
