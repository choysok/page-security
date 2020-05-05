package com.page.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;


import java.io.IOException;

public class MockServer {
    public static void main(String[] args) throws IOException {
        WireMock.configureFor("127.0.0.1",8062);
        WireMock.removeAllMappings();

        mock("/order/1","01");
        mock("/order/2","02");

    }

    private static void mock(String url,String file) throws IOException {
         ClassPathResource classPathResource = new ClassPathResource("mock/response/"+file+".txt");
        //StringUtils.join(FileUtils.readLines(classPathResource.getFile(),"UTF-8").toArray(),"\n");
         String content =   FileUtils.readFileToString(classPathResource.getFile(),"UTF-8");

         WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200))
        );
    }

}
