
public class BinaryHeap implements Minheap{

	int offset,size;
	Element[] heap;
	
	public BinaryHeap() {
		offset=0;
		heap=new Element[1000001];
		size=offset;
	}
	
	@Override
	public void buildHeap(int[] freqArray) {
		if(freqArray==null) return;
		for(int i=0;i<freqArray.length;i++){
			if(freqArray[i]>0){
				heap[size++]=new Element(i,freqArray[i]);
			}
		}
		if(size>offset)
			size--;
		
		for(int i=getParent(size);i>=offset;i--){
			minHeapify(i);
		}
		
	}

	@Override
	public Element extractMin() {
		if(size<offset) 
			return null;
		
		Element temp=heap[offset];
		heap[offset]=heap[size];
		heap[size]=null;
		size--;
		
		minHeapify(offset);
		return temp;
	}

	@Override
	public void minHeapify(int index) {
		int minChild=getMinChild(index);
		if(minChild==index) 
			return;
		else{
			Element temp=heap[index];
			heap[index]=heap[minChild];
			heap[minChild]=temp;
			minHeapify(minChild);
		}
	}

	@Override
	public void decreaseKey(int index, int updatedFreq) {
		if(heap[index].freq<updatedFreq) return;
		
		int parentofi=getParent(index);
		
		heap[index].freq=updatedFreq;
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
		if(size>=offset){
			return heap[offset];
		}
		return null;
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
		return size;
	}
	
	@Override
	public Element getRoot() {
		if(size>=offset){
			return heap[offset];
		}
		return null;
	}

	@Override
	public int getParent(int i) {
		return ((i+1)/2)-1;
	}

	@Override
	public int ithChild(int i, int j) {
		return 2*(j+1)+i-1;
	}

	@Override
	public int getMinChild(int index) {
		int smallest=index;
		for(int i=0;i<2;i++){
			if(ithChild(i,index)<=size){
					if(heap[ithChild(i,index)].freq<heap[smallest].freq){
						smallest=ithChild(i, index);
					}
			}
		}
		return smallest;
	}

}
