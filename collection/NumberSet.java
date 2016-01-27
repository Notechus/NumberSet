package collection;

import java.util.Collection;
import java.util.Iterator;

public class NumberSet<T extends Number> implements Collection<T>
{
	protected Number[] numbers;
	protected int size;
	protected int mod = 0;
	protected int capacity;

	private NumberSet()
	{
		this.size = 0;
		this.capacity = 0;
	}

	public NumberSet(int capacity)
	{
		this.size = 0;
		if (capacity < 0) throw new IllegalArgumentException("Capacity cannot be negative!");
		this.capacity = capacity;
		numbers = new Number[capacity];
	}

	@Override
	public boolean add(T o)
	{
		if (size == capacity || contains(o)) return false;
		numbers[size] = (Number) o;
		size++;
		mod++;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		if (size + c.size() > capacity || this.containsAll(c)) return false;
		for (Object i : c)
		{
			if (contains(i)) continue;
			add((T) i);
		}
		return true;
	}

	public int capacity()
	{
		return capacity;
	}

	@Override
	public void clear()
	{
		size = 0;
		numbers = new Number[capacity];
	}

	@Override
	public boolean contains(Object o)
	{
		if (o instanceof Number)
		{
			for (int i = 0; i < size; i++)
			{
				if (o.equals(numbers[i])) return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		for (Object i : c)
		{
			if (!contains(i)) return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty()
	{
		return (size == 0);
	}

	@Override
	public Iterator<T> iterator()
	{
		return new SetIterator<T>(this);
	}

	@Override
	public boolean remove(Object o)
	{
		if (!isEmpty() && o instanceof Number)
		{
			for (int i = 0; i < size; i++)
			{
				if (o.equals(numbers[i]))
				{
					numbers[i] = numbers[size - 1];
					numbers[size - 1] = null;
					size--;
					mod--;
					return true;
				}
			}
			return false;
		}
		return false;

	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		boolean changed = false;
		for (Object i : c)
		{
			boolean tmp = remove(i);
			if (!changed && tmp) changed = true;
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		boolean changed = false;
		/*
		 * for (int j = 0; j < size; j++) { if (c.contains(numbers[j])) continue; else { remove(numbers[j]); if (!changed) changed = true; } }
		 */
		for (Object i : this)
		{
			if (c.contains(i)) continue;
			else
			{
				remove(i);
				if (!changed) changed = true;
			}
		}
		return changed;
	}

	@Override
	public int size()
	{
		return size + 1;
	}

	@Override
	public Object[] toArray()
	{
		Number[] tmp = new Number[size];
		System.arraycopy(numbers, 0, tmp, 0, size);
		return tmp;
	}

	@Override
	public <T> T[] toArray(T[] o)
	{
		if (size() <= o.length) return (T[]) toArray();
		System.arraycopy(numbers, 0, o, 0, size);
		if (o.length > size())
		{
			for (int i = size(); i < o.length; i++)
			{
				o[i] = null;
			}
		}
		return o;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Collection)
		{
			Collection col = (Collection) o;
			for (Object i : col)
			{
				if (!contains(i))
				{
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		for (Number i : numbers)
		{
			hash += i.hashCode();
		}
		return hash;
	}
}
