package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
lambda
stream

distinct --> applied on stream --> returns stream
sorted --> applied on stream--> returns stream
map  -->  do something for each item and return it--> returns stream
filter --> filter based on a condition--> returns stream

reduce  --> iterate over each item and aggregate it with something
collect --> collect them into a list and return them as list etc

forEach --> do something on each item without returning anything. this is usually at the end of the 'chain'

operations that return 'stream' are called intermediate operations
operations on streams that don't return streams are called terminal stream operations
 */
public class AAFunctionalIntro {
	private static int addListFunctional1(final List<Integer> numbers) {
		return numbers.stream()
				.reduce(0, AAFunctionalIntro::sum);
	}

	private static int addListFunctional2(final List<Integer> numbers) {
		return numbers.stream()
				.reduce(0, (x,y) -> x + y);
	}

	private static int addListFunctional3(final List<Integer> numbers) {
		return numbers.stream()
				.reduce(0, Integer::sum); //Performs a reduction on the elements of this stream, using the provided identity value and an associative accumulation function, and returns the reduced value.
	}

	private static int addListFunctional4(final List<Integer> numbers) {
		return numbers.stream()
				.parallel()
				.reduce(0, Integer::sum);
	}


	private static List<String>  getFirstLettersOfCourses(final List<String> courses) {
		return courses.stream()
				.map(str -> str.substring(0,1))
				.collect(Collectors.toList())
				;
	}

	public static void main(final String[] args) {
		final List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices","AWS", "PCF","Azure", "Docker", "Kubernetes");
		final List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

		courses.stream().map(x -> x.toUpperCase()).forEach(System.out::println); //this can also be written as:
		courses.stream().map(String::toUpperCase).forEach(System.out::println); //String::toUpperCase is a call to an instance method whereas System.out::println is call to a static method

		printCubesOfOddNumbersInListFunctional(numbers);
		printOddNumbersInListFunctional(numbers);

		printSpringCourses(courses);
		printDistinctCourses(courses);
		printSortedCourses(courses);
		printSortedCoursesReverse(courses);
		printSortedCoursesLength(courses);
		System.out.println(getFirstLettersOfCourses(courses));

		System.out.println(addListFunctional1(numbers));
		System.out.println(addListFunctional2(numbers));
		System.out.println(addListFunctional3(numbers));

		final List<String> newList = List.of("A1", "a2" , "c23", "b33");
		final List<String> upperCaseList = new ArrayList<>(newList);
		upperCaseList.replaceAll(x -> x.toUpperCase()); //we are not assigning to anything.. it does the job anyway!
		System.out.println(upperCaseList); //we cannot perform replaceAll kind of operations directly because when List.of is used, it creates an immutable List

		final List<String> removeStartingA = new ArrayList<>(newList);
		removeStartingA.removeIf(x -> x.startsWith("A"));
		System.out.println(removeStartingA);

		final int[] numbers2 = {12, 9, 13, 4, 6, 2, 4, 12, 15};
		System.out.println(Stream.of(numbers));
		System.out.println(Arrays.stream(numbers2).min()); //Stream.of returns a ReferencePipeline and the members inside stream are converted to object types
		System.out.println(Arrays.stream(numbers2).average()); //Arrays.stream returns an IntPipeline or a primitive pipeline so integers stay as integers
		System.out.println(Arrays.stream(numbers2).sum());
	}

	private static void printCubesOfOddNumbersInListFunctional(final List<Integer> numbers) {
		numbers.stream() // Convert to Stream
		.filter(number -> number % 2 != 0) // Lamdba Expression
		.map (number -> number * number * number)
		.forEach(System.out::println);// Method Reference
	}


	private static void printDistinctCourses(final List<String> courses) {
		courses.stream()
		.distinct()
		.forEach(System.out::println);
	}

	private static void printOddNumbersInListFunctional(final List<Integer> numbers) {
		numbers.stream() // Convert to Stream
		.filter(number -> number % 2 != 0) // Lamdba Expression
		.forEach(System.out::println);// Method Reference
	}


	private static void printSortedCourses(final List<String> courses) {
		courses.stream()
		.sorted()
		.forEach(System.out::println);
	}

	private static void printSortedCoursesLength(final List<String> courses) {
		courses.stream()
		.sorted(Comparator.comparing(x -> x.length())) //the Comparator class has other useful methods for various types
		.forEach(System.out::println);
	}

	private static void printSortedCoursesReverse(final List<String> courses) {
		courses.stream()
		.sorted(Comparator.reverseOrder()) //the Comparator class has other useful methods for various types
		.forEach(System.out::println);
	}

	private static void printSpringCourses(final List<String> courses) {
		courses.stream()
		.filter(course -> course.contains("Spring"))
		.forEach(x -> System.out.println(x)); //same as forEach(System.out::println);
	}

	private static int sum(final int aggregate, final int nextNumber) {
		return aggregate + nextNumber;
	}

	/*
		First-class functions(function as a first-class citizen) means you can assign functions to variables, pass a function as an argument to another function or return a function from another. Unfortunately, Java doesn’t support this and hence makes concepts like closures, currying and higher-order-functions less convenient to write.
		The closest to first-class functions in Java is Lambda expressions. There are also some built-in functional interfaces like Function, Consumer, Predicate, Supplier and so on under the java.util.function package which can be used for functional programming.



	 */

}
