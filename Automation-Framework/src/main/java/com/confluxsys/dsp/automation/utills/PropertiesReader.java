package com.confluxsys.dsp.automation.utills;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader  {
  private  Properties properties;
  /*
     String fileInputStream = "C:\\Users\\Confluxsys\\Desktop\\DSP_Automation_Framework\\dsp-automation-selenium\\src\\main\\java\\com\\confluxsys\\dsp\\automation\\utills\\config.properties";
   */
  public PropertiesReader()
  {
      properties = new Properties();
      try {
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + File.separator + "src"+ File.separator +"main"+File.separator +"java"+File.separator +"com"+File.separator +"confluxsys"+File.separator +"dsp"+File.separator +"automation"+File.separator +"utills"+File.separator+"config.properties");
            properties.load(input);
      } catch (IOException e)
      {
            e.printStackTrace();
      }
  }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
