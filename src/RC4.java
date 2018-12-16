import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class RC4 {
	
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
	public static int[] run(String seed, int tamanho){
		int len = seed.length();
		int[] s = new int[256];
		int[] key = new int[len];
		int[] output = new int[tamanho];
		
		int i=0;
		int j=0;
		int aux;
		
		for(i=0; i<len; i++) key[i] = Character.getNumericValue(seed.charAt(i));
		for(i=0; i<256; i++) s[i] = i;
		
		j = 0;
		for(i=0; i<256; i++) {
			j = (j + s[i] + key[i % len]) % 256;
			aux = s[i];
			s[i] = s[j];
			s[j] = aux;
		}
		i=0;
		j=0;
		for(int k=0; k<tamanho; k++) {
			i = (i + 1) % 256;
			j = (j + s[i]) % 256;
			aux = s[i];
			s[i] = s[j];
			s[j] = aux;
			output[k] = (s[(s[i] + s[j]) % 256]) % 2;
		}
		return output;
	}
	public static void main(String[] args) throws Exception {
		
		BufferedWriter escritor = novoEscritor("C:\\MeusProgramas\\1-Seguranca\\Ex2\\rc4_3.txt", false);
		
		//String seed = "111000";
		//String seed = "101010";
		String seed = "10010110";
		
		int[] bits = run(seed, 1000000);
		
		for(int i=0; i<bits.length; i++) {
			escritor.write(String.valueOf(bits[i]));
		}
		escritor.close();
	}
}
