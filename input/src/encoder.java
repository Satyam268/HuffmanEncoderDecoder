import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.Iterator;

public class encoder {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		long start1= System.currentTimeMillis();
		int[] freqTable=new int[1000003];

		File foutcode=new File("./code_table.txt");
		File file=new File("./sample_input_large.txt");
		OutputStream outStream = null;

		BufferedWriter bW=null;
		BufferedReader br=null;
		BufferedReader brs=null;
		Minheap tree=null;
		try {
			br = new BufferedReader(new FileReader(file));
			readDataFromFile(br, freqTable);
			System.out.println("TimePOst reading file " +(System.currentTimeMillis()-start));
			//build heap first
			int heap=1;

			// generate code table and store it in hashmap and then write to file
			int sum=0;
			start =System.currentTimeMillis();
			for(int i=0;i<1;i++){
				if(heap == 2)
					tree=new BinaryHeap();
				else if(heap == 3)
					tree=new PairingHeap();
				else
					tree=new FourWayHeap();
			}

			tree.buildHeap(freqTable);	
			sum+=(System.currentTimeMillis()-start);  

			System.out.println("Heap build time:" +sum);
			
			HuffmanTree hmtc=new HuffmanTree();
			hmtc.createHuffmanTree(tree);
			
			EncodingDecodingMap cvg=new EncodingDecodingMap(tree);
			bW=new BufferedWriter(new FileWriter(foutcode));
			writeCodeTableOnFile(bW,cvg);
			bW.close();

			//encode the data in a binary file
			start =System.currentTimeMillis();
			outStream = new BufferedOutputStream(new FileOutputStream("./encoded.bin"));
			brs = new BufferedReader(new FileReader(file));
			encodeInputDataToBinary(brs,cvg,outStream);
			System.out.println("Time after writing map to file " +(System.currentTimeMillis()-start));
			System.out.println("Total Time: " +(System.currentTimeMillis()-start1));
		}
		catch(Exception exp){
			System.out.println("Exception :"+exp);
		}
		finally{
			if (br != null) {
				br.close();
			}
			if(outStream!=null){
				outStream.close();
			}
		}
	}

	public static void writeCodeTableOnFile(BufferedWriter bW, EncodingDecodingMap cvg) throws IOException{
		Iterator<Integer> it=cvg.dataCode.keySet().iterator();
		while(it.hasNext()){
			Integer temp=it.next();
			bW.write(temp+ " "+cvg.dataCode.get(temp));
			bW.newLine();
		}
	}
	
	public static void readDataFromFile(BufferedReader br, int[] freqTable) throws IOException{
		String line; 
		while ((line=br.readLine())!=null) {
		        freqTable[(Integer.parseInt(line))]++;
		   }
	}
	
	public static void encodeInputDataToBinary(BufferedReader br, EncodingDecodingMap cvg, OutputStream bW) throws IOException{
		StringBuilder sb=new StringBuilder(); 
		String line;
		BitSet bs=new BitSet();
		int length=0;
		//System.out.println(br.hasNextLine());
		while((line=br.readLine())!=null){
			
			sb.append(cvg.dataCode.get(Integer.parseInt(line)));
			for(int i=0;i<sb.length();i++){
				if(sb.charAt(i)=='1'){
					bs.set(length+i);
				}
			}
			length+=sb.length();
			sb.setLength(0);
		}	
		bW.write(bs.toByteArray());	
	}
}
