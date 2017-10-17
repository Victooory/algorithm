package algorithm.some_implementation;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉树(遍历)
 * 
 * @author dong
 *
 */
public class AboutTree {
	public class TreeNode { // 二叉树
		int val = 0;
		TreeNode left;
		TreeNode right;

		public TreeNode() {
		}

		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	public static void main(String[] args) {

	}
	/**
	 * 二叉搜索树转链表 
	 * @param pRootOfTree
	 * @return
	 */
	public TreeNode Convert(TreeNode pRootOfTree) {
		Stack<TreeNode> stack = new Stack<>();
		while(pRootOfTree != null){
			
		}
        return null;
    }
	/**
	 * 先序遍历非递归
	 * @param root
	 */
	public void preorderTraverse(TreeNode root){
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pNode = root;
		while(pNode != null || !stack.isEmpty()){
			if(pNode!=null){
				System.out.println(pNode.val + " ");
				stack.push(pNode);
				pNode = pNode.left;
			}else {
				pNode = stack.pop();
				pNode = pNode.right;
			}
			
		}
		/*
		while(pNode != null || !stack.isEmpty()){
			while(pNode != null){
				System.out.println(pNode);
				stack.push(pNode);
				pNode = pNode.left;
			}
			if(!stack.isEmpty()){
				pNode = stack.pop();
				pNode = pNode.right;
			}
		}
		*/
	}
	/**
	 * 中序遍历非递归
	 */
	public void inorderTraverse(TreeNode root){
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pNode = root;
		while(!stack.isEmpty()||pNode!=null){
			while(pNode!=null){
				//sout这里是前序遍历
				stack.push(pNode);
				pNode = pNode.left;
			}
			if(!stack.isEmpty()){
				pNode = stack.pop();
				System.out.println(pNode.val);
				pNode = pNode.right;
			}
		}
		/*//和前面等价
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pNode = root;
		while(!stack.isEmpty() || pNode != null){
			if(pNode != null){
				stack.push(pNode);
				pNode = pNode.left;
			}else{
				pNode = stack.peek();
				stack.pop();
				System.out.println(pNode.val);
				pNode = pNode.right;
			}
		}
		*/
	}
	
	/**
	 * 镜像二叉树
	 * @param root
	 */
	public void Mirror(TreeNode root) {
		if(root == null) return ;
		TreeNode node = new TreeNode();
		if(root.left!=null || root.right!=null){
			node = root.left;
			root.left = root.right;
			root.right = node;
		}
		if(root.left!=null)
			Mirror(root.left);
		if(root.right!=null)
			Mirror(root.right);
	}
	/**
	 * 二叉树是不是对称
	 * @param pRoot
	 * @return
	 */
    public boolean isSymmetrical(TreeNode pRoot)
    {
    	if(pRoot == null) return true;
        return f(pRoot.left,pRoot.right);
    }
    public boolean f(TreeNode left,TreeNode right) {
    	if(left ==null && right == null) return true;
    	if(left !=null && right !=null)
    		return left.val == right.val && f(left.left, right.right) && f(left.right, right.left);
    	return false;
    }
	
	// 构建二叉树 递归 (误) 翻转后有错误
	public void creatTree(TreeNode node,int[] data, int index) {
		if (index > data.length || data[index] == '#')
			return ;
		node.val = data[index];
		creatTree(node.left,data, 2 * index + 1);
		creatTree(node.right,data, 2 * index + 2);
	}

	// 二叉树深度
	public int depth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int l = depth(node.left);
		int r = depth(node.right);
		if (l > r) {
			return l + 1;
		} else {
			return r + 1;
		}
	}

	// 层序遍历 队列 广度优先搜索 队列
	public void LevelOrderTraverse(TreeNode root) {
		// ArrayList<Integer> arraylist = new ArrayList();
		// arraylist.add(cur.val);
		if (root == null)
			return;
		LinkedList<TreeNode> queue = new LinkedList<>();
		TreeNode cur = null;
		queue.offer(root);
		while (!queue.isEmpty()) {
			cur = queue.poll(); // 出队 树的当前结点为出队的结点
			System.out.println(cur.val); // 输出课换成添加到动态数组
			if (cur.left != null) {
				queue.offer(cur.left);
			}
			if (cur.right != null) {
				queue.offer(cur.right);
			}
		}
	}

	// 先序遍历
	public void PreOrderTraverse(TreeNode node) {
		if (node != null) {
			// res.add(node.val); //改变这行的位置 实现 中序 后序
			System.out.println(node.val);
			PreOrderTraverse(node.left);
			PreOrderTraverse(node.right);
		}
	}

	/*
	 * 2 题目描述 3 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 4
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。 5
	 */

	/*
	 * 8 思路： 9 先序遍历的第一个元素为根节点，在中序遍历中找到这个根节点，从而可以将中序遍历分为左右两个部分， 10
	 * 左边部分为左子树的中序遍历，右边部分为右子树的中序遍历，进而也可以将先序遍历除第一个元素以外的剩余部分分为两个部分， 11
	 * 第一个部分为左子树的先序遍历，第二个部分为右子树的先序遍历。 12
	 * 由上述分析结果，可以递归调用构建函数，根据左子树、右子树的先序、中序遍历重建左、右子树。 13
	 */
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre.length == 0 || in.length == 0)
			return null; // 长度为0返回空
		TreeNode res = new TreeNode(pre[0]);

		for (int i = 0; i < in.length; i++) {
			if (pre[0] == in[i]) { // Arrays.copyOfRange(original, from,
									// to)将置顶数组的范围赋值到新数组
				res.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
				res.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
						Arrays.copyOfRange(in, i + 1, in.length));
			}
		}
		return res;
	}


}
