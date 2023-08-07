// 3. Longest Substring Without Repeating Characters / leetcode.com
// 문제 링크 : https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
// 문제 해설 : https://velog.io/@yejinh/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-LeetCode-Longest-Substring-Without-Repeating-Characters-3rk3n08jvu
// Submission : https://leetcode.com/submissions/detail/289113136/

public class Solution {
    public static int lengthOfLongestSubstring(String str) {
        int N = str.length();
        char[] s = str.toCharArray();
        int ll = 0;

        if(N <= 1)
            return N;

        for(int i=0; i<N; ++i) {
            // 길이가 N 인 중복된 캐릭터가 없는, longest sub array 는
            // N-1 회 비교를 하면, longest sub array인지 아닌지 편별할 수 있습니다.
            // 빅오노테이션으로는 O(N^2)
            // sub array의 시작은 i
            // sub array의 끝은 j
            for(int j=i+1; j<N; ++j) {
                int t = 1;
                for(int iBen=i; (iBen<j) && (s[iBen] != s[j]); ++iBen) {
                    ++t;
                }
                ll = Math.max(ll, t);

                // 중간에 중복된 캐릭터가 있다면,
                // 그 위치로 i 를 되돌려서, 다시 longest sub array를 찾습니다.
                if(j-i+1 != t) {
                    i = i + t -1;
                    break;
                }
            }
        }

        return ll;
    }

    public static void main(String[] args) {
        String s;
//        s = "ddd";
//        s = "abcabcbb";
//        s = "pwwkew";
//        s = "aab";
        s = "dvdf";

        System.out.println(lengthOfLongestSubstring(s));
    }
}
