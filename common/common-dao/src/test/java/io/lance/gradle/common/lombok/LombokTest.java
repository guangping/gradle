package io.lance.gradle.common.lombok;

import io.lance.gradle.common.lombok.vo.User;
import org.testng.annotations.Test;

/**
 * @author lance.
 * @time: 2018-05-24 16:25
 * @desc:
 */
public class LombokTest {



    @Test
    public void run(){
        User user=new User();
        user.setAge(10);
        user.setName("aa");
        user.setSex('F');
        System.out.println(user.toString());

    }

}
