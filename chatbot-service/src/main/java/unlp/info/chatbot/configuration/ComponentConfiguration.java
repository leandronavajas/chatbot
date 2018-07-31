package unlp.info.chatbot.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(
    basePackages = {
        "unlp.info.chatbot.controller",
        "unlp.info.chatbot.facade",
        "unlp.info.chatbot.dto.transformer",
        "unlp.info.chatbot.transformer",
        "unlp.info.chatbot.operation",
        "unlp.info.chatbot.db",
        "unlp.info.chatbot.service",
        "unlp.info.chatbot.exception",
        "unlp.info.chatbot.client",
        "unlp.info.chatbot.factory",
        "unlp.info.chatbot.connector"
    }
)
public class ComponentConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods("GET", "POST", "OPTIONS", "PATCH", "PUT", "DELETE")
            .allowedOrigins("*")
            .allowedHeaders("*")
            .maxAge(8600);
    }

}
