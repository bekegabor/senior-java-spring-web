package hu.ponte.hr;

import hu.ponte.hr.services.FileService;
import hu.ponte.hr.services.SignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author zoltan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SignTest {

	private static final String CAT_JPG_FILE_NAME = "cat.jpg";
	private static final String CAT_JPG_RESOURCE_PATH = "/images/cat.jpg";

	private static final String ENHANCED_BUZZ_JPG_FILE_NAME = "enhanced-buzz.jpg";
	private static final String ENHANCED_BUZZ_JPG_RESOURCE_PATH  = "/images/enhanced-buzz.jpg";

	private static final String RND_JPG_FILE_NAME = "rnd.jpg";
	private static final String RND_JPG_RESOURCE_PATH  = "/images/rnd.jpg";

	@Autowired
	private SignService signService;

	@Autowired
	private FileService fileService;

	Map<String, String> files = new LinkedHashMap<String, String>() {
		{
			put(CAT_JPG_FILE_NAME, "XYZ+wXKNd3Hpnjxy4vIbBQVD7q7i0t0r9tzpmf1KmyZAEUvpfV8AKQlL7us66rvd6eBzFlSaq5HGVZX2DYTxX1C5fJlh3T3QkVn2zKOfPHDWWItdXkrccCHVR5HFrpGuLGk7j7XKORIIM+DwZKqymHYzehRvDpqCGgZ2L1Q6C6wjuV4drdOTHps63XW6RHNsU18wHydqetJT6ovh0a8Zul9yvAyZeE4HW7cPOkFCgll5EZYZz2iH5Sw1NBNhDNwN2KOxrM4BXNUkz9TMeekjqdOyyWvCqVmr5EgssJe7FAwcYEzznZV96LDkiYQdnBTO8jjN25wlnINvPrgx9dN/Xg==");
			put(ENHANCED_BUZZ_JPG_FILE_NAME, "tsLUqkUtzqgeDMuXJMt1iRCgbiVw13FlsBm2LdX2PftvnlWorqxuVcmT0QRKenFMh90kelxXnTuTVOStU8eHRLS3P1qOLH6VYpzCGEJFQ3S2683gCmxq3qc0zr5kZV2VcgKWm+wKeMENyprr8HNZhLPygtmzXeN9u6BpwUO9sKj7ImBvvv/qZ/Tht3hPbm5SrDK4XG7G0LVK9B8zpweXT/lT8pqqpYx4/h7DyE+L5bNHbtkvcu2DojgJ/pNg9OG+vTt/DfK7LFgCjody4SvZhSbLqp98IAaxS9BT6n0Ozjk4rR1l75QP5lbJbpQ9ThAebXQo+Be4QEYV/YXf07WXTQ==");
			put(RND_JPG_FILE_NAME, "lM6498PalvcrnZkw4RI+dWceIoDXuczi/3nckACYa8k+KGjYlwQCi1bqA8h7wgtlP3HFY37cA81ST9I0X7ik86jyAqhhc7twnMUzwE/+y8RC9Xsz/caktmdA/8h+MlPNTjejomiqGDjTGvLxN9gu4qnYniZ5t270ZbLD2XZbuTvUAgna8Cz4MvdGTmE3MNIA5iavI1p+1cAN+O10hKwxoVcdZ2M3f7/m9LYlqEJgMnaKyI/X3m9mW0En/ac9fqfGWrxAhbhQDUB0GVEl7WBF/5ODvpYKujHmBAA0ProIlqA3FjLTLJ0LGHXyDgrgDfIG/EDHVUQSdLWsM107Cg6hQg==");
		}
	};

	@Test
	public void signTest() throws IOException {
		Map<String, String> signedFiles = new LinkedHashMap<>();

		byte[] catBytes = fileService.readFileFromResourceFolder(CAT_JPG_RESOURCE_PATH);
		byte[] enhancedBuzzBytes = fileService.readFileFromResourceFolder(ENHANCED_BUZZ_JPG_RESOURCE_PATH);
		byte[] rndBytes = fileService.readFileFromResourceFolder(RND_JPG_RESOURCE_PATH);

		signedFiles.put(CAT_JPG_FILE_NAME, signService.createBase64SignatureWithSHA256RSA(catBytes));
		signedFiles.put(ENHANCED_BUZZ_JPG_FILE_NAME, signService.createBase64SignatureWithSHA256RSA(enhancedBuzzBytes));
		signedFiles.put(RND_JPG_FILE_NAME, signService.createBase64SignatureWithSHA256RSA(rndBytes));

		assertEquals(files.get(CAT_JPG_FILE_NAME), signedFiles.get(CAT_JPG_FILE_NAME));
		assertEquals(files.get(ENHANCED_BUZZ_JPG_FILE_NAME), signedFiles.get(ENHANCED_BUZZ_JPG_FILE_NAME));
		assertEquals(files.get(RND_JPG_FILE_NAME), signedFiles.get(RND_JPG_FILE_NAME));
	}

}
