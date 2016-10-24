package net.ahri;

import net.ahri.types.Functor;
import net.ahri.types.HaskellConcatList;
import net.ahri.types.Product;
import net.ahri.types.Sum;

import java.util.function.Function;

public class Main
{
    public static void main(String[] args)
    {
        // Monoid
        System.out.println("=== Monoid ===");
        Sum s = new Sum(4);
        System.out.println(String.format("<>       -  %s.mappend(Sum.identity()) = %s", s, s.mappend(Sum.identity_())));
        System.out.println(String.format("<>       -  %s.mappend(4) = %s", s, s.mappend(4)));

        Product p = new Product(3);
        System.out.println(String.format("<>       -  %s.mappend(Product.identity()) = %s", p, p.mappend(Product.identity_())));
        System.out.println(String.format("<>       -  %s.mappend(4) = %s", p, p.mappend(4)));

        final HaskellConcatList<Integer> l1 = new HaskellConcatList<>(1, 2);
        final HaskellConcatList<Integer> l2 = HaskellConcatList.identity_();
        System.out.println(String.format("identity -  HaskellConcatList.identity() = %s", l2));

        // Functor
        System.out.println("\n=== Functor ===");
        final HaskellConcatList<Integer> l3 = new HaskellConcatList<>(3, 4);
        final HaskellConcatList<Integer> lT = l1.mappend(l2).mappend(l3);
        System.out.println(String.format("<>       -  %s.mappend(%s).append(%s) = %s", l1, l2, l3, lT));

        Functor<Double> lTMapped = lT.fmap(i -> i / 2.0);
        System.out.println(String.format("<$>      -  %s.fmap(i -> i / 2.0)) = %s", lT, lTMapped));


        // Applicative
        System.out.println("\n=== Applicative ===");
        final HaskellConcatList<Character> la = HaskellConcatList.pure_('a', 'b', 'c');
        System.out.println(String.format("pure     -  HaskellConcatList.pure('a', 'b', 'c') = %s", la));

        HaskellConcatList<Function<Character, String>> fs = new HaskellConcatList<>(x -> x + "1", x -> x + "2");
        final Functor<String> postApplication = la.sequentialApply(fs);
        System.out.println(String.format("<*>      -  %s.sequentialApply(HaskellConcatList{list=[x -> x + \"1\", x -> x + \"2\"]}) = %s", la, postApplication));

        System.out.println();

        // Monad
        System.out.println("\n=== Monad ===");
        final HaskellConcatList<Character> lm = HaskellConcatList.return_('a', 'b', 'c');
        // Sequentially compose two actions, passing any value produced by the first as an argument to the second.
        System.out.println(String.format(">>=      -  HaskellConcatList.sequentialCompose(x -> new HaskellConcatList(x, x)) = %s", lm.sequentialCompose(x -> new HaskellConcatList<>(x, x))));
        // Sequentially compose two actions, discarding any value produced by the first, like sequencing operators (such as the semicolon) in imperative languages.
        //System.out.println(String.format(">>       -  HaskellConcatList.imperativeSequentialCompose('a', 'b', 'c') = %s", la));
        // Inject a value into the monadic type.
        //System.out.println(String.format("return   -  HaskellConcatList.return_('a', 'b', 'c') = %s", la));
    }
}
