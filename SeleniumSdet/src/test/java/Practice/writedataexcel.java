package Practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class writedataexcel {

	public static void main(String[] args) throws IOException {
	FileInputStream fis = new FileInputStream("./data/Testdataexcel.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet("Sheet2");
	Row r=sh.createRow(1);
    Cell cell= r.createCell(5);
	cell.setCellValue("pass");
	
	FileOutputStream fos=new FileOutputStream("./data/Testdataexcel.xlsx");
			wb.write(fos);
	        wb.close();
	
	}

}
