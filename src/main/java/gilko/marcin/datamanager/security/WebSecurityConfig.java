package gilko.marcin.datamanager.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled from users where username = ?")
		.authoritiesByUsernameQuery("select username, role from users where username=?");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/product_list/new_product").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/product_list//edit/{id}").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/product_list//delete/{id}").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/customer_list/new_customer").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/customer_list//edit_customer/{id}").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/customer_list//delete_customer/{id}").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/sale_list/new_sale").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/sale_list//edit_sale/{id}").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/sale_list//delete_sale/{id}").access("hasRole('ROLE_ADMIN')")
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll();
	}

}
