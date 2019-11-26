package com.tistory.skysign.topcoder.MeanMedian;

import java.util.Arrays;

public class MeanMedian {
    public int minEffort(int needMean, int needMedian, int[] d) {
        int subjects = d.length;
        Arrays.sort(d);
        int[] grades = new int[d.length];
        int grades_sum = needMedian * (d.length/2+1);
        int need_grades_sum = needMean * d.length;
        int r = 0;

        //initialize grades
        for(int i=0; i<=d.length/2; ++i) {
            grades[i] = needMedian;
            r += d[i] * needMedian;
        }

        if (need_grades_sum <= grades_sum) {
            return r;
        }

        for(int i=0; i<d.length; ++i) {
            while(grades[i] <10) {
                grades[i]++;
                grades_sum++;
                r += d[i];
                if (need_grades_sum <= grades_sum) {
                    return r;
                }
            }
        }

        return r;
    }

    public static void main(String[] args) {
        MeanMedian mm = new MeanMedian();
        int r = 0;
        r = mm.minEffort(2, 4, new int[]{30, 25, 20});
        System.out.println(r);

        r = mm.minEffort(4, 4, new int[]{30, 25, 20});
        System.out.println(r);

        r = mm.minEffort(10, 3, new int[]{1, 4, 3, 2, 1});
        System.out.println(r);

        r = mm.minEffort(0, 0, new int[]{1000});
        System.out.println(r);

        r = mm.minEffort(8, 3, new int[]{4, 8, 12, 16, 18, 20, 22, 23, 24});
        System.out.println(r);
    }
}
