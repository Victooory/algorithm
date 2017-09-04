package algorithm.some_implementation;

/**
 * 字典树的实现
 * @author dong
 * 
 */
public class Trie {
	private int SIZE = 26;
	private TrieNode root; // 根节点

	Trie() {
		root = new TrieNode();
	}

	private class TrieNode { // 节点
		private int num; // 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
		private TrieNode[] son; // 所有子节点
		private boolean isEnd; // 是不是最后一个节点
		private char val;

		public TrieNode() {
			num = 1;
			son = new TrieNode[SIZE];
			isEnd = false;
		}
	}

	// 建立字典树
	public void insert(String str) { // 插入一个单词
		if (str == null || str.length() == 0) {
			return;
		}
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			int pos = letters[i] - 'a';
			if (node.son[pos] == null) {
				node.son[pos] = new TrieNode(); // 加入新的子节点
				node.son[pos].val = letters[i]; // 把值赋给子节点val
			} else {
				node.son[pos].num++; // 如果不为空说明已经建好了 给单词书+1
			}
			node = node.son[pos];
		}
		node.isEnd = true; // 确定这个单词的结束
	}

	// 查找一个单词
	public boolean has(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			int pos = letters[i] - 'a';
			if (node.son[pos] != null) {
				node = node.son[pos];
			} else {
				return false;
			}
		}
		return node.isEnd;
	}

	public static void main(String[] args) {		
		Trie tree = new Trie();
		String[] strs = { "banana", "band", "bee", "absolute", "acm", };	//只能小写字母
		String[] prefix = { "ba", "b", "band", "abc", };
		for (String str : strs) {
			tree.insert(str);
		}
		System.out.println(tree.has("band"));
	}
	
	//复制的
	//计算单词前缀的数量
    public int countPrefix(String prefix)
    {
        if(prefix==null||prefix.length()==0)
        {
            return-1;
        }
        TrieNode node=root;
        char[]letters=prefix.toCharArray();
        for(int i=0,len=prefix.length(); i<len; i++)
        {
            int pos=letters[i]-'a';
            if(node.son[pos]==null)
            {
                return 0;
            }
            else
            {
                node=node.son[pos];
            }
        }
        return node.num;
    }
    //打印指定前缀的单词
    public String hasPrefix(String prefix)
    {
        if (prefix == null || prefix.length() == 0)
        {
            return null;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = prefix.length(); i < len; i++)
        {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null)
            {
                return null;
            }
            else
            {
                node = node.son[pos];
            }
        }
        preTraverse(node, prefix);
        return null;
    }
// 遍历经过此节点的单词.
    public void preTraverse(TrieNode node, String prefix)
    {
        if (!node.isEnd)
        {
        	for (TrieNode child : node.son)
            {
                if (child!=null)
                {
                    preTraverse(child, prefix+child.val);
                }
            }
            return;
        }
        System.out.println(prefix);
    }
}
