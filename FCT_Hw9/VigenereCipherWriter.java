import java.io.IOException;
import java.io.Writer;


/**
 * @author Wander
 *
 *	Class implement Vigenere cipher encoding
 */
public class VigenereCipherWriter extends Writer{
	
	private Writer wr;
	private String keyword;
	private int index_actual_character;
	private int length_keywork;
	public static int A_CODE = (int)'A';
	public static int Z_CODE = (int)'Z';
	public static int a_CODE = (int)'a';
	public static int z_CODE = (int)'z';
	public static int range = z_CODE - a_CODE + 1; 
	
	
	public VigenereCipherWriter(Writer wr, String keyword) throws IllegalArgumentException
	{
		this.wr = wr;
		this.keyword = keyword;
		this.index_actual_character = 0;
		this.length_keywork = this.keyword.length();
	} 
	public boolean isAnLetter(char letter)
	{
		int test = (int)letter;
		if((test>=a_CODE && test <=z_CODE) || (test>=A_CODE && test <=Z_CODE))
			return true;
		return false;
	}
	public void close() throws IOException
	{
		this.flush();
		this.wr.close();
	}
	public void flush() throws IOException
	{
		this.wr.flush();
	}
	public void write(char[] cbuf, int off, int len) throws IOException 
	{						
			try {
				if(cbuf.length < off+len)
					throw new Exception("the parameters seem kinda wrong");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		char []cipherText = cbuf.clone();		
		for(int i=off;i<off+len;i++)
		{			
			if(isAnLetter(cbuf[i]))
			{			
				if(Character.isLowerCase(cbuf[i]))
				{
					int encoded;
					int cbuf_actual = cbuf[i];
					//int keyChar = (int)keyword.charAt(this.index_actual_character%length_keywork);
					int keyChar = (int)Character.toLowerCase( keyword.charAt(i%len) );
												
					encoded = ((cbuf_actual - a_CODE + keyChar - a_CODE) % range) + a_CODE;
					cipherText[i]=(char)encoded;
					//this.index_actual_character ++;
				}
				else
				{
					int encoded;
					int cbuf_actual = cbuf[i];
					//int keyChar = (int)keyword.charAt(this.index_actual_character%length_keywork);
					int keyChar = (int)Character.toUpperCase( keyword.charAt(i%len) );
												
					encoded = ((cbuf_actual - A_CODE + keyChar - A_CODE) % range) + A_CODE;
					cipherText[i]=(char)encoded;
					//this.index_actual_character ++;
				}
			}
			else 
				cipherText[i] = cbuf[i];
		}
		wr.write(cipherText, off, len);
	}
	public void write(int ci) throws IOException
	{
		if(isAnLetter((char)ci))
		{
			if(Character.isLowerCase((char)ci))
			{
				int encoded;
				int keyChar = (int)Character.toLowerCase( keyword.charAt(this.index_actual_character%length_keywork) );			
							
				encoded = ((ci - a_CODE + keyChar - a_CODE) % range) + a_CODE;									
				
				wr.write(encoded);
				this.index_actual_character ++;
			}
			else
			{
				int encoded;
				int keyChar = (int)Character.toUpperCase( keyword.charAt(this.index_actual_character%length_keywork) );			
							
				encoded = ((ci - A_CODE + keyChar - A_CODE) % range) + A_CODE;									
				
				wr.write(encoded);
				this.index_actual_character ++;
			}
		}
		else
			wr.write(ci);
	}	

}
