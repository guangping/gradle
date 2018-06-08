import io.lance.gradle.common.file.service.sftp.impl.SFtpFileServiceImpl;
import io.lance.gradle.common.file.vo.EbsFile;
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
    public void run() {
        String s = "C:\\\\Users\\\\magua\\\\Desktop\\\\jiagou.jpg";
        EbsFile ebsFile = fileService.put(s);
        System.out.println(ebsFile);
    }

    @Test
    public void upload() {
        String s = "C:\\\\Users\\\\magua\\\\Desktop\\\\jiagou.jpg";
        //this.fileService.put("C:\\Users\\magua\\Desktop\\jiagou.jpg");
       /* String fileId=FileUtil.getFileId();
        System.out.println(fileId+","+fileId.length());*/
        //System.out.println(FilenameUtils.getName(s));
    }


}
