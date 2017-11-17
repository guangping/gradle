package io.lance.gradle.common.web.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;

/**
 * Author Lance.
 * Date: 2017-09-05 14:50
 * Desc:
 */
public class DirectiveWrapperUtils {


    private static class BeansWrapperClassLoader {
        private static final BeansWrapper instance = new BeansWrapperBuilder(Configuration.VERSION_2_3_26).build();
    }

    /**
     * @desc:获取wrapper
     * @author lance
     * @time: 2017-09-05 14:51:03
     */
    public static BeansWrapper getBeansWrapper() {
        return BeansWrapperClassLoader.instance;
    }
}
