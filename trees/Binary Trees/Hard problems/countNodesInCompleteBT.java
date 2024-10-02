class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        
        int left = getLeftHeight(root.left);
        int right = getRightHeight(root.right);
        
        if (left == right) return ((2 << (left)) - 1);
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    private static int getLeftHeight(TreeNode node) {
        int ans = 0;
        while (node != null) {
            node = node.left;
            ans++;
        }
        
        return ans;
    }
    
    private static int getRightHeight(TreeNode node) {
        int ans = 0;
        while (node != null) {
            node = node.right;
            ans++;
        }
        
        return ans;
    }
}