package ar.edu.unq.cookitbackend.controller;
import ar.edu.unq.cookitbackend.dto.request.LoginGoogleRequestDto;
import ar.edu.unq.cookitbackend.dto.request.LoginRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.JwtResponse;
import ar.edu.unq.cookitbackend.security.JwtAuthenticationEntryPoint;
import ar.edu.unq.cookitbackend.security.JwtRequestFilter;
import ar.edu.unq.cookitbackend.service.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IAuthService authService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private UserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testShouldCreateNewUser() throws Exception {
        UserRequestDto user = UserRequestDto.builder()
                .email("dani.villegas@gmail.com")
                .name("dani")
                .lastname("villegas")
                .password("test")
                .build();

        mockMvc.perform(post("/api/auth/register").servletPath("/api")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testShouldLoginUser() throws Exception {
    JwtResponse response = JwtResponse.builder()
                .email("dani.villegas@gmail.com")
                .token("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZXNpYXNzc3NzQGdtYWlsLmNvbSIsImV4cCI6MTYwNDQ1NDcyNCwiaWF0IjoxNjA0MDk0NzI0fQ.IVdNK1-yN52Ac9PZPQbyJbxGYPRSxjkHlBJx8ozENjpyQrGHdzXbhBM43Sv7fZnfPMC2eBoLk69gHyuoFd5L2QeyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZXNpYXNzc3NzQGdtYWlsLmNvbSIsImV4cCI6MTYwNDQ1NDcyNCwiaWF0IjoxNjA0MDk0NzI0fQ.IVdNK1-yN52Ac9PZPQbyJbxGYPRSxjkHlBJx8ozENjpyQrGHdzXbhBM43Sv7fZnfPMC2eBoLk69gHyuoFd5L2Q")
                .build();

        given(authService.login(any(LoginRequestDto.class))).willReturn(response);

        mockMvc.perform(post("/api/auth/login").servletPath("/api")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(response.getEmail())))
                .andExpect(jsonPath("$.token", is(response.getToken())));
    }

    @Test
    public void testShouldLoginSocialUser() throws Exception {
        JwtResponse response = JwtResponse.builder()
                .email("dani.villegas@gmail.com")
                .token("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZXNpYXNzc3NzQGdtYWlsLmNvbSIsImV4cCI6MTYwNDQ1NDcyNCwiaWF0IjoxNjA0MDk0NzI0fQ.IVdNK1-yN52Ac9PZPQbyJbxGYPRSxjkHlBJx8ozENjpyQrGHdzXbhBM43Sv7fZnfPMC2eBoLk69gHyuoFd5L2QeyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZXNpYXNzc3NzQGdtYWlsLmNvbSIsImV4cCI6MTYwNDQ1NDcyNCwiaWF0IjoxNjA0MDk0NzI0fQ.IVdNK1-yN52Ac9PZPQbyJbxGYPRSxjkHlBJx8ozENjpyQrGHdzXbhBM43Sv7fZnfPMC2eBoLk69gHyuoFd5L2Q")
                .build();

        given(authService.loginSocial(any(LoginGoogleRequestDto.class))).willReturn(response);

        mockMvc.perform(post("/api/auth/login/social").servletPath("/api")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(response.getEmail())))
                .andExpect(jsonPath("$.token", is(response.getToken())));
    }

}
