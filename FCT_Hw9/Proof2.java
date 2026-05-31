import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;


public class Proof2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Reader rd = null;
        Writer wr = null;
        rd = new FileReader(new File("reader.txt"));    
        wr = new FileWriter(new File("writer.txt"));
        char[] cbuf;
        char[] cbuf2;
        String a = "mamaMama";
        String b = "vainaloca";
        cbuf = a.toCharArray();
        cbuf2 = b.toCharArray();
        /*
        cbuf[0]='A';        
        cbuf[1]='B';        
        cbuf[2]='V';
        cbuf[3]='A';        
        cbuf[4]='B';        
        cbuf[5]='V';        
        cbuf[6]='A';        
        cbuf[7]='B';        
        cbuf[8]='V';
        */
        
			
		System.out.println(rd.read(cbuf, 3, 5));
		rd.close();
		wr.write(cbuf2, 2, 4);		
		wr.close();
		for(int i =0; i< cbuf.length;i++)
		{
			System.out.println(cbuf[i]);
		}
	}

}
