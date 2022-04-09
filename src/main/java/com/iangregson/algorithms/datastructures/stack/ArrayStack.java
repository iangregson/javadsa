package com.iangregson.algorithms.datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

import javax.annotation.Nullable;

public class ArrayStack<T> implements Stack<T> {

  private int len = 0;
  private int capacity = 16;
  private Object[] data;

  public ArrayStack() {
    data = new Object[capacity];
  }

  public int size() {
    return len;
  }
  
  public boolean isEmpty() {
    return len == 0;
  }
  
  public void push(T element) {
    if (len == capacity) {
      increaseCapacity();
    }

    len++;
    
    data[len] = element;
  }

  private void increaseCapacity() {
    capacity *= 2;
    data = Arrays.copyOf(data, capacity);
  }
  
  @SuppressWarnings("unchecked")
  public T pop() {
    if (isEmpty()) throw new EmptyStackException();
    
    T element = (T) data[len];
    data[len] = null;
    len--;
    
    return element;
  }
  
  @SuppressWarnings("unchecked")
  public T peek() {
    if (isEmpty()) throw new EmptyStackException();
    return (T) data[len];
  }
}