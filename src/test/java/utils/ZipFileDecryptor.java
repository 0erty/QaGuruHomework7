package utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import java.io.IOException;


public class ZipFileDecryptor {

    public void zipDecryptor(String path, String pass, String unzipPath) throws IOException, ZipException {
        ZipFile zipFile = new ZipFile(path);
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(pass);
        }

        zipFile.extractAll(unzipPath);
    }
}
