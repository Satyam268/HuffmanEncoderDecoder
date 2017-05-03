
public class HuffmanTree {
		
		public Minheap createHuffmanTree(Minheap tree){
			while(tree.getSizeCount()>tree.getOffset()){
				Element node1=tree.extractMin();
				Element node2=tree.getMin();
				Element node=new Element(-1,node1.freq+node2.freq);
				node.left=node1;
				node.right=node2;
				tree.setRoot(node);
			}
			return tree;
		}
}
