package collection;

import java.util.Arrays;
import java.util.Collection;

public class Main
{
	public static void main(String args[])
	{
		Collection<Number> col = new NumberSet<>(10);
		System.out.println(col.isEmpty());
		Number tab[] = { new Long(2), new Float(3.0f), new Integer(3), new Integer(4) };
		col.addAll(Arrays.asList(tab));
		System.out.println("For each ");
		col.forEach(System.out::println);
		System.out.println("Remove " + col.remove(new Long(2)));
		col.forEach(System.out::println);
		col.forEach((Number e) ->
		{
			System.out.println(e.doubleValue() + 1);
		});
	}
}
