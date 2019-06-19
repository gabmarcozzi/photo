package it.uniroma3.siw.photo.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("guest/galleryByPhotos");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin/home");
    }
}