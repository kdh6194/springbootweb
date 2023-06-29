//package honeybee.springboot.springbootweb.webconfig;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@RequiredArgsConstructor
//@Configuration
//public class WebConfiguration implements WebMvcConfigurer {
//    private final LoginUserArgumentResolver loginUserArgumentResolver;
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(loginUserArgumentResolver);
//    }
//}