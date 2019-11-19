package com.tistory.skysign.WSTT.AB;

public class AB{
    public String getS(int n, char x){
        String rtn = "";

        for(int i=0; i<n; ++i){
            rtn += x;
        }

        return rtn;
    }
//  문제의 아래 규칙에서, 힌트를 좀 빨리 찾아서, 문제 푸는대 좀 쉽게 접근할 수 있었습니다.
//  0 <= i < j <= N-1
//  N 길이의 A, B 두 문자로 이루어진 스트링이 있다고 할 때,
//  배열의 index로 min_i_a라 정의 하고, 가장 왼쪽에 있는 A의 인덱스입니다.
//  이 때, 0 <= k < min_i_a 관계로 0 ~ K 까지 B 만 존재 한다면,
//  B 는 pair 에는 카운트 되지 않고, 문자열의 길이만 채워주는 역할을 하게 됩니다.

//  만들 배열은 B의 연속, A의 연속, B의 연속
//  ex) BBBAAABB N이 8 K가 6인 예제
//  이와 같은 형태로 만들어지 지게 됩니다.
    public String createString(int N, int K){
        String rtn = "";

        // 구할 수 없는 경우
        // 길이가 N일 때, 가장 많은 pair 수를 찾는 다면
        // A의 길이가 N/2
        // B의 길이가 N - (N/2)
        // 이 둘의 곱이 N길이의 K의 최대값입니다.
        if(!(K <= ((N/2) * (N - (N/2)))))
            return rtn;

        if(K == 0)
            return getS(N, 'B');

//      여기서는 Na를 A의 길이, Nb는 A 뒤에 있는 B 의 길이
//      b는 A 이전에 있는 B의 길이를 뜻합니다.
        for(int Na = 1; Na <= N/2; Na++) {
            int Nb = K/Na;
            if((K == Na * Nb) & ((Na+Nb) <= N)){
                int b = N - (Na + Nb);

                rtn += getS(b, 'B');
                rtn += getS(Na, 'A');
                rtn += getS(Nb, 'B');

                return rtn;
            }
        }

        return rtn;
    }

    public static void main(String[] args) {
        System.out.printf("%s\n", "main method");

        AB ab = new AB();
        String r = "";
        r = ab.createString(3, 2);

        System.out.printf("%s\n", r);
    }
}