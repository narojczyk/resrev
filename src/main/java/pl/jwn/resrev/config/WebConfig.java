package pl.jwn.resrev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


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
