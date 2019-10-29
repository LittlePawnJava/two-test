package cn.com.icinfo.inter;

public interface List<E> {

    //返回线性表的大小
    public int getSize();

    /**
     * 判断线性表中是否为空
     */
    public boolean isEmpty();

    /**
     * 判断线性表中是否包含元素o
     */
    boolean contains(E o);

    //在线性表中查找元素o，若成功找到，返回其位置index；否则，返回-1
    public int indexOf(E e);

    //获取线性表中 位置为index的元素
    public E get(int index);

    //将线性表中 位置为index的元素设置为e
    public void set(int index, E e);

    //在线性表中位置为index处添加元素e
    public void add(int index, E e);

    //删除并返回线性表中位置为index的元素
    public E remove(int index);
}
