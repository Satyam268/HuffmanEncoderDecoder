
public class Element {
	int data;
	int freq;
	Element left,right,prev,next,child;
	
	Element(int data, int freq){
		this.data=data;
		this.freq=freq;
		left=null;
		right=null;
		prev=null;
		next=null;
		child=null;		
	}
}
