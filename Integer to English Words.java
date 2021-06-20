/*
Convert a non-negative integer num to its English words representation.

 

Example 1:

Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:

Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: num = 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 

Constraints:

0 <= num <= 231 - 1
*/


class Solution {
    Map<Integer,String> mpSingle = new HashMap<Integer,String>(){{
        put(1,"One") ;
        put(2,"Two") ;
        put(3,"Three") ;
        put(4,"Four") ;
        put(5,"Five") ;
        put(6,"Six") ;
        put(7,"Seven") ;
        put(8,"Eight") ;
        put(9,"Nine") ;
    }};
    Map<Integer,String> mpTens = new HashMap<Integer,String>(){{
        put(10,"Ten") ;
        put(20,"Twenty") ;
        put(30,"Thirty") ;
        put(40,"Forty") ;
        put(50,"Fifty") ;
        put(60,"Sixty") ;
        put(70,"Seventy") ;
        put(80,"Eighty") ;
        put(90,"Ninety") ;
    }};
    Map<Integer,String> mpTwenties = new HashMap<Integer,String>(){{
        put(11,"Eleven") ;
        put(12,"Twelve") ;
        put(13,"Thirteen") ;
        put(14,"Fourteen") ;
        put(15,"Fifteen") ;
        put(16,"Sixteen") ;
        put(17,"Seventeen") ;
        put(18,"Eighteen") ;
        put(19,"Nineteen") ;
    }};
    Map<Integer,String> mpTotal = new HashMap<Integer,String>(){{
        put(6,"Million") ;
        put(3,"Thousand") ;
        put(9,"Billion") ;
        put(2,"Hundred") ;
    }};
    List<Integer> arr = new ArrayList<Integer>();
    public String numberToWords(int num)
    {
        if(num == 0){
            return "Zero";
        }
        Set<Integer> set = mpTotal.keySet();
        for(Integer key : set){
           arr.add(key);
        }
        Collections.sort(arr,Collections.reverseOrder());
        return nTW(String.valueOf(num),true);
    }
    private String nTW(String n, boolean switchValues)
    {
        if(n.equals("")){
            return n;
        }
        if( mpTwenties.get(Integer.parseInt(n)) != null ){
            return mpTwenties.get(Integer.parseInt(n))+"";
        }
        else if(mpTens.get(Integer.parseInt(n)) != null ){
            return mpTens.get(Integer.parseInt(n))+"";
        }
        else if(mpSingle.get(Integer.parseInt(n)) != null ){
            return mpSingle.get(Integer.parseInt(n));
        }
        n = String.valueOf(Integer.parseInt(n));
        int totalLength = n.length();
        if(totalLength > 0){
            String leadingZeros = "";
            Integer fPos = Integer.parseInt(n.charAt(0)+"");
            String front = n;
            if(switchValues){
                for(int i = 0 ; i < totalLength-1 ; i++){
                    leadingZeros+="0";
                }
                front = n.charAt(0)+leadingZeros;
            }
            StringBuilder sb = new StringBuilder();
            String word = "";
            if(mpTotal.get(leadingZeros.length()) != null ){
                sb.append(mpSingle.get(fPos) + " " + mpTotal.get(leadingZeros.length()));
                if(!front.equals(n)){
                    sb.append(" ");
                }
            }
            else if( mpTwenties.get(Integer.parseInt(front)) != null ){
                sb.append(mpTwenties.get(Integer.parseInt(front))+" ");
            }
            else if(mpTens.get(Integer.parseInt(front)) != null ){
                sb.append(mpTens.get(Integer.parseInt(front))+" ");
            }
            else if(mpSingle.get(Integer.parseInt(front)) != null ){
                sb.append(mpSingle.get(Integer.parseInt(front)));
            }
            else{
                for(int i = 0 ; i<arr.size() ; i++){
                    if(arr.get(i) < n.length())
                    {
                        String balance = n.substring(0,n.length()-arr.get(i));
                        String rest = n.substring(n.length()-arr.get(i),n.length());
                        if(Integer.parseInt(rest) == 0){
                            return nTW(balance,true) +" "+mpTotal.get(arr.get(i));
                        }else{
                            return nTW(balance,true) +" "+mpTotal.get(arr.get(i))+" "+ nTW(rest,true);
                        }
                    }
                }
            }
            return sb.append(nTW(n.substring(1,n.length()),true)).toString();
        }
        return "";
    }
}
