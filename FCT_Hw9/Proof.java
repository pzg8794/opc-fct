import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;


public class Proof {
	
    private enum Command { Encode, Decode };
    private static void usage() {
        System.err.println("Usage: java VigenereCipher encode|decode <keyword> [<input-file> [<output-file>]]");
        System.exit(1);
    }
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if ( args.length < 2 || args.length > 4 ) {
            usage();
        }
        Command cmd = null;
        if ( args[0].equals("encode") ) {
            cmd = Command.Encode;
        } else if ( args[0].equals("decode") ) {
            cmd = Command.Decode;
        } else {
            usage();
        }
        String keyword = args[1];
        Reader rd = null;
        Writer wr = null;
        try {
            if ( args.length >= 3 ) {
                rd = new FileReader(new File(args[2]));
            } else {
                rd = new InputStreamReader(System.in);
            }
            if ( args.length >= 4 ) {
                wr = new FileWriter(new File(args[3]));
            } else {
                wr = new OutputStreamWriter(System.out);
            }
            switch (cmd) {
            case Encode:
                wr = new VigenereCipherWriter(wr, keyword);
                break;
            case Decode:
                rd = new VigenereCipherReader(rd, keyword);
                break;
            }
         
            boolean eof = false;
            do {
                // 
                    int c = rd.read();
                    if (c == -1) {
                        eof = true;
                    } else {
                        wr.write(c);
                    }
                   
                } while (! eof);
            
            
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (rd != null) { 
                    rd.close(); 
                }
            } catch (IOException e) {
                System.err.println(e);
            }
            try {
                if (wr != null) { 
                    wr.close(); 
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
	}

}
