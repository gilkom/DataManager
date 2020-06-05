package gilko.marcin.datamanager.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;
     
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled from userss where username=?")
            .authoritiesByUsernameQuery("select username, role from userss where username=?")
        ;
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/product_list/new_product").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/product_list//edit/{id}").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/product_list//delete/{id}").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/customer_list/new_customer").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/customer_list//edit_customer/{id}").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/customer_list//delete_customer/{id}").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/sale_list/new_sale").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/sale_list//edit_sale/{id}").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/sale_list//delete_sale/{id}").hasAnyAuthority("ROLE_ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/404");
	}

}
