package com.oldsailor.authSrv

 import JWTAuthorizationFilter
 import org.springframework.context.annotation.Configuration
 import org.springframework.http.HttpMethod
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
 import org.springframework.security.config.annotation.web.builders.HttpSecurity
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
 import org.springframework.web.cors.CorsConfiguration
 import javax.servlet.http.HttpServletRequest


@EnableWebSecurity
@Configuration
class BasicAuthSecurity : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
           // .passwordEncoder(BCryptPasswordEncoder())
            .withUser("user")
            .password("password")
            .roles("USER")

    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors()
            .configurationSource { CorsConfiguration().applyPermitDefaultValues() }

        http.csrf().disable()
            .addFilterAfter(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/auth-srv/admin/sign-in", "/api/auth-srv/admin/").permitAll()
            .anyRequest().authenticated()

    }


}



