package com.tistory.skysign.hackerrank.java.Strings.TagContentExtractor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCases = Integer.parseInt(scan.nextLine());

        while (testCases-- > 0) {
            String line = scan.nextLine();

            boolean matchFound = false;
//          문제가 너무 어려워서 못 풀고 있다가,
//          다른 사람의 풀이를 보고 이해 했습니다.
//          정규표현식은 봐도봐도, 잘 하는 사람의 코드를 보면, 참 따라가기 어려운 것 같습니다.
//          <(.+)>([^<]+)</\1> 이 코드를 좀 살펴보겠습니다.
//          크게 3부분 인대요,
//          첫번째, <(.+)> <h1> 과 같은 부분을 찾아 내는 코드구요.
//          두번째, </\1> back reference #1 로 써, 앞에 <h1> 과 매칭되는 </h1>을 찾는 코드 입니다.
//          세번째로, 가운대 부분이 오묘한 코드입니다.
//          ([^<]+) <를 제외한 모든 문자열의 반복으로 <h1>abcd</h1>에서 abcd에 매칭되는 부분입니다.
//          이부분을 모든 문자열의 반복이란 의미로 (.+) 이렇게 작성할 수도 있습니다.
//          하지만, <(.+)>(.+)</\1> 이렇게 작성하면, 입력값이 <h1><h1>abcd</h1></h1> 이런 경우에
//          <h1>abcd</h1> 이 매칭되게 됩니다. 이 문제를 피하기 위해서,
//          태그를 구성하는 글자 < > 둘 중 한자를 제외한 모든 문자인 ([^<]+)로 작성한 것입니다.
//          물론 ([^>]+) 이렇게 작성해도, 모든 테스트 케이스를 패스 할 수 있습니다.
//          정규표현식의 세계는 참 오묘하네요 ^^a
            Pattern r = Pattern.compile("<(.+)>([^<]+)</\\1>");
//          Pattern r = Pattern.compile("<(.+)>([^>]+)</\\1>"); 이 것도 정답입니다.
            Matcher m = r.matcher(line);

            while (m.find()) {
                System.out.println(m.group(2));
                }
                matchFound = true;
            }
            if ( ! matchFound) {
                System.out.println("None");
            }
        }
    }
}
