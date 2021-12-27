package com.deha.fashionwebsite.config;

import com.deha.fashionwebsite.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/* @Configurable xác định lớp WebSecurityConfig của ta là một lớp dùng để cấu hình*/
@Configurable
/* @EnableWebSecurity: kích hoạt việc tích hợp SPring Security vói Spring MVC*/
@EnableWebSecurity
/* WebSecurityConfiguration: là một interface giusp cho cài đặt các thông tin dễ dàng hơn*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /* trong lớp hiện tại ta cần gọi đến interface UserDetailService để cấu hình do đó ta sẽ inject UserDetailsService */
    @Autowired
    private IUserService userService;

    /* việc mã hóa mật khẩu là do interface PasswordEncoder đảm nhận. Ở đây là mã hóa mật khẩu bằng thuật toán BCrypt
     * Nhưng để sử dụng được PasswordEncoder thì phải cấu hình để PasswordEncoder thành một Bean*/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /* http đai diện cho người dùng nhập vào www.abc
         * antMachers: khai báo các đường dẫn của request
         * */
        http.authorizeRequests().antMatchers(
                        "/resources/**",
                        "/css/**",
                        "/js/**",
                        "/image/**",
                        "/favicon.icon")
                .permitAll()
                /* permitAll(): cho phép tất cả user đều có thể truy cập được*/
                .anyRequest().authenticated()
                .and()
                .formLogin()
                /* loginPage("/login"): đuòng dẫn đến trang chứa form đăng nhập*/
                .loginPage("/login")
                /* dẫn đến trang đăng nhập thành công*/
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                /* khiến cho HTTPSession bị vô hiệu sau đó sẽ xóa Authentication*/
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                /* xem sự kiện đăng xuất có xảy ra hay không.*/
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                /* nếu sự kiện đăng xuất xảy ra thì sẽ đưa người dùng về trang đăng nhập*/
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

//    public void configure(WebSecurity web) throws Exception {
//        // web.ignoring().antMatchers("/resources/static/**").anyRequest();
//    }
}
