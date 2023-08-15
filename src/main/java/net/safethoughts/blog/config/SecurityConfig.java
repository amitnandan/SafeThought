package net.safethoughts.blog.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import net.safethoughts.blog.security.JwtAuthenticationEntryPoint;
import net.safethoughts.blog.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@SecurityScheme(
        name = "Bear Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtAuthenticationEntryPoint authenticationEntryPoint,
                          JwtAuthenticationFilter authenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        //authorize.anyRequest().authenticated()
                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                //.requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()

                ).exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}








//prequthorize, postauthorize ,prefilter and postfiler annotation
//public class SecurityConfig {
//
//    private UserDetailsService userDetailsService;
//
//    private JwtAuthenticationEntryPoint authenticationEntryPoint;
//
//    private JwtAuthenticationFilter authenticationFilter;
//
//    public SecurityConfig(UserDetailsService userDetailsService,
//                          JwtAuthenticationEntryPoint authenticationEntryPoint,
//                          JwtAuthenticationFilter authenticationFilter){
//        this.userDetailsService = userDetailsService;
//        this.authenticationEntryPoint = authenticationEntryPoint;
//        this.authenticationFilter = authenticationFilter;
//    }
//
//    //this authentication manager uses the userDetailService internally to authenticate the
//    //details and uses the password Encoder to encode and decode the password
//    //don't need to provide it explicitly
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//
//
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//    //security filter chain is an interface and default security interface
//    //is the one that implements this interface
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        http.csrf(csrf -> csrf.disable())
////                //authorize all incoming request
////                .authorizeHttpRequests((authorize)->{
////                    //authorize.anyRequest().authenticated();
////
////
////                    authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
////                            .requestMatchers("/api/auth/**").permitAll()
////                            .anyRequest().authenticated();
////                }).exceptionHandling(
////                        exception ->
////                                exception.
////                                        authenticationEntryPoint(jwtAuthenticationEntryPoint)
////                ).sessionManagement(
////                        session ->
////                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                )
////                .httpBasic(Customizer.withDefaults());
////
////        //this authentication filter will be executed before UsernamePasswordAuthenticationFilter
////        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
//
//
////    @Bean
////    public UserDetailsService userDetailsService(){
////        UserDetails amitNandan = User.builder().username("amit").password(passwordEncoder().encode("amit")).roles("USER")
////                .build();
////        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
////                .build();
////
////        return new InMemoryUserDetailsManager(amitNandan,admin);
////    }
////
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests((authorize) ->
//                        //authorize.anyRequest().authenticated()
//                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
//                                //.requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
//                                .requestMatchers("/api/auth/**").permitAll()
//                                .requestMatchers("/swagger-ui/**").permitAll()
//                                .requestMatchers("/v3/api-docs/**").permitAll()
//                                .anyRequest().authenticated()
//
//                ).exceptionHandling( exception -> exception
//                        .authenticationEntryPoint(authenticationEntryPoint)
//                ).sessionManagement( session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//
//        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//
//
//
//
//}
