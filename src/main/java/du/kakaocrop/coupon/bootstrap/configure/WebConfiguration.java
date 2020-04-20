package du.kakaocrop.coupon.bootstrap.configure;

import du.kakaocrop.coupon.bootstrap.interceptor.SignInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"du.kakaocrop.coupon.api"})
public class WebConfiguration implements WebMvcConfigurer {
    private static final String[] EXCLUDE_PATHS = {
            "/member/**",
            "/error/**"
    };

    @Autowired
    private SignInterceptor signInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/coupon/sign/**");
    }
}
