package com.crazy.demovhr.config;

import com.crazy.demovhr.model.Hr;
import com.crazy.demovhr.model.RespBean;
import com.crazy.demovhr.service.HrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName SecurityConfig
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/16 15:03
 * @Version 1.0
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Autowired
    CustomUrlMyDecisionManager customUrlMyDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    //配置登录成功或者登录失败向前端传送的json数据
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //剩下请求是登录之后能够访问的
                //.anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(customUrlMyDecisionManager);
                        return o;
                    }
                })
                .and()
                //表单登录
                .formLogin()
                //修改默认登录的username
                .usernameParameter("username")
                //修改默认登陆的password
                .passwordParameter("password")
                //处理表单登录的url
                .loginProcessingUrl("/doLogin")
                //默认看到的登录页面，如果是前后端分离的话，就不用配置登录页面
                .loginPage("/login")
                //登录成功的处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        //返回一段json
                        resp.setContentType("application/json;charset=utf-8");
                        //这是向外写的
                        PrintWriter out = resp.getWriter();
                        //登录成功的hr对象
                        Hr hr = (Hr) auth.getPrincipal();
                        //忽视掉传给前端的密码
                        hr.setPassword(null);
                        RespBean ok = RespBean.ok("登录成功！", hr);
                        //把hr写成字符串
                        String s = new ObjectMapper().writeValueAsString(ok);
                        //最后将字符串写出去
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                //登录失败的处理
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        RespBean respBean = RespBean.error("登录失败");
                        if (exception instanceof LockedException) {
                            respBean.setMsg("账户被锁定，请联系管理员！");
                        }else if (exception instanceof CredentialsExpiredException) {
                            respBean.setMsg("密码过期，请联系管理员！");
                        }else if (exception instanceof AccountExpiredException) {
                            respBean.setMsg("账户过期，请联系管理员！");
                        }else if (exception instanceof DisabledException) {
                            respBean.setMsg("账户被禁用，请联系管理员");
                        }else if (exception instanceof BadCredentialsException) {
                            respBean.setMsg("用户名或者密码错误，请重新输入");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                //跟登录相关的接口直接访问
                .permitAll()
                .and()
                .logout()
                //注销后回调
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功！")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                //关闭crsf攻击
                .csrf()
                .disable().exceptionHandling()
                //没有认证时，再这里处理结果不需要重定向
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                //如果长时间没登陆或者服务器做了改变给出一个响应码让前端根据响应码做出反应
                resp.setStatus(401);
                PrintWriter out = resp.getWriter();
                RespBean respBean = RespBean.error("访问失败");
                if (e instanceof InsufficientAuthenticationException) {
                    respBean.setMsg("请求失败，请联系管理员！");
                }
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });


    }
}
