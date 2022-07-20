package utils;

import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class StringCompressor {
	public static String compress(String inputString) {
		if(true) {
			String output = inputString.replaceAll("  ", "");
			System.out.println(inputString.length() + " -> " + output.length());
			return output;
		}
		byte[] input = inputString.getBytes(StandardCharsets.US_ASCII);

		// Compress the bytes
		byte[] output = new byte[input.length];
		Deflater compressor = new Deflater();
		compressor.setInput(input);
		compressor.finish();
		compressor.deflate(output);
		System.out.println(inputString.length() + " compressed to " + (new String(output)).length());
		return new String(output);
	}
	public String decode(String compressString){
		try{

			byte[] input = compressString.getBytes(StandardCharsets.UTF_8);
			int compressedDataLength = input.length;

			// Decompress the bytes
			Inflater decompressor = new Inflater();
			decompressor.setInput(input, 0, compressedDataLength);
			byte[] result = new byte[10 * compressString.length()];
			int resultLength = decompressor.inflate(result);
			decompressor.end();
			return new String(result);
			// Decode the bytes into a String
		} catch (java.util.zip.DataFormatException ex) {
			// handle
			return null;
		}
	}
}
