package com.iangregson.algorithms.datastructures.queue;

public interface Queue<T> {
  public void offer(T element);
  
  public T poll();
  
  public T peek();
  
  public int size();
  
  public boolean isEmpty();
}