import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMessages {

	/**
	 * @param args
	 
	���ŷ��ͺ󷵻�ֵ	˵����
	-1	û�и��û��˻�
	-2	�ӿ���Կ����ȷ [�鿴��Կ]
	�����˻���½����
	-21	MD5�ӿ���Կ���ܲ���ȷ
	-3	������������
	-11	���û�������
	-14	�������ݳ��ַǷ��ַ�
	-4	�ֻ��Ÿ�ʽ����ȷ
	-41	�ֻ�����Ϊ��
	-42	��������Ϊ��
	-51	����ǩ����ʽ����ȷ
	�ӿ�ǩ����ʽΪ����ǩ�����ݡ�
	-6	IP����
	����0	���ŷ�������
	*/
	HashMap<String,String> HM=null;
	public SendMessages() {
		super();
		// TODO Auto-generated constructor stub
		HM= new HashMap<String,String>();
		HM.put("-1", "û�и��û��˻�");
		HM.put("-2", "�ӿ���Կ����ȷ");
		HM.put("-21", "MD5�ӿ���Կ���ܲ���ȷ");
		HM.put("-3", "������������");
		HM.put("-11", "���û�������");
		HM.put("-14", "�������ݳ��ַǷ��ַ�");
		HM.put("-4", "�ֻ��Ÿ�ʽ����ȷ");
		HM.put("-41", "�ֻ�����Ϊ��");
		HM.put("-42", "��������Ϊ��");
		HM.put("-51", "����ǩ����ʽ����ȷ");
		HM.put("-6", "IP����");
			
	}
	
	 
 public String resultOfSendMeesage(String statusCode)
 {
	 String str_statusCode=null;
	 if(Integer.parseInt(statusCode)>0)
	 {
		 str_statusCode="���ͳɹ�"+statusCode+"��";
	 }
	 else
	 {
		 str_statusCode = HM.get(statusCode);
	 }
	return str_statusCode;
	 
 }
public void send_message(String name,String passwd,String phone,String Messages) throws Exception
{
	HttpClient client = new HttpClient();
	PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn"); 
	post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//��ͷ�ļ�������ת��
	NameValuePair[] data ={ new NameValuePair("Uid", name),new NameValuePair("Key", passwd),new NameValuePair("smsMob",phone),new NameValuePair("smsText",Messages)};
	post.setRequestBody(data);

	client.executeMethod(post);
	Header[] headers = post.getResponseHeaders();
	int statusCode = post.getStatusCode();
	System.out.println("statusCode:"+statusCode);

	for(Header h : headers)
	{
	  System.out.println(h.toString());
	}
	String result = new String(post.getResponseBodyAsString().getBytes("utf8")); 
	System.out.println(result); //��ӡ������Ϣ״̬
	
	 String zt=resultOfSendMeesage(result);
	 javax.swing.JOptionPane.showMessageDialog(null,zt);
	
	post.releaseConnection();
	
	
}

}
