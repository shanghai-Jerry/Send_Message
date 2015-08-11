import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMessages {

	/**
	 * @param args
	 
	短信发送后返回值	说　明
	-1	没有该用户账户
	-2	接口密钥不正确 [查看密钥]
	不是账户登陆密码
	-21	MD5接口密钥加密不正确
	-3	短信数量不足
	-11	该用户被禁用
	-14	短信内容出现非法字符
	-4	手机号格式不正确
	-41	手机号码为空
	-42	短信内容为空
	-51	短信签名格式不正确
	接口签名格式为：【签名内容】
	-6	IP限制
	大于0	短信发送数量
	*/
	HashMap<String,String> HM=null;
	public SendMessages() {
		super();
		// TODO Auto-generated constructor stub
		HM= new HashMap<String,String>();
		HM.put("-1", "没有该用户账户");
		HM.put("-2", "接口密钥不正确");
		HM.put("-21", "MD5接口密钥加密不正确");
		HM.put("-3", "短信数量不足");
		HM.put("-11", "该用户被禁用");
		HM.put("-14", "短信内容出现非法字符");
		HM.put("-4", "手机号格式不正确");
		HM.put("-41", "手机号码为空");
		HM.put("-42", "短信内容为空");
		HM.put("-51", "短信签名格式不正确");
		HM.put("-6", "IP限制");
			
	}
	
	 
 public String resultOfSendMeesage(String statusCode)
 {
	 String str_statusCode=null;
	 if(Integer.parseInt(statusCode)>0)
	 {
		 str_statusCode="发送成功"+statusCode+"条";
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
	post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
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
	System.out.println(result); //打印返回消息状态
	
	 String zt=resultOfSendMeesage(result);
	 javax.swing.JOptionPane.showMessageDialog(null,zt);
	
	post.releaseConnection();
	
	
}

}
