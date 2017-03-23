package com.maxwell.readcode.collection;

import java.util.Collection;
import java.util.Iterator;

/************************************************************************************
 * 文件功能描述：
 * 创建人：岳增存
 * 创建时间： 2017年03月23日 --  20:00 
 * 其他说明：
 * 修改时间：
 * 修改人：
 *************************************************************************************/

/**
 *
 * E：Collection中元素的类型
 */
public interface ReadCollection<E> extends Iterable<E> {

    //获取当前collection的元素个数（如果该collection中的元素个数大于Integer.MAX_VALUE，就返回Integer.MAX_VALUE）
    int size();

    //判断当前collection是否为空（不包含任何元素），为空则返回true
    boolean isEmpty();

    //判断当前collection是否包含元素o，包含则返回true
    boolean contains(Object o);

    //获取当前collection的迭代器
    Iterator<E> iterator();

    //将当前collection转换为数组
    Object[] toArray();

    //将当前collection转换为指定了元素类型的数组
    <T> T[] toArray(T[] a);

    //向当前collection添加元素e，添加成功返回true
    boolean add(E e);

    //从当前collection删除元素o，删除成功则返回true
    boolean remove(Object o);

    //判断当前collection是否包含另一个collection的所有元素，全部包含则返回true
    boolean containsAll(Collection<?> c);

    //添加另一个collection的所有元素到当前collection，添加成功则返回true
    boolean addAll(Collection<? extends E> c);

    //从当前collection中删除所有c中的元素，删除成功则返回true
    boolean removeAll(Collection<?> c);

    //保留c中的元素，将其他不在c中的元素从当前collection删除，删除成功则返回true
    boolean retainAll(Collection<?> c);

    //删除当前collection中的所有元素
    void clear();

    //判断当前collection是否与o相等，相等则返回true
    boolean equals(Object o);

    //获取当前collection的hash code值
    int hashCode();
}
