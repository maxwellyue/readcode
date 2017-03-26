package com.maxwell.readcode.collection;

import java.util.Collection;
import java.util.Iterator;

/************************************************************************************
 * 文件功能描述：
 * 创建人：岳增存
 * 创建时间： 2017年03月26日 --  11:34 
 * 其他说明：
 * 修改时间：
 * 修改人：
 *************************************************************************************/
public interface ReadSet<E> extends Collection<E> {

    //获取set的大小
    int size();

    //判断set是否为空
    boolean isEmpty();

    //判断set是否包含元素o
    boolean contains(Object o);

    //获取set的迭代器
    Iterator<E> iterator();

    //将set转换为数组
    Object[] toArray();

    //将set所有元素添加到数组a中
    <T> T[] toArray(T[] a);

    //添加元素
    boolean add(E e);

    //移除元素
    boolean remove(Object o);

    //判断set是否包含c中所有元素
    boolean containsAll(Collection<?> c);

    //添加c中所有元素到set中
    boolean addAll(Collection<? extends E> c);

    //使set只保留c中所有元素
    boolean retainAll(Collection<?> c);

    //移除set中c中所有元素
    boolean removeAll(Collection<?> c);

    //清空set
    void clear();

    //set的equals()方法
    boolean equals(Object o);

    //set的hashCode()方法
    int hashCode();
}
