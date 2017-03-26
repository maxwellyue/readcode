package com.maxwell.readcode.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/************************************************************************************
 * 文件功能描述：
 * 创建人：岳增存
 * 创建时间： 2017年03月23日 --  21:31 
 * 其他说明：
 * 修改时间：
 * 修改人：
 *************************************************************************************/

/**
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @see Collection
 * @since 1.2
 * @param <E>
 */
public abstract class ReadAbstractCollection <E> implements Collection<E> {

    //唯一的构造器（包访问权限），用来被子类构造器调用（通常是隐式调用）
    protected ReadAbstractCollection() {
    }

    // Query Operations

    //抽象方法：获取迭代器
    public abstract Iterator<E> iterator();

    //抽象方法：获取大小
    public abstract int size();

    //判断是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    //判断是否包含元素o：
    //分两种情况：元素为空和元素不为空
    public boolean contains(Object o) {
        Iterator<E> it = iterator();//获取迭代器
        if (o==null) {//如果元素o为空
            while (it.hasNext())//本次迭代还有元素
                if (it.next()==null)//本次迭代的下一个元素为空
                    return true;
        } else {//如果元素o不为空
            while (it.hasNext())//次迭代还有元素
                if (o.equals(it.next()))//本次迭代的下一个元素与o相等
                    return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation returns an array containing all the elements
     * returned by this collection's iterator, in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * The length of the returned array is equal to the number of elements
     * returned by the iterator, even if the size of this collection changes
     * during iteration, as might happen if the collection permits
     * concurrent modification during iteration.  The {@code size} method is
     * called only as an optimization hint; the correct result is returned
     * even if the iterator returns a different number of elements.
     *
     *
     *
     */
    public Object[] toArray() {
        Object[] r = new Object[size()];               //1
        Iterator<E> it = iterator();                   //2
        for (int i = 0; i < r.length; i++) {           //3
            if (! it.hasNext())                        //4
                return Arrays.copyOf(r, i);            //5
            r[i] = it.next();                          //6
        }                                              //7
        return it.hasNext() ? finishToArray(r, it) : r;//8
    }
    /**
     *
     * 该方法是将集合转换为一个数组，该数组包含了所有当前集合的迭代器返回的元素，按照迭代器返回的顺序，元素被
     * 从位置0开始，连续地依次存放在数组中。该数组的长度等于当前迭代器返回的元素的个数，即使当前集合在迭代过程中
     * 被修改（迭代过程中进行并发操作，如增加或移除元素等）。即使迭代器返回不同数量的元素，也可以返回正确的结果。
     *
     * 该方法等同于：
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray();
     * }
     *
     * 正常思路是:取出集合中所有元素，依次放在数组中就可以了，如下：
     *
     *  Object[] r = new Object[size()];
     *  Iterator<E> it = iterator();
     *  for (int i = 0; i < r.length; i++) {
     *      if (it.hasNext()){
     *          r[i] = it.next();
     *      }
     *   }
     *  return r;
     *
     * 但是，在允许并发操作的时候，如果你在一个线程A进行迭代操作，而在另一个线程B对操作的集合进行了添加/移除元素的操作，就会出问题。
     * 对于元素减少的情况，通过4和5来进行处理：如果元素数量比预期少，则减少数组分配空间
     * 对于元素增多的情况，通过8中finishToArray()进行处理：如果元素数量比预期多，则重新分配数组空间，策略是newLength = originLength + 1/2  originLength + 1,
     * 但是如果在迭代结束后，在线程B中继续修改了当前集合，该方法返回的结果仍然是不准确的，所以说是线程不安全的。
     *
     */


    public <T> T[] toArray(T[] a) {
        // Estimate size of array; be prepared to see more or fewer elements
        int size = size();
        T[] r = a.length >= size ? a :
                (T[])java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = iterator();

        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) { // fewer elements than expected
                if (a == r) {
                    r[i] = null; // null-terminate
                } else if (a.length < i) {
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            r[i] = (T)it.next();
        }
        // more elements than expected
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    //定义最大数组大小
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     *当迭代器返回元素比预期返回元素多，重新分配数组
     * @param r 存储之前元素的数组
     * @param it 当前迭代器
     * @return  数组：原数组的所有元素 + 迭代器返回的多余的元素
     */
    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;//定义i为数组的大小
        while (it.hasNext()) {//当迭代器可以返回更多元素
            int cap = r.length;//定义cap为数组的大小
            if (i == cap) {//如果i=cap
                int newCap = cap + (cap >> 1) + 1;//定义newCap = cap + 1/2 cap + 1
                // 这两句代码是为了防止数组大小太大，对数组的大小进行了处理
                if (newCap - MAX_ARRAY_SIZE > 0)//如果newCap大于MAX_ARRAY_SIZE
                    newCap = hugeCapacity(cap + 1);
                r = Arrays.copyOf(r, newCap);//将r中的元素copy到一个大小为newCap的新数组中
            }
            r[i++] = (T)it.next();
        }
        // trim if overallocated
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    //获取容量
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError
                    ("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    // Modification Operations

    //添加元素（实现在哪？）
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    //移除元素
    public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if (o==null) {//o为空的情况
            while (it.hasNext()) {
                if (it.next()==null) {
                    it.remove();
                    return true;
                }
            }
        } else {//o不为空的情况
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }



    //添加c中所有元素到当前集合中
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    //将c中的原有元素添加到当前集合中
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
    }

    //从当前集合中移除所有c中包含的元素
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Iterator<?> it = iterator();//当前集合的迭代器（而非c的迭代器）
        while (it.hasNext()) {//迭代器还有可以访问的元素
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }


    //保留c中的元素，其余元素都移除
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<E> it = iterator();//当前集合的迭代器（而非c的迭代器）
        while (it.hasNext()) {//迭代器还有可以访问的元素
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }


    //清空元素
    public void clear() {
        Iterator<E> it = iterator();
        while (it.hasNext()) {//是否有可访问的元素
            it.next();//迭代器指向某个元素，假如为a
            it.remove();//将a移除
        }
    }



    //将集合转换为字符串
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";// 如果是空集合，返回  "[]"

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {//对集合遍历
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

}
