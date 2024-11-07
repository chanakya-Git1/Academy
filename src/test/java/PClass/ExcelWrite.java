package PClass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
public class ExcelWrite {


@Test
public static void writedata() throws IOException {
	 FileOutputStream fo = new FileOutputStream("D:\\BDD\\userdata.xlsx");
	 XSSFWorkbook wb = new XSSFWorkbook();
	 XSSFSheet  sheet=wb.createSheet("userData");
	 ArrayList<Object[]> data = new ArrayList<Object[]>();
     data.add(new String[] { "Name", "Id", "Salary" });
     data.add(new Object[] { "Jim", "001A", 10000 });
     data.add(new Object[] { "Jack", "1001B", 40000 });
     data.add(new Object[] { "Tim", "2001C", 20000 });
     data.add(new Object[] { "Gina", "1004S", 30000 });
     int rownum=0;
     for (Object[] employeeDetails : data) { 
       XSSFRow row = sheet.createRow(rownum++);
       int cellnum = 0;
       		for (Object obj : employeeDetails) {
       				XSSFCell cell = row.createCell(cellnum++);
       					if (obj instanceof String)
       						cell.setCellValue((String) obj);
       					else if (obj instanceof Double)
       						cell.setCellValue((Double) obj);
       					else if (obj instanceof Integer)
       						cell.setCellValue((Integer) obj);
     }}
     
     wb.write(fo);
     fo.close();
     wb.close();
}     
     
      
    	 
    	 
    	 

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

}
