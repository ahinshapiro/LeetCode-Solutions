/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        return isBreak(wordDict,s,0,new HashMap<Integer,Boolean>());
    }
    public boolean isBreak(List<String> wordDict, String s, int pos, Map<Integer,Boolean> mp){
        if(mp.containsKey(pos)) return mp.get(pos);
        if(pos > s.length()){
            return false;
        }
        if(pos == s.length()){
            return true;
        }
        for(int i = 0 ;i < wordDict.size(); i++)
        {
            String data = wordDict.get(i);
            int dataLength = data.length();
            if(pos+dataLength > s.length()) continue;
            if(!data.equals(s.substring(pos,pos+dataLength))) continue;
            boolean newData = isBreak(wordDict,s,pos+dataLength,mp);
            mp.put(pos,newData);
            if(newData) return true;
        }
        return false;
    }
}
