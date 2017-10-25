import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressWithDictionary {
    public static void main(String[] args) throws Exception {
        String inputString = "blahblahblahblahblah";
        byte[] input = inputString.getBytes("UTF-8");
        byte[] dict = "blah".getBytes("UTF-8");

        // Compress the bytes
        byte[] output = new byte[100];
        Deflater compresser = new Deflater();
        compresser.setInput(input);
        compresser.setDictionary(dict);
        compresser.finish();
        long start = System.nanoTime();
        int compressedDataLength = compresser.deflate(output);
        System.out.println("compress Time Dict: " + (System.nanoTime() - start));
        System.out.println("compressed String: " + compressedDataLength);
        // Decompress the bytes
        Inflater decompresser = new Inflater();
        decompresser.setInput(output, 0, compressedDataLength);
        byte[] result = new byte[100];
        decompresser.inflate(result);
        decompresser.setDictionary(dict);
        start = System.nanoTime();
        int resultLength = decompresser.inflate(result);
        decompresser.end();
        System.out.println("Decompress Time Dict: " + (System.nanoTime() - start));

        // Decode the bytes into a String
        String outputString = new String(result, 0, resultLength, "UTF-8");
        System.out.println("Decompressed String: " + outputString);
        
        // Compress the bytes
        output = new byte[100];
        compresser = new Deflater();
        compresser.setInput(input);
        compresser.finish();
        start = System.nanoTime();
        compressedDataLength = compresser.deflate(output);
        System.out.println("compress Time Without Dict: " + (System.nanoTime() - start));
        System.out.println("compressed String: " + compressedDataLength);

        // Decompress the bytes
        decompresser = new Inflater();
        decompresser.setInput(output, 0, compressedDataLength);
        result = new byte[100];
        start = System.nanoTime();
        resultLength = decompresser.inflate(result);
        decompresser.end();
        System.out.println("Decompress Time Without Dict: " + (System.nanoTime() - start));

        // Decode the bytes into a String
        outputString = new String(result, 0, resultLength, "UTF-8");
        System.out.println("Decompressed String: " + outputString);
    }
}
