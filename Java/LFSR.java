import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class LFSR {
	
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
	public static int[] run(String seed, int tamanho) {
		int[] output = new int[tamanho];
		for(int i=0; i<seed.length(); i++) output[i] = Character.getNumericValue(seed.charAt(i));
		for(int i=seed.length(); i<tamanho; i++) {
			output[i] = (output[i-1] + output[i-2] + output[i-4] + output[i-5] + output[i-8])%2;
		}
		return output;
	}
	public static void main(String[] args) throws Exception {
		
		BufferedWriter escritor = novoEscritor("C:\\MeusProgramas\\1-Seguranca\\Ex2\\lfsr3.txt", false);
		
		//String seed = "1111100000";
		//String seed = "1010101010";
		String seed = "1010010110";
		
		int[] bits = run(seed, 1000000);
		
		for(int i=0; i<bits.length; i++) {
			escritor.write(String.valueOf(bits[i]));
		}
		escritor.close();
	}
}
