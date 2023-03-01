package functional;

import java.util.function.Function;

public class CurryingExample {
    public static void main(String[] args) {
        Function<Integer,Function<Integer, Function<Integer,Integer>>> addThreeNos = u -> v -> w -> u+v+w;
        System.out.println("Adding 6,7,8 :"+addThreeNos.apply(6).apply(7).apply(8));
    }

}