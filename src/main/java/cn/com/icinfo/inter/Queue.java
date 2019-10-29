package cn.com.icinfo.inter;

public interface Queue<E> {
    public int size();

    public boolean empty();

    public void enqueue(E e);

    public E dequeue();

    public E peek();
}
