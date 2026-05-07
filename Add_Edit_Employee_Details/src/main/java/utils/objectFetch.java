package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class objectFetch {
Properties pro;
public objectFetch() throws IOException{
	pro=new Properties();
	FileInputStream file=new FileInputStream("C:\\Users\\2478661\\eclipse-workspace\\Add_Edit_Employee_Details\\ObjectRepository\\object.properties");
	pro.load(file);
}
public String getLocator(String key){
    return pro.getProperty(key);
}
public String getUrl() {
	return pro.getProperty("baseurl");
}
}
