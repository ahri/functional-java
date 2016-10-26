package net.ahri;

import java.util.function.Function;

public class Functional
{
    public static void main(String... args)
    {
        // ord :: Character -> Integer
        // ord chr = chr :: Integer
        Function<Character, Integer> ord = x -> (int)x;

        // square :: Integer -> Integer
        // square n = n * n
        Function<Integer, Integer> square = x -> x * x;

        System.out.println("square . ord $ 'a' = " + compose(square, ord).apply('a'));

        // substr :: Integer -> Integer -> String -> String
        // substr from to str = str.substring(from, to)
        P3<Integer, Integer, String, String> substr = (from, to, str) -> str.substring(from, to);

        System.out.println("substr 1 2 \"foo\" = " + curry(substr).apply(1).apply(2).apply("foo"));

        // charAt :: Integer -> String -> Character
        // charAt idx str = str.charAt idx
        P2<Integer, String, Character> charAt = (i, s) -> s.charAt(i);

        // firstLetter :: String -> Character
        // firstLetter = charAt 0
        Function<String, Character> firstLetter = curry(charAt).apply(0);

        System.out.println("ord . firstLetter $ \"adam\" = " + compose(ord, firstLetter).apply("adam"));
    }

    public interface P2<T1, T2, R>
    {
        R apply(T1 p1, T2 p2);
    }

    public interface P3<T1, T2, T3, R>
    {
        R apply(T1 p1, T2 p2, T3 p3);
    }

    public static <T1, R1, R2> Function<T1, R2> compose(Function<R1, R2> second, Function<T1, R1> first)
    {
        return x -> second.apply(first.apply(x));
    }

    public static <T1, T2, R> Function<T1, Function<T2, R>> curry(P2<T1, T2, R> p2f)
    {
       return p1 -> p2 -> p2f.apply(p1, p2);
    }

    public static <T1, T2, T3, R> Function<T1, Function<T2, Function<T3, R>>> curry(P3<T1, T2, T3, R> p3f)
    {
        return p1 -> p2 -> p3 -> p3f.apply(p1, p2, p3);
    }
}
