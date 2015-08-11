import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class WindowText extends Frame {

	     Panel flowLayoutPanel;
	     JFrame frame;
	     Read_excel rex=null;
	     String MESSAGE;
	     
	public String getMESSAGE() {
			return MESSAGE;
		}

		public void setMESSAGE(String mESSAGE) {
			MESSAGE = mESSAGE;
		}

	public WindowText(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		//设置短信内容
		 frame = new JFrame("Welcome");
		 rex = new Read_excel();
		//调整frame的大小和初始位置
	     frame.setSize(545, 100);
	     frame.setResizable(false);
	     frame.setLocation(100, 100);
	     flowLayoutPanel = new Panel();
	     flowLayoutPanel.setLayout(new FlowLayout());
	     Label text1;
	     Label text3_1;
	     Label text4_1;
	     final TextField text2; 
	     final TextField text3; 
	     final TextField text4; 
		 text1 = new Label("输入短信内容：");  
		 text3_1 = new Label("输入用户名：");  
		 text4_1 = new Label("输入短信密钥：");  
		 text2 = new TextField(30);
		 text3= new TextField(20);
		 text3.setText("巴黎一刻");
		 text4 = new TextField(20);
		 text4.setText("79f53cc0f7a6b2fc41c6");
		 //text2.setText("ILOVEYOU");
		 Button button1 = new Button("浏览Excel文件");
		 Button button2 = new Button("发送短信");
		 button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("点击了浏览");
				rex.getFileName();
				if(rex.get_filepath().isEmpty())
				{
					javax.swing.JOptionPane.showMessageDialog(null,"请选择相应文件");
				}
				else
				{
				   rex.getInfoROfPerson();
				}
			}
		});
		 button2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//System.out.println("点击了浏览");
					MESSAGE = text2.getText();
					SendMessages sm =new SendMessages();
					try {
						System.out.println(rex.get_phoneNumber()+"\t"+MESSAGE);
						if(!MESSAGE.isEmpty())
						sm.send_message(text3.getText(),text4.getText(),rex.get_phoneNumber(),MESSAGE+"【爱培客】");
						else
					    javax.swing.JOptionPane.showMessageDialog(null,"短信内容为空");
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		 
		 
		 flowLayoutPanel.add(text3_1);
		 flowLayoutPanel.add(text3);
		 flowLayoutPanel.add(text4_1);
		 flowLayoutPanel.add(text4);
		 
		 flowLayoutPanel.add(text1);
		 flowLayoutPanel.add(text2); 
		
		 flowLayoutPanel.add(button1);
		 flowLayoutPanel.add(button2);
		 
		 frame.add(flowLayoutPanel);
		 //增加窗口监听事件，使用内部类方法，并用监听器的默认适配器
	       frame.addWindowListener(new WindowAdapter(){
	 
	           //重写窗口关闭事件
	           @Override
	           public void windowClosing(WindowEvent arg0) {
	              System.exit(0);
	           }  
	       });
	   
	       //显示窗体
	       frame.setVisible(true);
	      
	       
  }
}
