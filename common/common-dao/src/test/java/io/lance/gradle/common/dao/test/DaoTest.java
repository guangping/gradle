package io.lance.gradle.common.dao.test;

import io.lance.gradle.common.core.util.JsonUtils;
import io.lance.gradle.common.dao.AppMain;
import io.lance.gradle.common.dao.modules.sys.entity.SysConfig;
import io.lance.gradle.common.dao.modules.sys.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Lance.
 * @time: 2017-11-22 16:45
 * @desc:
 */
@SpringBootTest(classes = AppMain.class)
public class DaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Test
    public void run() {
        System.out.println(sysConfigMapper);
    }

    @Test
    public void query() {
        SysConfig sysConfig = this.sysConfigMapper.selectByPrimaryKey(1);
        System.out.println(JsonUtils.toJSONString(sysConfig));
    }

}
