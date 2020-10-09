# Strings

+ [Group Anagrams](#group-anagrams)
+ [Valid Palindrome](#valid-palindrome)
+ [Longest Palindromic Substring](#-longest-palindromic-substring)
+ [Reverse Linked List](#-206.-reverse-linked-list)
+ [Palindromic Substrings](#palindromic-substrings)

##  Group Anagrams

https://leetcode.com/problems/group-anagrams/

```java
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {

                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
```

##  Valid Palindrome

https://leetcode.com/problems/valid-palindrome/

```java
 public boolean isPalindrome(String s) {
 if(s.length() <= 1) return true;
 int st = 0, en = s.length()-1;
 while(st < en){
 while(st < en && !Character.isLetterOrDigit(s.charAt(st))) st++;
 while(st < en && !Character.isLetterOrDigit(s.charAt(en))) en--;
 if(st < en && Character.toLowerCase(s.charAt(st)) != Character.toLowerCase(s.charAt(en))) return false;
 st++;
 en--;
        }
 return true;
    }
```


## Longest Palindromic Substring

https://leetcode.com/problems/longest-palindromic-substring/

```java
    public String longestPalindrome(String s) {
        if(s.length() <= 1) return s;
        int max_len = 1;
        int n = s.length();
        int st = 0, end = 0;

        // нечетная длина
        for(int i = 0; i < n-1; ++i){
            int l = i, r = i;
            while(l >= 0 && r < n){
                if(s.charAt(l) == s.charAt(r)){
                    l--; r++;
                }else
                    break;
            }
            int len = r-l-1;
            if(len > max_len){
                max_len = len;
                st = l+1;
                end = r-1;
            }
        }

        // четная длина
        for(int i = 0; i < n-1; ++i){
            int l = i, r = i+1;
            while(l >= 0 && r < n){
                if(s.charAt(l) == s.charAt(r)){
                    l--; r++;
                }else
                    break;
            }
            int len = r-l-1;
            if(len > max_len){
                max_len = len;
                st = l+1;
                end = r-1;
            }
        }

        return s.substring(st, end + 1);
    }
```

##  Reverse Linked List

 https://leetcode.com/problems/reverse-linked-list/

```java
   public ListNode reverseList(ListNode head) {
     ListNode prev = null;
     ListNode curr = head;
     while (curr != null) {
         ListNode nextTemp = curr.next;
         curr.next = prev;
         prev = curr;
         curr = nextTemp;
     }
     return prev;
 }

```

##Palindromic Substrings

https://leetcode.com/problems/palindromic-substrings/

```java

int ans = 0;
public int countSubstrings(String s) {
    for(int i = 0; i <= s.length(); i++){
        countSubstrings(s, i, i);
        countSubstrings(s, i, i + 1);
    }
    return ans;
}

private void countSubstrings(String s, int left, int right){
    while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
        left--;
        right++;
        ans++;
    }
}
```
