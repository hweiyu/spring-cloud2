package com.imin.adminweb.conf;

import io.undertow.Undertow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2019/4/8 15:40
 **/
@Configuration
@Slf4j
public class SslConfig {

    @Value("${server.httpPort}")
    private Integer port;

    @Bean
    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory undertow = new UndertowEmbeddedServletContainerFactory();
        undertow.addBuilderCustomizers((Undertow.Builder builder) -> {
            builder.addHttpListener(port, "0.0.0.0");
        });
        log.info("*** Undertow http setting successful." + port);
        return undertow;
    }
}
