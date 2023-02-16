package pl.pedyk.post.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostMapperConfig {

    @Bean
    public ModelMapper PostModelMapper() {
        return new ModelMapper();
    }
}
