import java.util.HashMap;

public class EncodingDecodingMap {
		
	HuffmanTree hmtc;
	Minheap tree;
	HashMap<Integer, String> dataCode;
	
	public EncodingDecodingMap(Minheap tree) {
			// TODO Auto-generated constructor stub
			dataCode =new HashMap<>();
			generateDecodeEncodeMap(tree.getRoot(),new StringBuilder());
		}
		
	public void generateDecodeEncodeMap(Element root, StringBuilder sb){
		if(root!=null && root.left==null && root.right==null){
			dataCode.put(root.data, sb.toString());
			return;
		}
		if(root.left!=null){
			sb.append('0');
			generateDecodeEncodeMap(root.left, sb);
			sb.deleteCharAt(sb.length()-1);
		}
		if(root.right!=null){
			sb.append('1');
			generateDecodeEncodeMap(root.right, sb);
			sb.deleteCharAt(sb.length()-1);
		}
	}
			
}
