package hu.ponte.hr.services;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class SignService {

    private static final String PATH_TO_PRIVATE_KEY = "/config/keys/key.private";
    private static final String RSA = "RSA";
    private static final String SHA256_WITH_RSA = "SHA256withRSA";

    public String createBase64SignatureWithSHA256RSA(byte[] file){
        byte[] bytes = createSignatureWithSHA256RSA(file);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] createSignatureWithSHA256RSA(byte[] file) {
        byte[] signedBytes;
        try {
            InputStream inputStream = getClass().getResourceAsStream(PATH_TO_PRIVATE_KEY);
            byte[] keyBytes = IOUtils.toByteArray(inputStream);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance(RSA);
            PrivateKey privateKey = kf.generatePrivate(spec);
            Signature privateSignature = Signature.getInstance(SHA256_WITH_RSA);
            privateSignature.initSign(privateKey);
            privateSignature.update(file);
            signedBytes = privateSignature.sign();
            return signedBytes;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
