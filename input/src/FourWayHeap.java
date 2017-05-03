
public class FourWayHeap implements Minheap {

	int offset,sizecount;
	Element[] heap;
	
	public FourWayHeap() {
		offset=3;
		heap=new Element[1000003];
		sizecount=offset;
	}
	
	@Override
	public void buildHeap(int[] freqArray) {
		if(freqArray==null) return;
		
		for(int i=0;i<freqArray.length;i++){
			if(freqArray[i]>0){
				heap[sizecount++]=new Element(i,freqArray[i]);
			}
		}
		if(sizecount>offset)
			sizecount--;
		
		for(int i=getParent(sizecount);i>=offset;i--){
			minHeapify(i);
		}
	}

	@Override
	public int getMinChild(int index) {
		int smallest=index;
		for(int i=1;i<=4;i++){
			if(ithChild(i,index)<=sizecount){
					if(heap[ithChild(i,index)].freq<heap[smallest].freq){
						smallest=ithChild(i, index);
					}
					
			}
		}
		return smallest;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public void setRoot(Element minNode) {
		heap[offset]=minNode;
		minHeapify(offset);
	}

	@Override
	public int getSizeCount() {
		// TODO Auto-generated method stub
		return sizecount;
	}

	@Override
	public Element getRoot() {
		if(sizecount>=offset){
			return heap[offset];
		}
		return null;
	}
	
	@Override
	public void minHeapify(int index) {
		int minChild=getMinChild(index);
		if(minChild==index) return;
		else{
			Element temp=heap[index];
			heap[index]=heap[minChild];
			heap[minChild]=temp;
			minHeapify(minChild);
		}
	}
	
	@Override
	public Element extractMin() {
		if(sizecount<offset) return null;
		Element temp=heap[offset];
		heap[offset]=heap[sizecount];
		heap[sizecount]=null;
		sizecount--;
		minHeapify(offset);
		return temp;
	}
	
	@Override
	public void decreaseKey(int index, int newFreq) {
		if(heap[index].freq<newFreq) return;
		int parentofi=getParent(index);
		heap[index].freq=newFreq;
		Element temp;
		while(parentofi >= offset && heap[parentofi].freq>heap[index].freq){
			temp=heap[parentofi];
			heap[parentofi]=heap[index];
			heap[index]=temp;
			index=parentofi;
			parentofi=getParent(index);
		}
	}

	@Override
	public Element getMin() {
		if(sizecount>=offset){
			return heap[offset];
		}
		return null;
	}

	@Override
	public int getParent(int i) {
		if(i>offset)
			return ((i-offset-1)/4)+offset;
		return -1;
	}

	@Override
	public int ithChild(int i, int j) {
		return ((j-offset)*4)+i+offset;
	}

}
