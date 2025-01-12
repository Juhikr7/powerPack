package cc.altius.powerpack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringBootSecurity {

    @Autowired
    private UserDetailsService userService;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(this.userService);
        daoProvider.setPasswordEncoder(this.bCryptPasswordEncoder());
        return daoProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(form
                        -> form
                        .loginPage("/login.htm")
                        .defaultSuccessUrl("/index.htm")
                        .permitAll()
                )
                .logout(out
                        -> out
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login.htm")
                        .permitAll()
                )
                .authenticationProvider(this.authenticationProvider())
                .authorizeHttpRequests(r
                        -> r
                        .requestMatchers("/",
                                "/error/**",
                                "/js/**",
                                "/images/**",
                                "/css/**",
                                "/favicon.ico",
                                "/WEB-INF/jsp/**").permitAll()
                        .requestMatchers("/index.htm**").hasAnyAuthority("ROLE_BF_SHOW_HOME")
                        .requestMatchers(
                                "/addUser.htm**",
                                "/editUser.htm**",
                                "/showEditUser.htm**",
                                "/listUser.htm**"
                        ).hasAnyAuthority("ROLE_BF_MANAGE_USER")
                        .requestMatchers(
                                "/showCustomerRegPage.htm**",
                                "/addItems.htm**",
                                "/saveOrderDetails.htm**",
                                "/listOrder.htm**"
                        ).hasAnyAuthority("ROLE_BF_MANAGE_CUSTOMER")
                        .requestMatchers(
                                "/manageFlow.htm**",
                                "/addItems.htm**",
                                "/saveOrderDetails.htm**",
                                "/listOrder.htm**",
                                "/addFlow.htm**"
                        ).hasAnyAuthority("ROLE_BF_MANAGE_FLOW")
                );
        return http.build();
    }
}
