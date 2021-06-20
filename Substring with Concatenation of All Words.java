/*
You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.

You can return the answer in any order.

 

Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
 

Constraints:

1 <= s.length <= 104
s consists of lower-case English letters.
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] consists of lower-case English letters.
*/

class Solution {
    public static List<String> splitArrayWithValue(String a, int pos){
        int aLength = a.length();
        List<String> positions = new ArrayList<String>();
        for(int i = 0 ;i < aLength ; i+= pos){
            String str = a.substring(i, i+pos);
            positions.add(str);
        }
        return positions;
    }
    public static List<String> getListFromStringArray(String[] a){
        List<String> positions = new ArrayList<String>();
        for(String arr : a){
            positions.add(arr);
        }
        return positions;
    }
    public static Boolean isSame(List<String> subStringArray, List<String> basicStringArray){
        for(int i = 0 ;i < basicStringArray.size() ; i++){
            String s = basicStringArray.get(i);
            subStringArray.remove(s);
        }
        if(subStringArray.size() == 0)
            return true;
        return false;
    }
    public List<Integer> findSubstring(String s, String[] words) 
    {
        List<Integer> positions = new ArrayList<Integer>();
        int firstWordLength = words[0].length();
        int wordsPos = firstWordLength*words.length;
        int sLength = s.length();
        List<String> basicStringArray = getListFromStringArray(words);
        
        for( int i = 0 ;i <= sLength-wordsPos ; i++ ){
            List<String> subStringArray = splitArrayWithValue(s.substring(i, i+wordsPos),firstWordLength);
            if(isSame(subStringArray,basicStringArray)){
                positions.add(i);
            }
        }
        
        return positions;
    }
}
