import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class Trivium {
	
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
	public static void inicializar(int[] vet) {
		for(int i=0; i<vet.length; i++) {
			vet[i] = 0;
		}
	}
	public static void shiftRight(int[] vet) {
		for(int i=vet.length-1; i>0; i--) vet[i] = vet[i-1];
	}
	public static int[] run(String seed, int tamanho) {
		int[] output = new int[tamanho];
		int atualPos = 0;
		output[atualPos] = 1;
		atualPos++;
		
		int[] A = new int[93];
		int[] B = new int[84];
		int[] C = new int[111];
		
		inicializar(A);
		inicializar(B);
		inicializar(C);
		
		for(int i=0; i<seed.length(); i++) {
			output[i] = Character.getNumericValue(seed.charAt(i));
			A[i] = Character.getNumericValue(seed.charAt(i));
		}
		
		int bitA, bitB, bitC;
		
		for(int i=0; i<2000; i++) {
			bitA = ((A[A.length-3] & A[A.length-2]) + A[A.length-1] + output[atualPos-1])%2;
			bitB = ((B[B.length-3] & B[B.length-2]) + B[B.length-1] + output[atualPos-1])%2;
			bitC = ((C[C.length-3] & C[C.length-2]) + C[C.length-1] + output[atualPos-1])%2;
			
			shiftRight(A);
			shiftRight(B);
			shiftRight(C);
			
			A[0] = bitC;
			B[0] = bitA;
			C[0] = bitB;
		}
		for(int i=0; i<tamanho-1; i++) {
			bitA = ((A[A.length-3] & A[A.length-2]) + A[A.length-1] + output[atualPos-1])%2;
			bitB = ((B[B.length-3] & B[B.length-2]) + B[B.length-1] + output[atualPos-1])%2;
			bitC = ((C[C.length-3] & C[C.length-2]) + C[C.length-1] + output[atualPos-1])%2;
			
			shiftRight(A);
			shiftRight(B);
			shiftRight(C);
			
			A[0] = bitC;
			B[0] = bitA;
			C[0] = bitB;
			
			output[atualPos] = (bitA + A[3] + bitB + B[3] + bitC + C[3])%2;
			atualPos++;
		}
		return output;
	}
	public static void main(String[] args) throws Exception {
		
		BufferedWriter escritor = novoEscritor("C:\\MeusProgramas\\1-Seguranca\\Ex2\\trivium3.txt", false);
		
		//String seed = "111000";
		//String seed = "10101010";
		String seed = "110100100101";
		
		int[] bits = run(seed, 1000000);
		
		for(int i=0; i<bits.length; i++) {
			escritor.write(String.valueOf(bits[i]));
		}
		escritor.close();
	}
}
