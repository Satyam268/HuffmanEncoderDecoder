
public interface Minheap {
	
	public void buildHeap(int[] freqArray);
	
	public Element extractMin();
	
	public void minHeapify(int index);
	
	public void decreaseKey(int index, int newFreq);
	
	public Element getMin();
	
	public int getParent(int i);
	
	public int ithChild(int i,int j);
	
	public int getMinChild(int index);
	
	public int getOffset();
	
	public void setRoot(Element minNode);
	
	public int getSizeCount();
	
	public Element getRoot();
}
