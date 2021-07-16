package lab07;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
	private int threshold;
	LinkedList<IWithName> arr[]; // use pure array
    private final static int defaultInitSize = 8;
    private final static double defaultMaxLoadFactor = 0.7;
    private int size;
    private final double maxLoadFactor;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    public HashTable()
    {
        this(defaultInitSize);
    }

    public HashTable(int initCapacity)
    {
        this(initCapacity, defaultMaxLoadFactor);
    }


    public HashTable(int initCapacity, double maxLF)
    {
        if (initCapacity < 0)
        {
            throw new IllegalArgumentException("Illegal Capacity: " + initCapacity);
        }
        if (maxLF <= 0 || Double.isNaN(maxLF))
        {
            throw new IllegalArgumentException("Illegal Load: " + maxLF);
        }

        if (initCapacity == 0)
        {
            initCapacity = 1;
        }
        this.maxLoadFactor = maxLF;
        arr = new LinkedList[initCapacity];
        threshold = (int) Math.min(initCapacity * maxLoadFactor, MAX_ARRAY_SIZE + 1);
    }

    public boolean add(Object elem)
    {
        if (elem == null || !(elem instanceof IWithName))
        {
            return false;
        }
        IWithName value = (IWithName) elem;

        if (size >= threshold)
        {
            doubleArray();
        }

        int index = (value.hashCode() & 0x7FFFFFFF) % arr.length;
        if (arr[index] == null)
        {
            arr[index] = new LinkedList<>();
        }
        if (arr[index].contains(value))
        {
            return false;
        }
        arr[index].add(value);
        size++;
        return true;
    }

    private void doubleArray()
    {
        int oldCapacity = arr.length;
        LinkedList<IWithName>[] oldArr = arr;
        int newCapacity = oldCapacity * 2;
        LinkedList<IWithName>[] newArr = new LinkedList[newCapacity];

        threshold = (int) Math.min(newCapacity * maxLoadFactor, MAX_ARRAY_SIZE + 1);
        arr = newArr;

        for (int i = 0; i < oldCapacity; i++)
        {
            List<IWithName> old = oldArr[i];
            if (old == null) {
                continue;
            }
            for (IWithName e : old)
            {
                int index = (e.hashCode() & 0x7FFFFFFF) % newCapacity;
                if (newArr[index] == null)
                {
                    newArr[index] = new LinkedList<>();
                }
                newArr[index].add(e);
            }
        }
    }


    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++)
        {
            result.append(i).append(':');
            LinkedList<IWithName> list = arr[i];
            if (list != null)
            {
                Iterator<IWithName> it = list.iterator();
                while (it.hasNext())
                {
                    IWithName elem = it.next();
                    result.append(' ').append(elem.getName());
                    if (it.hasNext())
                    {
                        result.append(',');
                    }

                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    public Object get(Object toFind)
    {
        if (toFind == null) {
            return null;
        }
        int hash = toFind.hashCode();
        int index = (hash & 0x7FFFFFFF) % arr.length;
        LinkedList<IWithName> list = arr[index];
        if (list == null)
        {
            return null;
        }
        for (IWithName e : list)
        {
            if (toFind.equals(e))
            {
                return e;
            }
        }
        return null;
    }

}

