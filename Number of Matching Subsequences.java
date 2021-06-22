/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 

Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2
 

Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
*/

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        Map<String,Integer> wordsMap = new HashMap<String,Integer>();
        Set<String> wordsSet = new TreeSet<String>();
        for(int i = 0 ; i < words.length ; i++){
            if(wordsMap.get(words[i]) == null){
                wordsMap.put(words[i],1);
            }else{
                Integer existingData = wordsMap.get(words[i]);
                wordsMap.put(words[i],existingData+1);
            }
            wordsSet.add(words[i]);
        }
        List<String> wordsList = new ArrayList<String>(wordsSet);
        char[] str = s.toCharArray();
        int totalCount = 0;
        for(int i = 0 ; i < wordsList.size() ; i++){
            String strWord = wordsList.get(i);
            int x = 0;
            int y = 0;
            int strWordLength = strWord.length();
            int count = 0;
            while(x < str.length && y < strWordLength){
                if(strWord.charAt(y) == str[x]){
                   count++;
                    y++;
                }
                x++;
            }
            if(count == strWordLength){
                totalCount+=wordsMap.get(strWord);
            }
        }
        return totalCount;
    }
}


/*
Runtime: 70 ms, faster than 61.63% of Java online submissions for Number of Matching Subsequences.
Memory Usage: 39.9 MB, less than 81.94% of Java online submissions for Number of Matching Subsequences.
*/

