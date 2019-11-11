package com.tistory.skysign.WSTT.ANewHope;

import java.util.Arrays;

public class ANewHope {
	public void mln(String s, int[] a) {
		System.out.printf("%s ", s);
		for(int i=0; i<a.length; ++i)
			System.out.printf("[%d]%d ", i, a[i]);
		System.out.printf("\n");
	}

	public int count(int[] fw, int[] lw, int D) {
		int N = fw.length;
		int weeks = 1;

		if (Arrays.equals(fw, lw)) return 1;

		for(int i=0; i<N; ++i)
			for(int j=0; j<i; ++j)
				if(fw[i] == lw[j]) {
					int v = (i-j) / (N-D);
					if(v*(N-D) < (i-j))
						++v;
					weeks = Math.max(weeks, v);
//					System.out.printf("fw[i %d]%d lw[j %d]%d weeks(%d) v(%d)\n", i, fw[i], j, lw[j], weeks, v);
				}

		return weeks +1;
	}
}
