package configReader;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
 
import org.openqa.selenium.WebDriver;
 
 
public class ConfigReader {
    private Properties property;

    private FileInputStream fileinput;

    public ConfigReader() throws IOException
    {  
    	
        fileinput = new FileInputStream("C:\\Users\\2478647\\Downloads\\Miniprojectint\\src\\main\\resources\\ObjectRepo\\Object.properties");

       
        property = new Properties();
 
        property.load(fileinput);
    }

    public String get_Url()
    {
        return property.getProperty("BaseUrl"); 
        //The baseUrl is returned 
    }
 
    public String get_InitialSearch()
    {
        return property.getProperty("Initialtesting");
        //the initial search is done 
    }
 
    public String get_IdofEmail()
    {
        return property.getProperty("Emailby_id");
        //the input box for email
    }
 
    public String get_CorrectEmail()
    {
        return property.getProperty("correctEmail");
        //the valid email entered
        
    }
 
    public String get_IdofPassword()
    {
        return property.getProperty("password_id");
        //input box for the password 
    }
 
    public String get_correctPassword()
    {
        return property.getProperty("correctPassword");
        //the valid password entered
    }
 
    public String get_InitialLoginbutton()
    {
        return property.getProperty("Loginbutton");
        //checking the login button 
    }
    
    //check the search content 
    public String get_Content()
    {
        return property.getProperty("content");
    }
    
 
    //the expected search content   
    public String get_Expectedcontent()
    {
        return property.getProperty("EXtext");
    }
 
    public String get_Nametag()
    {
        return property.getProperty("Nametag");
        //name tag for the logout action
    }
 
    public String get_Logout()
    {
        return property.getProperty("Logout");
        //log out from the browser 
    }
 
    public String get_SignupwithEmail()
    {
        return property.getProperty("signupwithemail");
        //clicking the sign up button
    }
 
    public String get_Nameattforsignup()
    {
        return property.getProperty("signup_name");
        //checking  the name box
    }
 
    public String get_Signupnamecontent()
    {
        return property.getProperty("sign_name_content");
        //entering the name
    }
 
    public String get_ResultText()
    {
        return property.getProperty("Result");
    }
 
    public String get_search()
    {
        return property.getProperty("SearchQuora");
        //searching quora 
    }
 
    public String get_CheckLoginButton()
    {
        return property.getProperty("check_login");
        //checking the login button is enabled or not 
    }
 
    public String get_signupEmailButton()
    {
        return property.getProperty("signupwithemail");
        //checking the signupwithemail button
    }
 
    public String get_SecondEmail()
    {
        return property.getProperty("Email_second");
    }
 
    public String get_Incorrect_email()
    {
        return property.getProperty("Incorrect_email");
        //entering the invalid email
    }
 
    public String get_FirstSuggestion()
    {
        return property.getProperty("FirstSuggestion");
        //the error message is displayed or not 
    }
}