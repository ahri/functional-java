package net.ahri.types;

import java.util.Arrays;
import java.util.function.Function;

public class HaskellConcatList<a> implements Monoid<HaskellConcatList<a>>, Functor<a>, Applicative<a>, Monad<a>
{
    private final a[] list;

    public HaskellConcatList(a... list)
    {
        this.list = list;
    }

    public static <a> HaskellConcatList<a> identity_()
    {
        return new HaskellConcatList<a>();
    }

    @Override
    public HaskellConcatList<a> identity() // TODO: is this needed?
    {
        return identity_();
    }

    @Override
    public HaskellConcatList mappend(HaskellConcatList<a> other)
    {
        a[] concat = Arrays.copyOf(list, list.length + other.list.length);
        for (int i = 0; i < other.list.length; i++)
        {
            concat[i + list.length] = other.list[i];
        }

        return new HaskellConcatList(concat);
    }

    @Override
    public <b> Functor<b> fmap(Function<a, b> f)
    {
        b[] work = (b[]) new Object[list.length];
        for (int i = 0; i < list.length; i++)
        {
            work[i] = f.apply(list[i]);
        }

        return new HaskellConcatList<b>(work);
    }

    @Override
    public String toString()
    {
        return "HaskellConcatList{" +
                "list=" + Arrays.toString(list) +
                '}';
    }

    public static <a, b extends Functor<a>> b pure_(a... vals)
    {
        return (b) new HaskellConcatList<a>(vals);
    }

    @Override
    public <b extends Functor<a>> b pure(a... vals)
    {
        return pure_(vals);
    }

    @Override
    public <b> Functor<b> sequentialApply(Functor<Function<a, b>> fs) // TODO: can move this impl up to HaskellList and inherit
    {
        if (!(fs instanceof HaskellConcatList))
        {
            throw new RuntimeException("Same type only please"); // TODO: it would be nice to do this in generics
        }

        final HaskellConcatList application = (HaskellConcatList) fs.fmap(f -> this.fmap(v -> f.apply(v)));

        HaskellConcatList<b> tmp = null;
        for (int i = 0; i < application.list.length; i++)
        {
            if (i == 0)
            {
                tmp = (HaskellConcatList<b>) application.list[i];
            }
            else
            {
                tmp = tmp.mappend((HaskellConcatList<b>) application.list[i]);
            }
        }

        return tmp;
    }

    public static <a, b extends Monad<a>> b return_(a... vals)
    {
        return null;
    }

    @Override
    public <b> Monad<b> sequentialCompose(Function<a, Monad<b>> f)
    {
        Monad<a> tmp = return_();

        for (a item : list)
        {
            f.apply(item);
        }

        return null;
    }
}
