package com.ay.test.concurrentDemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class AvoidConcurrentModificationException {
    public static void main(String[] args) throws Exception {

        List<String> myList = new CopyOnWriteArrayList<String>();

        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        Iterator<String> it = myList.iterator();
        while (it.hasNext()) {
            String value = it.next();
            System.out.println("List Value:" + value);
            if (value.equals("3")) {
                myList.remove("4");
                myList.add("6");
                myList.add("7");
            }
        }
        System.out.println("List Size:" + myList.size());

        Map<String, String> myMap = new ConcurrentHashMap<String, String>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        myMap.put("3", "3");

        Iterator<String> it1 = myMap.keySet().iterator();
        while (it1.hasNext()) {
            String key = it1.next();
            System.out.println("Map Value:" + myMap.get(key));
            if (key.equals("1")) {
                myMap.remove("3");
                myMap.put("4", "4");
                myMap.put("5", "5");
            }
        }
        System.out.println("Map Size:" + myMap.size());

        Map<String, String> myMap2 = new ConcurrentHashMap<String, String>();
        myMap2.put("1", "1");
        myMap2.put("2", "2");
        myMap2.put("3", "3");
        myMap2.put("4", "4");
        myMap2.put("5", "5");
        myMap2.put("6", "6");
        myMap2.put("7", "7");
        myMap2.put("8", "8");

        Iterator<String> it22 = myMap2.keySet().iterator();
        while (it22.hasNext()) {
            String key = it22.next();
            System.out.println("Map Value:" + myMap2.get(key));
            if (key.equals("1")) {
                myMap2.remove("3");
                myMap2.put("9", "9");

            }
        }
        System.out.println("Map Size:" + myMap2.size());

        for (int i = 0; i < 38; i++) {
            //将对象写入流中
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(myMap2);

            //从流中取出
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            myMap2 = (ConcurrentHashMap<String, String>) objectInputStream.readObject();
            Iterator<String> it2 = myMap2.keySet().iterator();
            while (it2.hasNext()) {
                String key = it2.next();
                System.out.println("Map Value:" + myMap2.get(key));
                if (key.equals("1")) {
                    myMap2.remove("3");
                    myMap2.put("9", "9");

                }
            }
            System.out.println("Map Size:" + myMap2.size());
            System.out.println("--------------------------------------------------------------------");

        }

    }
}
