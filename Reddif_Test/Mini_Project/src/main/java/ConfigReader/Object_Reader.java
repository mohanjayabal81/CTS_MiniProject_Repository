package ConfigReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Object_Reader {
	Properties pro;
	FileInputStream fis;
	public Object_Reader() throws IOException
	{
		pro=new Properties();
		fis=new FileInputStream("C:\\Users\\2478810\\eclipse-workspace\\Mini_Project\\Object_Repository\\object.properties");
		pro.load(fis);
	}
	
	public String get_ApplicationUrl(){
		return pro.getProperty("base_Url");
	}
	public String getHeader(){
		return pro.getProperty("createAccount.header");
	}
	public String getLinksTagName(){
		return pro.getProperty("createAccount.tagname");
	}
	public String getTermsAndCondtionsLink(){
		return pro.getProperty("createAccount.linktext");
	}
	public String getCreateAccountLink() {
		return pro.getProperty("landingpage.linktext");
	}
}
