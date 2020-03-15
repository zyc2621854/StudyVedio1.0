package com.example.studyvedio;

public class MyJni {

    static {
        System.loadLibrary("MyJni");
    }
    /** 方法名不正确时报的错： java.lang.UnsatisfiedLinkError: No implementation found for java.lang.String com.example.studyvedio.MyJni.getString1() (tried Java_com_example_studyvedio_MyJni_getString1 and Java_com_example_studyvedio_MyJni_getString1__)**/
    public native static String getString();
}