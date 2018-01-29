import io.lance.gradle.common.file.service.sftp.impl.SFtpFileServiceImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Lance.
 * @time: 2018-01-23 16:37
 * @desc:
 */
public class SFtpTest {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();


    private SFtpFileServiceImpl fileService = null;

    @BeforeTest
    public void before() {
        fileService = new SFtpFileServiceImpl();
        fileService.init();
    }

    @Test
    public void get(){
        this.fileService.get("");
    }


}
