package com.iangregson.algorithms.datastructures.dynamicarray;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

  private T[] arr;
  private int len = 0; // the user facing length
  private int capacity = 0; // the actual array size

  public DynamicArray() {
    this(16);
  }

  public DynamicArray(int capacity) {
    if (capacity < 0)
      throw new IllegalArgumentException("Illegal capacity: " + capacity);
    this.capacity = capacity;
    arr = (T[]) new Object[capacity];
  }

  public int size() {
    return len;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void clear() {
    for (int i = 0; i < len; i++) {
      arr[i] = null;
    }
    len = 0;
  }

  @Override
  public java.util.Iterator<T> iterator() {
    return new java.util.Iterator<T>() {
      int index = 0;

      @Override
      public boolean hasNext() {
        return index < len;
      }

      @Override
      public T next() {
        return arr[index++];
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public String toString() {
    if (len == 0)
      return "[]";
    else {
      StringBuilder sb = new StringBuilder(len).append("[");
      for (int i = 0; i < len - 1; i++)
        sb.append(arr[i] + ", ");
      return sb.append(arr[len - 1] + "]").toString();
    }
  }

  public void add(T element) {
    // adding to the array means we need to resize it
    if (len + 1 >= capacity) {
      // if this is first addition, capacity is 1
      if (capacity == 0) capacity = 1;
      // if this is anything but the first addition, double the size
      else capacity *= 2;

      // copy existing array to new sized arrary
      T[] new_arr = (T[]) new Object[capacity];
      for (int i = 0; i < len; i++) {
        new_arr[i] = arr[i];
      }
      arr = new_arr;
    }

    // append to the end of the new array
    int slot = len++;
    arr[slot] = element;
  }

  public T removeAt(int idx) {
    // bounds check
    if (idx >= len || idx < 0) throw new IndexOutOfBoundsException();

    T element = arr[idx];

    // resize the array down by 1
    T[] new_arr = (T[]) new Object[len - 1];
    // copy to the new array but skip the element we're deleting
    for (int i = 0, j = 0; i < len; i++, j++) {
      if (i == idx) j--;
      else new_arr[j] = arr[i];
    }
    arr = new_arr;
    len--;
    capacity = len;
    return element;
  }

  public boolean remove(T element) {
    // check for presence of element
    int idx = indexOf(element);
    if (idx == -1) return false;
    removeAt(idx);
    return true;
  }
  
  public int indexOf(Object obj) {
    for (int i = 0; i < len; i++) {
      if (obj == null) {
        if (arr[i] == null) return i;
      } else {
        if (obj.equals(arr[i])) return i;
      }
    }
    return -1;
  }

  public T get(int idx) {
    // bounds check
    if (idx >= len || idx < 0) throw new IndexOutOfBoundsException();

    return arr[idx];
  }
  
  public void set(int idx, T element) {
    // bounds check
    if (idx >= len || idx < 0) throw new IndexOutOfBoundsException();
    arr[idx] = element;
  }
}
