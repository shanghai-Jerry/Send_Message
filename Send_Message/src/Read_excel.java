import javax.swing.JFileChooser; 
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.Data;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.*; 
public class Read_excel{
	
	 String _name;
	 String _id;
	 String _birthday;
	 String _phoneNumber;
	 String _filepath;
	 int _colums;
	 int  _rows;
	
	public Read_excel() {
		super();
		
	}
	public void getFileName()
	 {
		
		//初始化文件选择框
				JFileChooser fDialog = new JFileChooser();
				 FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        ".xls", "xls");
				 fDialog.setFileFilter(filter);

				//设置文件选择框的标题 
				fDialog.setDialogTitle("请选择Excel文件");
				//弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if(JFileChooser.APPROVE_OPTION == returnVal){
				       //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
					//System.out.println(fDialog.getSelectedFile());
					_filepath = fDialog.getSelectedFile().toString();
				}
				else
				{
					_filepath="";
				}
	  	 
	 }
	 public int get_colums() {
			return _colums;
		}
		public void set_colums(int _colums) {
			this._colums = _colums;
		}
		public int get_rows() {
			return _rows;
		}
		public void set_rows(int _rows) {
			this._rows = _rows;
		}
		
	public void getDataNumber()
	{
        int i = 0;
        Sheet sheet;
        Workbook book;
        Cell cell1,cell2,cell3;
        try { 
            //t.xls为要读取的excel文件名
            book= Workbook.getWorkbook(new File(_filepath)); 
             
            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet=book.getSheet(0); 
            //获取左上角的单元格
            _colums=sheet.getColumns();
            _rows = sheet.getRows();
   
        }
        catch(Exception e)  
        { } 
         
      }
	public String GetNowDate()
	{   
	    String temp_str="";   
	    Date dt = new Date();   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	    temp_str=sdf.format(dt);   
	    temp_str=temp_str.substring(5);
	    return temp_str;   
	} 
     public void getInfoROfPerson()
     {
    	 int i=0;
    	 int count=0;
    	 Sheet sheet;
         Workbook book;
         String str_date ="";
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
         Cell cell1,cell2,cell4,cell10;
         try { 
             //t.xls为要读取的excel文件名
             book= Workbook.getWorkbook(new File(_filepath)); 
              
             //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
             sheet=book.getSheet(0); 
             //获取左上角的单元格
             // cell1=sheet.getCell(0,0);
            // System.out.println("标题："+cell1.getContents()); 
             i=1;
             while(true)
             {
                 //获取每一行的单元格 
            	 //生日
                 cell1=sheet.getCell(1,i);//（列，行）
                 //卡号
                 cell2=sheet.getCell(2,i);
                 //姓名
                 cell4=sheet.getCell(4,i);
                 //手机
                 cell10=sheet.getCell(10,i);
                 
                 Date date = sdf.parse(cell1.getContents());
                 str_date=sdf.format(date);
                 str_date=str_date.substring(5);
                 System.out.println(i+"\t"+str_date+"\t"+this.GetNowDate()+"\t"+str_date.equals(this.GetNowDate()));
                 if(str_date.equals(this.GetNowDate()))
                 { 
                	 count++;
                	 if(count==1)
                	 {
                	  _phoneNumber=cell10.getContents();
                	 // System.out.println(_phoneNumber);
                	 }
                	 else
                	 {
                	 _phoneNumber=_phoneNumber+","+cell10.getContents();
                	 //System.out.println(_phoneNumber);
                	 }
                 }
                 if("".equals(cell1.getContents())==true)    //如果读取的数据为空
                     break;
               //  System.out.println(i+"\t"+cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell4.getContents()+"\t"+cell10.getContents()); 
                 i++;
             }
             book.close(); 
         }
         catch(Exception e)  { } 
     }
	 public String get_filepath() {
			return _filepath;
		}
		public void set_filepath(String _filepath) {
			this._filepath = _filepath;
		}
	 public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_birthday() {
		return _birthday;
	}
	public void set_birthday(String _birthday) {
		this._birthday = _birthday;
	}
	public String get_phoneNumber() {
		return _phoneNumber;
	}
	public void set_phoneNumber(String _phoneNumber) {
		this._phoneNumber = _phoneNumber;
	}
	
	 
	 
        
}
