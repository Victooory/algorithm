package algorithm;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 * @author dong
 *
 */
public class TreeSubstructure {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	}
	public boolean HasSubtree(TreeNode root1,TreeNode root2){
		boolean flag = false;
		if(root1!=null&&root2!=null){
			if(root1.val == root2.val){
				flag = nextTree(root1,root2);
			}
			if(!flag){
				flag = HasSubtree(root1.left, root2);
			}
			if(!flag){
				flag = HasSubtree(root1.right, root2);
			}
		}
		return flag;
	}
	public boolean nextTree(TreeNode root1,TreeNode root2){			//继续找是否完全相等
		if(root2==null){
			return true;
		}
		if(root1==null){
			return false;
		}
		if(root1.val==root2.val){
			return nextTree(root1.left, root2.left)&&nextTree(root1.right, root2.right); 
		}
		return false;
	}
}
