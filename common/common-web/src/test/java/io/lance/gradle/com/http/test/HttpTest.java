package io.lance.gradle.com.http.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

/**
 * @author Lance.
 * @time: 2017-12-04 11:47
 * @desc:
 */
public class HttpTest {

    private String jsonParam = null;//参数
    private String URL = null;//url

    @Test
    public void send() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        //cookie
        headers.add("Cookie", "csrfToken=1855a6a6274b3bfbabfdb64878faff05");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paramJson", jsonParam);
        params.add("csrfToken", "1855a6a6274b3bfbabfdb64878faff05");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        String result = restTemplate.postForObject(URL, requestEntity, String.class);
    }
}
