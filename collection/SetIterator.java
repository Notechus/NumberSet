package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SetIterator<T extends Number> implements Iterator<T>
{
	int next;
	int mod;
	NumberSet set;

	protected SetIterator(NumberSet s)
	{
		next = 0;
		this.mod = s.mod;
		this.set = s;
	}

	@Override
	public boolean hasNext()
	{
		/*
		 * if (set.mod == this.mod) { return next <= set.size; } else { throw new ConcurrentModificationException(); }
		 */
		return next < set.size;
	}

	@Override
	public T next()
	{
		return (T) set.numbers[next++];
	}
}
