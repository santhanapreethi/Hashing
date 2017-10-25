import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

//$Id$

public class GoogleTest {
	
	public static void main(String[] e) throws Exception
	{
		final HashFunction HASH_FUNCTION = Hashing.sha512();
		System.out.println(HASH_FUNCTION.hashUnencodedChars("test"));
	}
}
