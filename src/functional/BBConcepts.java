package functional;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class BBConcepts {


	public static void main(final String[] args) {
		//FunctionalInterface is an interface that has only 1 function.
		//Each of the functional interfaces such as Predicate/Function/Consumer/BinaryOperator etc implement FunctionalInterface

		//Predicate<Integer> --> Predicate takes 1 input and returns boolean. Ex: where we apply to filter
		//Function<Integer, String> --> Function takes 1 input of any type and returns 1 output of any type. ex: where we apply to map
		//Consumer<Integer> --> Consumer accepts one input but doesnt provide any output. Unlike most other functional interfaces, Consumer is expected to operate via side-effects. ex: where we apply to forEach

		//BiPredicate<Integer, String> --> BiPredicate takes 2 inputs of different/same type and returns boolean
		//BiFunction<Integer, String, String> --> takes 2 inputs and returns one input
		//BiConsumer<Integer, String> --> takes 2 inputs but doesnt return anything
		//BinaryOperator<Integer> --> BinaryOperator takes 2 inputs and does something on them and returns an output. both of  the two inputs and the output should be of same type

		//UnaryOperator<Integer> --> UnaryOperator takes 1 inputs and does something on them and returns an output. both the input and the output should be of same type
		//Supplier<Integer> --> Supplier doesnt take any parameters but will return something

		//All of the above take Object types (Integer, Long, String etc) but if we want to work with primitives, we also have below ones. This will avoid unnecessary boxing/unboxing needed for performance critical applications
		//IntBinaryOperator
		//IntConsumer
		//IntFunction
		//IntPredicate
		//IntSupplier
		//IntToDoubleFunction
		//IntToLongFunction
		//IntUnaryOperator

		//Long, Double, Int


		BehaviorParameterization.main(null);
		FunctionalInterfaces.main(null);

		final List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

		final Predicate<Integer> isEvenPredicate = x -> x%2==0;

		final Predicate<Integer> isEvenPredicate2 = x -> x%2==0;
		final Predicate<Integer> isEvenPredicate3 = (final Integer x) -> x%2==0; //we can also do this but is redundant because of type inference by Java

		final Function<Integer, Integer> squareFunction = x -> x * x;

		final Function<Integer, Integer> squareFunction2 = x -> x*x;

		final Consumer<Integer> sysoutConsumer = System.out::println;

		final Consumer<Integer> sysoutConsumer2 = x -> System.out.println(x);

		numbers.stream()
		.filter(isEvenPredicate2)
		.map(squareFunction2)
		.forEach(sysoutConsumer2);

		numbers.stream()
		.filter(isEvenPredicate)
		.map(squareFunction)
		.forEach(sysoutConsumer);

		final BinaryOperator<Integer> sumBinaryOperator = Integer::sum;
		//BinaryOperator<Integer> sumBinaryOperator = (x,y) => x + y;


		final BinaryOperator<Integer> sumBinaryOperator2 = (a, b) -> a + b;


		final int sum = numbers.stream()
				.reduce(0, sumBinaryOperator);
	}
}

class BehaviorParameterization {

	private static void filterAndPrint(final List<Integer> numbers, final Predicate<? super Integer> predicate) {
		numbers.stream()
		.filter(predicate)
		.forEach(System.out::println);
	}

	@SuppressWarnings("unused")
	public static void main(final String[] args) {

		final List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

		filterAndPrint(numbers, x -> x%2==0);

		filterAndPrint(numbers, x -> x%2!=0);

		filterAndPrint(numbers, x -> x % 3 == 0);

		final Function<Integer, Integer> mappingFunction = x -> x * x;

		final List<Integer> squaredNumbers = mapAndCreateNewList(numbers, mappingFunction);

		final List<Integer> cubedNumbers = mapAndCreateNewList(numbers, x -> x * x * x);

		final List<Integer> doubledNumbers = mapAndCreateNewList(numbers, x -> x + x);

		System.out.println(doubledNumbers);

	}

	private static List<Integer> mapAndCreateNewList(final List<Integer> numbers,
			final Function<Integer, Integer> mappingFunction) {
		return numbers.stream()
				.map(mappingFunction)
				.collect(Collectors.toList());
	}
}


class FunctionalInterfaces {

	@SuppressWarnings("unused")
	public static void main(final String[] args) {

		final List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

		final Predicate<Integer> isEvenPredicate = (final Integer x) -> x % 2 == 0;

		final Function<Integer, Integer> squareFunction = x -> x * x;

		final Function<Integer, String> stringOutpuFunction = x -> x + " ";

		final Consumer<Integer> sysoutConsumer = x -> System.out.println(x);

		final BinaryOperator<Integer> sumBinaryOperator = (x, y) -> x + y;

		//No input > Return Something
		final Supplier<Integer> randomIntegerSupplier = () -> {
			final Random random = new Random();
			return random.nextInt(1000);
		};

		final Supplier<String> newString = String::new; //this will simply return a new string when we do newString.get(). this is called constructor reference

		System.out.println(randomIntegerSupplier.get());

		final UnaryOperator<Integer> unaryOperator = x -> 3 * x;
		System.out.println(unaryOperator.apply(10));

		final BiPredicate<Integer, String> biPredicate = (number,str) -> {
			return number<10 && str.length()>5;
		};

		System.out.println(biPredicate.test(10, "in28minutes"));

		final BiFunction<Integer, String, String> biFunction = (number,str) -> {
			return number + " " + str;
		};

		System.out.println(biFunction.apply(15, "in28minutes"));

		final BiConsumer<Integer, String> biConsumer = (s1,s2) -> {
			System.out.println(s1);
			System.out.println(s2);
		};

		biConsumer.accept(25, "in28Minutes");

		final BinaryOperator<Integer> sumBinaryOperator2 = (x, y) -> x + y;

		final IntBinaryOperator intBinaryOperator = (x,y) -> x + y;



		numbers.stream().filter(isEvenPredicate).map(squareFunction).forEach(sysoutConsumer);

		final int sum = numbers.stream().reduce(0, sumBinaryOperator);
	}
}