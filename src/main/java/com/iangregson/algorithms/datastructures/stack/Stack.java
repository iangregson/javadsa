package com.iangregson.algorithms.datastructures.stack;

public interface Stack<T> {

  public int size();
  
  public boolean isEmpty();
  
  public void push(T element);
  
  public T pop();
  
  public T peek();
}
