package Practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class loginthroughexceldataa {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("./data/Testdataexcel.xlsx");
		
		//open workbook in read mode
		Workbook wb= WorkbookFactory.create(fis);
		
		//get the control of sheet1
		Sheet sh = wb.getSheet("Sheet1");
		
		//get the control of row
		Row row = sh.getRow(1);
		
		//get the control of the cell
		Cell cell = row.getCell(1);
		
		//read the value
		String cellvalue = cell.getStringCellValue();
        System.out.println(cellvalue);
	}

}
