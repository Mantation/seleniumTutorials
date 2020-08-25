package Constants;

import java.io.File;

public class Constants {

    public static final String DATE_FORMAT = "ddMMyyyy_HHmmss";
    public static final String SCREENSHOT_FOLDER = "screenshots";
    public static final String REPORTS_FOLDER = "Reports";
    public static final String LOG_FOLDER = "TestOutput";
    public static final String INPUT_DATA_FOLDER = "inputData" + File.separator;

    //Timeouts
    public static final long BROWSER_PAGE_LOAD_TIMEOUT_SECONDS = 40;
    public static final long BROWSER_SCRIPT_TIMEOUT_SECONDS = 40;
    public static final long BROWSER_IMPLICIT_WAIT_SECONDS = 30;

    //Browser types
    public static final String FF_BROWSER = "firefox";
    public static final String C_BROWSER = "chrome";
    public static final String IE_BROWSER = "iexplore";

    //AUT
    public static final String AUT = "https://myspa-5b893.firebaseapp.com/";
    public static final String TEST_ENVIRONMENT = "qa";

    // ENVIRONMENT



}
