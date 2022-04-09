package com.iangregson.algorithms.datastructures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class ListStack<T> implements Iterable<T>, Stack<T> {

  private LinkedList<T> list = new LinkedList<T>();

  public ListStack() { }
  
  public ListStack(T element) {
    push(element);
  }

  public int size() {
    return list.size();
  }
  
  public boolean isEmpty() {
    return list.isEmpty();
  }
  
  public void push(T element) {
    list.addFirst(element);
  }
  
  public T pop() {
    if (isEmpty()) throw new EmptyStackException();
    return list.removeFirst();
  }
  
  public T peek() {
    if (isEmpty()) throw new EmptyStackException();
    return list.peekFirst();
  }

  @Override
  public Iterator<T> iterator() {
    return list.iterator();
  }
}