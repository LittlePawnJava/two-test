package cn.com.icinfo.inter;

public interface Stack<E> {

    boolean isEmpty();

    int size();

    void push(E item); // 压栈

    E pop(); // 弹栈

    E peek(); // 查看栈顶元素
}
