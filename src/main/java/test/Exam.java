package test;

import java.util.ArrayList;

public class Exam {
    public static void main(String[] args) {
        int a = 4;

        /**
         * 2
         * 2 4
         * 2 4 8
         * 2 4 8 16
         */

//        for (int i = 2; i < a; i++) {
//            for (int j = 2; j <= i; j++) {
//                System.out.println(j);
//            }
//        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }


}
