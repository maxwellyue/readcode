package com.maxwell.readcode.collection;

import java.util.Collection;

/************************************************************************************
 * 文件功能描述：
 * 创建人：岳增存
 * 创建时间： 2017年03月26日 --  11:41 
 * 其他说明：
 * 修改时间：
 * 修改人：
 *************************************************************************************/
public interface ReadQueue<E> extends Collection<E> {

    //添加元素
    boolean add(E e);

    //添加元素
    boolean offer(E e);

    //取出并移除队头元素（queue为空队列时，抛出异常）
    E remove();

    //取出并移除队头元素（queue为空队列时，返回null）
    E poll();

    //获取队头元素（queue为空队列时，抛出异常）
    E element();

    //获取队头元素（queue为空队列时，返回null）
    E peek();
}