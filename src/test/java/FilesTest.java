import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;
import utils.ZipFileDecryptor;

import java.io.File;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesTest {

    ZipFileDecryptor zipFileDecryptor = new ZipFileDecryptor();

    @Test
    void readTxtFileTest() throws Exception{

    }

    @Test
    void readPdfFileTest() throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("File.pdf")) {
            PDF parsed = new PDF(stream);
            assertThat(parsed.author).contains("Edem Ibragimov");
        }
    }

    @Test
    void readExcelFileTest() throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("Excel.xlsx")) {
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue())
                    .isEqualTo("Тест охранника любого разряда");
        }
    }


    @Test
    void readZipFileTest() throws Exception {
        String zipPath = "./src/test/resources/resources.zip";
        String unzipPath = "./src/test/resources/unzipFiles";
        String pass = "1234";

        zipFileDecryptor.zipDecryptor(zipPath, pass, unzipPath);

        File file = new File("./src/test/resources/unzipFiles/File.pdf");

        assertTrue(file.exists());
    }
}