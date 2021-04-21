package com.timtro247.maven1311.config;

import com.timtro247.maven1311.service.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.security.SecureRandom;
import java.util.Arrays;

@Configuration //  xac dinh lop WebSecurityconfig cua ta la mot lop dung de cau hinh
@EnableWebSecurity // kich hoat viec tich hop Spring Security voi Spring MVC
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {

    private static final String SALT = "salt"; // salt be protected carefully
    private final Environment env;
    private final UserSecurityService userSecurityService;
    private final SimpleAuthenticationSuccessHandler successHandler;

    @Autowired
    public SecurityConfig(Environment env, UserSecurityService userSecurityService, SimpleAuthenticationSuccessHandler successHandler) {
        this.env = env;
        this.userSecurityService = userSecurityService;
        this.successHandler = successHandler;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    private static final String[] PUBLIC_MATCHERS = {"/webjars/**", "/web/css/**", "/web/js/**", "/web/images/**", "/",
            "/index", "/register", "/login", "/paging/**","/page/**","/filter/**","/search/**", "/chitiet/**","/post/**"};

    private static final String[] ADMIN_MATCHERS = {"/admin/**", "/admin-home/**","/paging/**"};

    private static final String[] USER_MATCHERS = {"/user/**", "/post/**", "/room/**", "/paging/**"};

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//                withUser("hieuminh22490@gmail.com").roles("ADMIN").
//                and().withUser("hieuminh22690@gmail.com").roles("USER");
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
//                .antMatchers(ADMIN_MATCHERS).hasRole("ADMIN")
//                .antMatchers(USER_MATCHERS).hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable().cors().disable()
                .formLogin().failureUrl("/login?error").successHandler(successHandler).loginPage("/login")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
}
