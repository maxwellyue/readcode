package com.maxwell.readcode.collection;

import java.util.Iterator;

/************************************************************************************
 * 文件功能描述：
 * 创建人：岳增存
 * 创建时间： 2017年03月26日 --  11:53 
 * 其他说明：
 * 修改时间：
 * 修改人：
 *************************************************************************************/
public interface ReadListIterator<E> extends Iterator<E> {

    //是否有下一个元素
    boolean hasNext();

    //获取下一个元素
    E next();

    //是否有上一个元素
    boolean hasPrevious();

    //获取上一个元素
    E previous();

    //获取下一个元素的index
    int nextIndex();

    //获取上一个元素的index
    int previousIndex();

    //移除元素
    void remove();

    //设置元素
    void set(E e);

    //添加元素
    void add(E e);
}
