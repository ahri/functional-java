package net.ahri.types;

public class Sum implements Monoid<Integer>
{
    private final Integer i;

    public Sum(Integer i)
    {
        this.i = i;
    }

    public static Integer identity_()
    {
        return 0;
    }

    @Override
    public Integer identity()
    {
        return identity_();
    }

    @Override
    public Integer mappend(Integer other)
    {
        return i + other;
    }

    @Override
    public String toString()
    {
        return "Sum{" +
                "i=" + i +
                '}';
    }
}
