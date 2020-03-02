package TestDataReaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelFileReader 
{
	public FileInputStream fis=null;
	public FileOutputStream fos=null;
	public HSSFWorkbook workbook=null;
	public HSSFSheet sheet=null;
	public HSSFRow row=null;
	public HSSFCell cell=null;
	String xlFilePath;
	
	
	public ExcelFileReader(String xlFilePath) throws Exception
	{
		this.xlFilePath=xlFilePath;
		fis=new FileInputStream(xlFilePath);
		workbook=new HSSFWorkbook(fis);
		fis.close();
	}

	public String getCellData(String sheetName, String colName, int rowNum) 
	{
		try
		{
			int colNum=-1;
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++)
			{
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum=i;
			}
			row=sheet.getRow(rowNum);
			cell=row.getCell(colNum);
			

			if(cell.getCellTypeEnum()==CellType.STRING)
				return cell.getStringCellValue();
			else if(cell.getCellTypeEnum()==CellType.NUMERIC || cell.getCellTypeEnum()==CellType.FORMULA) 
			{
				String cellValue=String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell))
				{
					DateFormat dt=new SimpleDateFormat("dd/mm/yy");
					java.util.Date date=cell.getDateCellValue();
					cellValue=dt.format(date);
				}
				return cellValue;
				
			}else if(cell.getCellTypeEnum()== CellType.BLANK)
				return"";
			else
				return String.valueOf(cell.getBooleanCellValue());				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return"data not found";
		}
		
			
	}

}
