package com.iangregson.algorithms.datastructures.queue;

public class ArrayQueue<T> implements Queue<T> {

  private int front;
  private int back;
  private Object[] data;

  public ArrayQueue(int capacity) {
    data = new Object[capacity + 1];
    front = 0;
    back = 0;
  }

  public int size() {
    return adjustIndex(back + data.length - front, data.length);
  }

  public boolean isEmpty() {
    return front == back;
  }

  public boolean isFull() {
    return (front + data.length - back) % data.length == 1;
  }

  public void offer(T element) {
    if (isFull()) throw new RuntimeException("Queue is full");
    data[back++] = element;
    back = adjustIndex(back, data.length);
  }

  private int adjustIndex(int index, int size) {
    return index >= size ? index - size : index;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    front = adjustIndex(front, data.length);
    return (T) data[front];
  }
  
  @SuppressWarnings("unchecked")
  public T poll() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    
    front = adjustIndex(front, data.length);
    return (T) data[front++];
  }
}