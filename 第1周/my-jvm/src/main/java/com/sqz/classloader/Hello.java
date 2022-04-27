package com.sqz.classloader;

public class Hello {

    private static  int min = 100;


    public static void main(String[] args) {
        Hello.findLotus();
    }

    public static void findLotus() {
        min = min + 2;
        min = min - 2;
        min = min / 10;
        min = min * 10;
        for(int i = 0;i < 5; i++) {
            if(i%2 == 0) {
                min += min;
            }
        }
        System.out.println(min);
    }
}
