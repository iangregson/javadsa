package com.iangregson.algorithms.datastructures.queue;

import java.util.LinkedList;

public class ListQueue<T> implements Queue<T> {
  
  private LinkedList<T> list = new LinkedList<>();
  
  public void offer(T element) {
    list.addLast(element);
  }
  
  public T poll() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    return list.removeFirst();
  }
  
  public T peek() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    return list.peekFirst();
  }
  
  public int size() {
    return list.size();
  }
  
  public boolean isEmpty() {
    return list.isEmpty();
  }
}