package functional;

import java.util.function.Function;

public class ClosureSample {
    // this is a higher-order-function that returns an instance of Function interface
    Function<Integer, Integer> add(final int x) {
        // this is a closure, i.e, a variable holding an anonymous inner class instance of the Function interface
        // which uses variables from the outer scope
        var partial = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer y) {
                // variable x is obtained from the outer scope of this method which is declared as final
                return x + y;
            }
        };
        // The closure function instance is returned here
        return partial;
    }


    Function<Integer, Integer> add2(final int x) {
        // The lambda expression is returned here as closure
        // variable x is obtained from the outer scope of this method which is declared as final
        return y -> x + y;
    }

    public static void main(String[] args) {
        ClosureSample sample = new ClosureSample();

        // we are currying the add method to create more variations
        var add10 = sample.add(10);
        var add20 = sample.add(20);
        var add30 = sample.add(30);

        System.out.println(add10.apply(5)); // 15
        System.out.println(add20.apply(5)); // 25
        System.out.println(add30.apply(5)); // 35



        // we are currying the add method to create more variations
        var add10_2 = sample.add2(10);
        var add20_2 = sample.add2(20);
        var add30_2 = sample.add2(30);

        System.out.println(add10_2.apply(5));
        System.out.println(add20_2.apply(5));
        System.out.println(add30_2.apply(5));
    }
}