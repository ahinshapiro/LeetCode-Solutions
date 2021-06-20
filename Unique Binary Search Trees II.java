/*
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

 

Example 1:


Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
Example 2:

Input: n = 1
Output: [[1]]
 

Constraints:

1 <= n <= 8
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<String> duplicateFinder = new ArrayList<String>();
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> al = new ArrayList<>();
        int[] a = new int[n];
        for(int i = 0 ; i < n ; i++){
            a[i] = i+1;
        }
        findPermutation(a,0, al);
        return al;
    }
    public void findPermutation(int[] a, int k , List<TreeNode> al){
        if( k == a.length ){
            TreeNode x = createBST(a);
            if(isValidTree(x)){
                al.add(x);
            }
        }else{
            for(int i = k ; i < a.length ; i++){
                int temp = a[i];
                a[i] = a[k];
                a[k] = temp;
                findPermutation(a,k+1,al);
                temp = a[i];
                a[i] = a[k];
                a[k] = temp;
            }
        }
    }
    public TreeNode createBST(int[] a){
        int i = 1;
        TreeNode root = new TreeNode(a[0]);
        while(i < a.length){
            createSubNodes(root,a[i]);
            i++;
        }
        return root;
    }
    public void createSubNodes(TreeNode root, int value){
        if(root == null)
            return;
        if(value < root.val){
            if(root.left != null){
                createSubNodes(root.left,value);   
            }else{
                root.left = new TreeNode(value);
                return;
            }
        }else{
            if(root.right != null){
                createSubNodes(root.right,value);   
            }else{
                root.right = new TreeNode(value);
                return;
            }
        }
        return;
    }
    public Boolean isValidTree(TreeNode node)
    {
        ArrayDeque<TreeNode> ad = new ArrayDeque<>();
        ad.add(node);
        List<Integer> data = new ArrayList<Integer>();
        data.add(node.val);
        while(!ad.isEmpty()){
            TreeNode x = ad.poll();
            if(x.left == null && x.right == null){
                continue;
            }
            if(x.left != null){
                ad.add(x.left);
                data.add(x.left.val);
            }else{
                data.add(null);
            }
            if(x.right != null){
                ad.add(x.right);
                data.add(x.right.val);
            }else{
                data.add(null);
            }
        }
        int i = data.size() - 1;
        for( ; i >= 0 ; i--){
            if(data.get(i) != null){
                break;
            }else{
                data.remove(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int x = 0 ;x <= i ; x++){
            sb.append(data.get(x));
        }
        String ss = sb.toString();
        if(!duplicateFinder.contains(ss)){
            duplicateFinder.add(ss);
            return true;
        }
        return false;
    }
}
