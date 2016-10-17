package net.ahri.types;

public class Product implements Monoid<Integer>
{
    private final Integer i;

    public Product(Integer i)
    {
        this.i = i;
    }

    public static Integer identity_()
    {
        return 1;
    }

    @Override
    public Integer identity()
    {
        return identity_();
    }

    @Override
    public Integer mappend(Integer other)
    {
        return i * other;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "i=" + i +
                '}';
    }
}
