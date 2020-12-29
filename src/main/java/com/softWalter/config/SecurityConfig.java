package com.softWalter.config;

import com.softWalter.service.implement.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    Metodo para criptografar e descriptografar senhas.

    @Bean
    public PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence charSequence) {
            return null;
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return false;
        }
    }*/
    /*Traz os objetos para auenticação dos usuarios.
    ex: email e senha.(parte de autenticação.)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
                /*
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("WalterFreitas")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .and()

                .withUser("WalterHeitor")
                .password(passwordEncoder().encode("1234"))
                .roles("USER", "ADMIN");
                 */
    }

    /*Verifica se usuario esta autorizado.
    Nivel de permições.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/apirest/clientes/**")
                //.authenticated()
                    .hasAnyRole("USER", "ADMIN")
                //.antMatchers("/api/pedidos/**")
                //    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/produtos/**")
                    .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                    .permitAll()
                .anyRequest().authenticated()
        .and()
             //.formLogin();
            .httpBasic();

    }
}
