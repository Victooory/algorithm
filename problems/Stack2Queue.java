package algorithm.problems;

import java.util.Stack;
/**
 * 用两个栈实现队列
 * @author dong
 *
 */
public class Stack2Queue {				//还有一种方法：每次都判断S1或S2栈是否为空，为空则从另一个导入后操作
	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
    	int out=0;
    	int temp;
    	for(int i=0;;i++){
    		if(!stack1.empty()){
    			stack2.push(stack1.pop());
    		}else{
    			out = stack2.pop();
    			for(int j=0;;j++){
    				if(!stack2.empty()){
    					stack1.push(stack2.pop());
    				}else{
    					break;
    				}
    			}
    			break;
    		}
    	}
    	return out;
    }
}
