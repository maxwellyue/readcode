package com.maxwell.readcode.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/************************************************************************************
 * 文件功能描述：
 * 创建人：岳增存
 * 创建时间： 2017年03月26日 --  11:19 
 * 其他说明：
 * 修改时间：
 * 修改人：
 *************************************************************************************/
public interface ReadList<E> extends Collection<E> {

    //获取list大小
    int size();

    //判断list是否为空
    boolean isEmpty();

    //判断list是否包含元素o
    boolean contains(Object o);

    //获取list的迭代器
    Iterator<E> iterator();

    //将list转换为数组
    Object[] toArray();

    //将list的元素添加到数组a中
    <T> T[] toArray(T[] a);

    //添加元素
    boolean add(E e);

    //移除元素
    boolean remove(Object o);

    //判断list是否包含c中所有元素
    boolean containsAll(Collection<?> c);

    //添加所有c中元素到list中
    boolean addAll(Collection<? extends E> c);

    //添加所有c中元素到list中：从位置index开始
    boolean addAll(int index, Collection<? extends E> c);

    //移除list中与c中元素相同的元素
    boolean removeAll(Collection<?> c);

    //保留list中与c中元素相同的元素
    boolean retainAll(Collection<?> c);

    //清除list中所有元素
    void clear();

    //equals方法
    boolean equals(Object o);

    //获取list的hash code值
    int hashCode();

    //获取index位置的元素
    E get(int index);

    //设置index位置处的元素
    E set(int index, E element);

    //添加元素到index位置
    void add(int index, E element);

    //移除index位置的元素
    E remove(int index);

    //获取元素o在list中第一次出现的位置
    int indexOf(Object o);

    //获取元素o在list中最后一次出现的位置
    int lastIndexOf(Object o);


    // List Iterators

    //获取list的ListIterator
    ListIterator<E> listIterator();

    //获取list的从index位置开始的ListIterator
    ListIterator<E> listIterator(int index);

    // Viewlist

    //获取子
    List<E> subList(int fromIndex, int toIndex);
}

