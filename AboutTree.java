package algorithm;
/**
 * 二叉树(遍历)
 * @author dong
 *
 */

import java.util.ArrayList;

public class AboutTree {
	public class TreeNode {			//二叉树
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;
	    public TreeNode(){}
	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	//构建二叉树	递归
/*	public void buildTree(TreeNode node,int[] data){
		TreeNode cur = node;
		
	}*/
	public TreeNode creatTree(int[] data,int index){
		if(index>data.length || data[index] == '#') return null;
		TreeNode node = new TreeNode(data[index]);
		node.left = creatTree(data, 2*index+1);
		node.right = creatTree(data, 2*index+2);
		return node;
	}
	
	
	//先序遍历	
	public void PreOrderTraverse(TreeNode node){
		//ArrayList<Integer> res = new ArrayList<>();
		if(node!=null){	
		//	res.add(node.val);				//改变这行的位置 实现 中序 后序
			System.out.println(node.val);
			PreOrderTraverse(node.left);
			PreOrderTraverse(node.right);
		}
		//return res;
	}
	
	public static void main(String[] args) {
		AboutTree aboutTree = new AboutTree();
		int[] data={1,2,4,'#','#','#',3,'#',6,'#','#'};
		TreeNode node = aboutTree.creatTree(data, 0);
		new AboutTree().PreOrderTraverse(node);

	}
}
