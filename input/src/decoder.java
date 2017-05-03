

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;

public class decoder {
	
	public static void main(String args[]) throws IOException{
		BufferedWriter bw=null;
		BufferedReader br=null;

		File codeTable = new File("codetable.txt");
		File encodedBinaryFile = new File("encoded.bin");
		File decodedFile = new File("sample_output_small.txt");

		FileInputStream fileinputStream=null;

		try{
			long start=System.currentTimeMillis();
			br=new BufferedReader(new FileReader(codeTable));
			DecoderTree decodeTree=new DecoderTree();
			
			codeTableToDecoderTree(br,decodeTree);
			
			bw=new BufferedWriter(new FileWriter(decodedFile));
			fileinputStream=new FileInputStream(encodedBinaryFile);
			long size=encodedBinaryFile.length();
			
			writeDecodeDataToFile(bw,fileinputStream,decodeTree,(int)size);
			System.out.println("Time taken: "+(System.currentTimeMillis()-start));
			System.out.println("Data Decoded");
		}
		finally{
			if (fileinputStream != null) {
				fileinputStream.close();
			}
			if(bw!=null){
				bw.close();
			}
			if(br!=null){
				br.close();
			}
		}
	}

	//reads code_table.txt / reads the encoded msg and fills into tree. 
	public static void codeTableToDecoderTree(BufferedReader br, DecoderTree decodeTree) throws IOException{
		String line=null;
		String[] dataValue=null;
		while ((line=br.readLine())!=null) {
			dataValue=line.split(" ");
			decodeTree.insert(dataValue[1],decodeTree.getRoot(),Integer.parseInt(dataValue[0]));
		}
	}
	
	public static void writeDecodeDataToFile(BufferedWriter bw, FileInputStream fis, DecoderTree decodeTree, int size) throws IOException{
		DecoderNode dNode=decodeTree.getRoot();		
		byte[] b=new byte[size];
		fis.read(b);
		BitSet bitset=BitSet.valueOf(b);
		bitset.set((b.length)*8);
		for(int i=0;i<bitset.length()-1;i++){
			if(!bitset.get(i)){
				dNode=dNode.left;
			}
			else{
				dNode=dNode.right;
			}
			if(dNode.isLeaf){	
				bw.write(""+dNode.data);
				bw.newLine();
				dNode=decodeTree.getRoot();
			}
		}
	}
}
