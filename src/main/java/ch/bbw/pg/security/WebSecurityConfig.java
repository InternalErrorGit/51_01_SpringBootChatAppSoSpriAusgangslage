package ch.bbw.pg.security;

import ch.bbw.pg.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService userService;

    public WebSecurityConfig(MemberService userService) {
        this.userService = userService;
    }

    @Autowired
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("admin");
        auth.inMemoryAuthentication().withUser("user").password("user").roles("user");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new CustomPasswordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/fragments/*").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/img/*").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/contact.html").permitAll()
                .antMatchers("/get-register").permitAll()
                .antMatchers("/registerconfirmed.html").permitAll()
                .antMatchers("/404.html").permitAll()
                .antMatchers("/get-members").hasRole("admin")
                .antMatchers("/get-channel").hasRole("user")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic()
                .and().exceptionHandling().accessDeniedPage("/403.html");
    }


}
