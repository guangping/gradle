package io.lance.gradle.common.core.log4j2;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @desc: 日志输出ip
 * @author lance
 * @time: 2017-11-14 17:44:33
 * 使用   <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%ip] %-5level %c:%L - %m%n"/>
 */
@Plugin(name = "IPatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({"ip"})
public class IPatternConverter extends LogEventPatternConverter {

    private static class IPatternConverterClassesLoader {
        static final IPatternConverter INSTANCE = new IPatternConverter();
    }

    private IPatternConverter() {
        super("ip", "ip");
    }

    public static IPatternConverter newInstance() {
        return IPatternConverterClassesLoader.INSTANCE;
    }


    @Override
    public void format(LogEvent logEvent, StringBuilder builder) {
        builder.append(getHostIp());
    }

    private static String getHostIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
