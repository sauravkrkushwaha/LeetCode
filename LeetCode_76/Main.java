package LeetCode_76;
import java.util.*;
class Solution {
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }

        Map<Character, Integer> dictT = new HashMap<>();
        for (char c : t.toCharArray()) {
            int count = dictT.getOrDefault(c, 0);
            dictT.put(c, count + 1);
        }

        int required = dictT.size();
        int l = 0, r = 0;
        int formed = 0;

        Map<Character, Integer> windowCounts = new HashMap<>();
        int[] ans = { -1, 0, 0 };

        while (r < s.length()) {
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            while (l <= r && formed == required) {
                c = s.charAt(l);

                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }

                l++;
            }

            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage
        String s = "ADOBECODEBANC";
        String t = "ABC";

        String result = solution.minWindow(s, t);

        System.out.println("Minimum window that contains all characters of '" + t + "' in '" + s + "': " + result);
    }
}

