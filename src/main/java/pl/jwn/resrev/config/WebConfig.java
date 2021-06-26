package pl.jwn.resrev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addFormatters(FormatterRegistry registry){
//        registry.addConverter(publisherConverter());
//    }

//    @Bean
//    public PublisherConverter publisherConverter(){
//        return new PublisherConverter();
//    }

//    @Bean(name="localeResolver")
//        public LocaleContextResolver localeResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
////        localeResolver.setDefaultLocale(new Locale("pl","PL"));
//        localeResolver.setDefaultLocale(Locale.forLanguageTag("PL"));
//        return localeResolver;
//    }

}
