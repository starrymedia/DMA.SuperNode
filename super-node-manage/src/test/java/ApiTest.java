import lombok.extern.slf4j.Slf4j;
import org.elastos.record.ManageApplication;
import org.elastos.record.api.controller.ApiController;
import org.elastos.record.manage.task.NodeScheduled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/21 11:16
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageApplication.class)
@Slf4j
public class ApiTest {

    @Autowired
    private ApiController apiController;

    @Autowired
    private NodeScheduled nodeScheduled;

    @Test
    public void test() {

        apiController.htmlApi("EHXHcn4ZcHAfE4LjBDDG5myEECPuEG3ReQ");

    }

    @Test
    public void test2(){
        nodeScheduled.incomeScheduled();
    }

}
