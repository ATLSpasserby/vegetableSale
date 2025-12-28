package com.vegetablesale.securityjwt.config;

import com.vegetablesale.securityjwt.dao.UserMapper;
import com.vegetablesale.securityjwt.handler.MyAuthenticationFailureHandler;
import com.vegetablesale.securityjwt.handler.MyAuthenticationSuccessHandler;
import com.vegetablesale.securityjwt.service.UserService;
import com.vegetablesale.securityjwt.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Resource
    private UserService userService;

    //使用明文密码，NoOpPasswordEncoder加密为不加密
    @Bean
    public PasswordEncoder passwordEncoder(){

//        // 使用 DelegatingPasswordEncoder 来支持密码升级检测
//        String idForEncode = "bcrypt";
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put(idForEncode, new BCryptPasswordEncoder());
//        encoders.put("noop", NoOpPasswordEncoder.getInstance());
//
//        DelegatingPasswordEncoder delegatingPasswordEncoder =
//                new DelegatingPasswordEncoder(idForEncode, encoders);
//
//        // 设置默认编码器为 bcrypt
//        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
//
//        return delegatingPasswordEncoder;


        
//        返回此工厂方法，此工厂方法会自动创建包含所有推荐编码器的 DelegatingPasswordEncoder
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        //匹配合适的AuthenticationProvider（DaoAuthenticationProvider）
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //匹配基于数据库认证的UserDetailsService对象
        provider.setUserDetailsService(userService);
        //创建并返回认证管理器对象（返回实现ProviderManager）
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests(requests -> requests
                        //放行login页面和register页面无需经过过滤链校验
                        .requestMatchers("/loginSource","/user/register","/login").permitAll()
                        //其余所有页面都需要经过校验
                        .anyRequest().authenticated())
                .formLogin(
//                        //设置认证成功的结果过滤器为自己写的
//                        form -> form
//                                .successHandler(new MyAuthenticationSuccessHandler())
//                                .failureHandler(new MyAuthenticationFailureHandler())
                        form -> form.disable()
                )
                .csrf(csrf->csrf.disable());

//        //所有页面都必须经过过滤链校验
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());

//        //登录方式表单登录
//        http.formLogin(Customizer.withDefaults());
//        弹窗登录
//        http.httpBasic(Customizer.withDefaults());

//        //跨域请求关闭
//        http.csrf(csrf->csrf.disable());


        return http.build();
    }

}
