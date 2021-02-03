package letsCode.sweater.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // look at application.property
    @Value("${upload.path}")
    private String uploadPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        // The addViewController() method adds view controller
        // The addViewController() method overrides the method of the same name in WebMvcConfigurer
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // for example: '/img/icon', '/img/logo' or '/img/button'
        registry.addResourceHandler("/img/**")
                // adding path to get file
                .addResourceLocations("file:/" + uploadPath + "/");
        registry.addResourceHandler("/static/**")
                // adding path to seek file in hierarchy of project only and to get this file
                .addResourceLocations("classpath:/static/");
    }

}
