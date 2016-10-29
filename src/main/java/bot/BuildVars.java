package bot;

/**
 * Created by said on 27.10.16.
 */
public class BuildVars {
    public static final Boolean debug = true;
    public static final Boolean useWebHook = false;
    public static final int PORT = 8443;
    public static final String EXTERNALWEBHOOKURL = "https://example.changeme.com:" + PORT; // https://(xyz.)externaldomain.tld
    public static final String INTERNALWEBHOOKURL = "https://localhost.changeme.com:" + PORT; // https://(xyz.)localip/domain(.tld)
    public static final String pathToCertificatePublicKey = "./YOURPEM.pem"; //only for self-signed webhooks
    public static final String pathToCertificateStore = "./YOURSTORE.jks"; //self-signed and non-self-signed.
    public static final String certificateStorePassword = "yourpass"; //password for your certificate-store

    public static final String OPENWEATHERAPIKEY = "<your-api-key>";

    public static final String DirectionsApiKey = "<your-api-key>";

    public static final String TRANSIFEXUSER = "<transifex-user>";
    public static final String TRANSIFEXPASSWORD = "<transifex-password>";

    public static final String pathToLogs = "./";

    public static final String linkDB = "jdbc:mysql://localhost:3306/asud_bot?useUnicode=true&characterEncoding=UTF-8";
    public static final String controllerDB = "com.mysql.jdbc.Driver";
    public static final String userDB = "root";
    public static final String password = "said1995";
}
