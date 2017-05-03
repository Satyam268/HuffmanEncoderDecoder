import java.util.LinkedList;

public class PairingHeap implements Minheap {
	
	Element root=null;
	
	private Element meld(Element node1, Element node2){
		if(node1==null) return node2;
		if(node2==null) return node1;
		
		if(node1.freq>node2.freq){	
			Element temp=node2.child;
			node2.child=node1;
			node1.prev=node2;
			node1.next=temp;
			if(temp!=null)
				temp.prev=node1;
			node2.prev=null;
			node2.next=null;
			return node2;
		}
		else{
			Element temp=node1.child;
			node1.child=node2;
			node2.prev=node1;
			node2.next=temp;
			if(temp!=null) 
				temp.prev=node2;
			node1.prev=null;
			node1.next=null;
			return	node1;
		}

	}

	private Element insert(Element node){
		return meld(root,node);
	}

	@Override
	public void buildHeap(int[] freqArray) {
		for(int i=0;i<freqArray.length;i++){
			if(freqArray[i]>0){
				root = insert(new Element(i,freqArray[i]));
			}
		}
	}

	@Override
	public Element extractMin() {
		LinkedList<Element> queue=new LinkedList<>();

		if(root==null){
			return null;
		}
		Element child=root.child;
		Element min=new Element(root.data,root.freq);
		min.left=root.left;
		min.right=root.right;
		if(child!=null){
			child.prev=null;
			Element temp=null;
			while(child!=null){
				temp=child.next;
				child.next=null;
				child.prev=null;
				queue.add(child);
				child=temp;
			}
			
			while(queue.size()>1){	
				queue.offer(meld(queue.poll(),queue.poll()));
			}
			
			root=queue.poll();
		}
		else{
			root=null;
		}
		return min;
	}

	@Override
	public Element getMin() {
		return extractMin();
	}
	
	@Override
	public Element getRoot() {
		return root;
	}

	@Override
	public void minHeapify(int index) {
	}

	@Override
	public void decreaseKey(int index, int newFreq) {
	}

	@Override
	public int getMinChild(int index) {
		return 0;
	}
	
	@Override
	public int getParent(int i) {
		return 0;
	}
	
	@Override
	public int ithChild(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRoot(Element minNode) {
		root=insert(minNode);
	}

	@Override
	public int getSizeCount() {
		// TODO Auto-generated method stub
		if(root!=null && root.child!=null){
			return 2;
		}
		return 0;
	}

}
