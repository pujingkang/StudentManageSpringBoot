package com.souls.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynDemo implements InvocationHandler {
    private Object obj;
    private BeforeMethod befor;

    public DynDemo(){}
    public DynDemo(Object obj){
        this.obj = obj;
    }

    public DynDemo(Object obj,BeforeMethod befor){
        this.obj = obj;
        this.befor = befor;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());

        if(method.getName().equals("hello"))
        {
            return method.invoke(obj, args);  //�����ĵ�����ԭ����

        }

        System.out.println("before calling " + method);
        if(befor!=null)
            befor.say();
        //System.out.println(this.obj);
        Object result= method.invoke(obj, args);  // ִ�б��������������
        // obj.say(args);
        System.out.println("after calling " + method);

        return result;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
