package com.maxwell.readcode.collection;

import java.util.*;

/************************************************************************************
 * 文件功能描述：
 * 创建人：岳增存
 * 创建时间： 2017年03月22日 --  20:43 
 * 其他说明：
 * 修改时间：
 * 修改人：
 *************************************************************************************/
public class Read {

    Collection collection = null;
    AbstractCollection abstractCollection = null;
    List list= null;
    Set set = null;
    Queue queue = null;
    AbstractList abstractList = null;
    ArrayList arrayList = null;

    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        for (Iterator i=list.iterator(); i.hasNext();){
            System.out.println(i.next());
        }
    }


































}
