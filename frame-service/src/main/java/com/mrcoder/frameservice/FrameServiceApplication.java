package com.mrcoder.frameservice;

import com.mrcoder.framecommon.auth.AuthCheck;
import com.mrcoder.framecommon.config.api.SystemApiConfig;
import com.mrcoder.framecommon.config.http.RestTemplateConfig;
import com.mrcoder.framecommon.config.redis.RedisConfig;
import com.mrcoder.framecommon.config.swagger.SwaggerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@RestController
@MapperScan("com.mrcoder.frameservice.mapper")
@ComponentScan({"com.mrcoder.frameservice", "com.mrcoder.framecommon.utils"})
@Import({SwaggerConfig.class, RedisConfig.class, RestTemplateConfig.class, SystemApiConfig.class})
@SpringBootApplication
public class FrameServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrameServiceApplication.class, args);
    }

    @Value("${spring.profiles.active:default}")
    private String profile;

    /**
     * 测试首页
     *
     * @return
     */
    @AuthCheck(loginRequired = false)
    @GetMapping({"/index", "/"})
    public String index() {
        return "This is frame-service project【version = 20200413】【profile = " + profile + "】";
    }
}
