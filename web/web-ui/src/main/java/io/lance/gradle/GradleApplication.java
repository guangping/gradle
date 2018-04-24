package io.lance.gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication(scanBasePackages = {"io.lance.gradle.common.*", "io.lance.gradle.api.*"})
        /*@Import({
        DispatcherServletAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        JmxAutoConfiguration.class,
        MultipartAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class,
        WebMvcAutoConfiguration.class,
        EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,
        MessageSourceAutoConfiguration.class,
        RestTemplateAutoConfiguration.class,
        TransactionAutoConfiguration.class,
        WebClientAutoConfiguration.class,
        ProjectInfoAutoConfiguration.class
       // FreeMarkerServletWebConfiguration.class,
       //ReactiveSecurityAutoConfiguration
        //ConfigurationPropertiesAutoConfiguration
        //ValidationAutoConfiguration
        //RedisCacheConfiguration
        //ReactorCoreAutoConfiguration.class
       // RabbitAutoConfiguration.class
       // RabbitAnnotationDrivenConfiguration.class
        // WebSocketAutoConfiguration.class,
})*/
public class GradleApplication {

    public static void main(String[] args) {
        System.out.println("start ........");
        SpringApplication.run(GradleApplication.class, args);
    }
}
