package Configurations;

import Constants.Constants;
import appModules.functionalActions.Login_Action;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Config {
    private static Logger Log = Logger.getLogger(Config.class.getName());

    //get the current time stamp
    public static String getCurrentTimeStamp(){
        SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date now = new Date();
        String strTimeStamp = df.format(now);
        Log.info(strTimeStamp);
        return strTimeStamp;
    }


}
