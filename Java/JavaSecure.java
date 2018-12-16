import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.security.SecureRandom;

public class JavaSecure {
	
	public static BufferedWriter novoEscritor(String dir, boolean append) {
		try {
			Charset ASCII = Charset.forName("ASCII");
			
			OutputStream os = new FileOutputStream(dir, append);
			OutputStreamWriter osw = new OutputStreamWriter(os, ASCII);
			BufferedWriter escritor = new BufferedWriter(osw);
			
			return escritor;
		}
		catch(Exception e) {return null;}
	}
	public static void main(String[] args) throws Exception {
		BufferedWriter escritor = novoEscritor("C:\\MeusProgramas\\1-Seguranca\\Ex2\\javaSecure.txt", false);
		
		for(int i=0; i<1000000; i++) {
	    	SecureRandom random = new SecureRandom();
			int atual = random.nextInt(2);
		    escritor.write(String.valueOf(atual));
	    }
		escritor.close();
	}
}
