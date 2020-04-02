package Helpers;

public class FileReaderHelper {

    private static FileReaderHelper fileReaderHelper = new FileReaderHelper();
    private static ConfigFileHelper configFileHelper = new ConfigFileHelper();

    private FileReaderHelper() {

    }

    public static FileReaderHelper getInstance() {
        return fileReaderHelper;
    }

    public ConfigFileHelper getConfigFileHelper() {
        return (configFileHelper == null) ? new ConfigFileHelper() : configFileHelper;
    }


}
