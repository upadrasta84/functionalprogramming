package functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class DDRanges {

	public static void main(final String[] args) {
		final List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices","AWS", "PCF","Azure", "Docker", "Kubernetes");
		System.out.println();//we also have DoubleStream, LongStream etc
		System.out.println(IntStream.range(1,10).sum()); //1, 2,3,4,5,6,7,8,9 //startInclusive, endExclusive
		System.out.println(IntStream.rangeClosed(1,10).sum()); //1, 2,3,4,5,6,7,8,9,10 //startInclusive, endInclusive
		System.out.println(IntStream.iterate(0, e->e+2).limit(10)); //if we dont limit, it will go to infinity
		IntStream.iterate(0, e->e+2).limit(10).peek(System.out::println).sum();
		System.out.println(IntStream.iterate(0, e->e+2).limit(10).boxed().collect(Collectors.toList())); //we need to call boxed() before applying collect method to these kind of operations

		System.out.println(courses.stream().collect(Collectors.joining(" ")));
		System.out.println(courses.stream().map(x -> x.split("")).flatMap(Arrays::stream).collect(Collectors.joining(" ")));
		System.out.println(courses.stream().map(x -> x.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.joining(" ")));

		final long time = System.currentTimeMillis();
		System.out.println(LongStream.range(0,1000000000).parallel().sum()); //excutes faster by making use of all the cores of the cpu
		System.out.println(System.currentTimeMillis() - time);

		System.out.println(LongStream.range(0,1000000000).sum());

		System.out.println(System.currentTimeMillis() - time);

		//Functional programming is lazy.. as can be seen from output of the below, the functions checked only till what is needed instead of complete list.
		courses.stream().peek(System.out::println).filter(x -> x.length() > 11).map(x -> x.toUpperCase()).peek(System.out::println).findFirst();

		//Java is performant when it comes to Functional programming as it will not execute intermediate functions till it knows what to execute at the last call.

		//as per below example, the intermediate operations, for example peek and others, did not execute..
		courses.stream().peek(System.out::println).filter(x -> x.length() > 11).map(x -> x.toUpperCase()).peek(System.out::println);
	}
}
