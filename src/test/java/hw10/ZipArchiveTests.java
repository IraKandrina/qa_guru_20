package hw10;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveTests {

    String zipFilePath = "src/test/resources/archive.zip";

    @Test
    public void pdfTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            ZipEntry pdfEntry = zipFile.getEntry("GlassesStudies.pdf");
            try (InputStream stream = zipFile.getInputStream(pdfEntry)) {
                PDF pdf = new PDF(stream);
                assertEquals("Martin Orendac", pdf.author);
            }
        }
    }

    @Test
    public void xlsxTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            ZipEntry xlsxEntry = zipFile.getEntry("ProjectTimelineTemplate.xlsx");
            try (InputStream stream = zipFile.getInputStream(xlsxEntry)) {
                XLS xlsx = new XLS(stream);
                Assertions.assertEquals("In Progress", xlsx.excel
                        .getSheetAt(0)
                        .getRow(4)
                        .getCell(4)
                        .getStringCellValue());
            }
        }
    }

    @Test
    public void csvTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            ZipEntry csvEntry = zipFile.getEntry("CountryList.csv");
            try (InputStream stream = zipFile.getInputStream(csvEntry)) {
                CSVReader csvReader = new CSVReader(new InputStreamReader(stream));
                List<String[]> content = csvReader.readAll();
                assertEquals(4, content.size());

                final String[] firstRow = content.get(0);
                final String[] secondRow = content.get(1);
                final String[] thirdRow = content.get(2);
                final String[] fourthRow = content.get(3);

                Assertions.assertArrayEquals(new String[] {"Abkhazia","Sukhumi"}, firstRow);
                Assertions.assertArrayEquals(new String[] {"Afghanistan","Kabul"}, secondRow);
                Assertions.assertArrayEquals(new String[] {"Akrotiri and Dhekelia","Episkopi Cantonment"}, thirdRow);
                Assertions.assertArrayEquals(new String[] {"Albania","Tirana"}, fourthRow);
            }
        }
    }

}
