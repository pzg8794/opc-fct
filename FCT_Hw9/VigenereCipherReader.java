import java.io.IOException;
import java.io.Reader;


public class VigenereCipherReader extends Reader{
	private Reader rd;
	
	private String keyword;
	private int index_actual_character;
	private int length_keyword;
	
	public static int A_CODE = (int)'A';
	public static int Z_CODE = (int)'Z';
	public static int a_CODE = (int)'a';
	public static int z_CODE = (int)'z';
	public static int range = z_CODE - a_CODE + 1; 
	
	public VigenereCipherReader(Reader rd, String keyword) throws IllegalArgumentException
	{
		this.rd = rd;
		this.keyword = keyword;
		this.index_actual_character = 0;
		this.length_keyword = this.keyword.length();
	}
	public boolean isALetter(char letter)
	{
		int test = (int)letter;
		if((test>=a_CODE && test <=z_CODE) || (test>=A_CODE && test <=Z_CODE))
			return true;
		return false;
	}
	public void close() throws IOException
	{
		this.rd.close();				
	}
	public void mark(int readAheadLimit) throws IOException
	{
		this.rd.mark(readAheadLimit);
	}
	public boolean markSupported()
	{
		return this.rd.markSupported();
	}
	public int read() throws IOException
	{
		int readInt = this.rd.read(); 
		if(isALetter((char)readInt))
		{
			if(Character.isLowerCase((char)readInt))
			{
				int decoded;
				int keyChar = (int)Character.toLowerCase( keyword.charAt(this.index_actual_character%length_keyword) );			
							
				decoded = ((readInt - a_CODE) - (keyChar - a_CODE));
				if(decoded>=0)
					decoded = decoded % range + a_CODE;
				else
					decoded = decoded % range + z_CODE +1;								
				this.index_actual_character ++;
				return decoded;
			}
			else
			{
				int decoded;
				int keyChar = (int)Character.toUpperCase( keyword.charAt(this.index_actual_character%length_keyword) );			
							
				decoded = ((readInt - A_CODE) - (keyChar - A_CODE));
				if(decoded >=0)
					decoded = decoded % range + A_CODE;
				else
					decoded = decoded % range + Z_CODE +1;
				this.index_actual_character ++;
				return decoded;
			}
		}
		else
			return readInt;
	}
	/* (non-Javadoc)
	 * @see java.io.Reader#read(char[], int, int) 
	 */
	public int read(char[] cbuf, int off, int len)  throws IOException
	{
		int answer = rd.read(cbuf, off, len); 
		
		char []message = null;
		//int index=0;
		for(int i=0;i<len;i++)
		{									
				if(isALetter(cbuf[i]))
				{
					if(Character.isLowerCase(cbuf[i]))
					{
						int decoded;					
						//int keyChar = (int)keyword.charAt(this.index_actual_character%length_keywork);					
						int keyChar = (int)Character.toLowerCase( keyword.charAt(i%length_keyword) );			
						
						decoded = Math.abs((((((int)cbuf[i]) - a_CODE) - (keyChar - a_CODE)) % range)) + a_CODE;																	
						//this.index_actual_character ++;										
						message[i]=(char)decoded;
						//this.index_actual_character ++;
					}
					else
					{
						int decoded;					
						//int keyChar = (int)keyword.charAt(this.index_actual_character%length_keywork);					
						int keyChar = (int)Character.toUpperCase( keyword.charAt(i%length_keyword) );						
						
						decoded = Math.abs((((((int)cbuf[i]) - A_CODE) - (keyChar - A_CODE)) % range)) + A_CODE;																	
						//this.index_actual_character ++;										
						message[i]=(char)decoded;
						//this.index_actual_character ++;
					}
				}
				else 
					//message[i] = cbuf[i];					
					message[i] = cbuf[i];
		}
		return answer;
	}
	public boolean ready() throws IOException
	{
		return this.rd.ready();
	}
	public void reset() throws IOException
	{
		this.rd.reset();		
	}
	public long skip(long n) throws IOException
	{
		return this.rd.skip(n);		
	}
	

}
