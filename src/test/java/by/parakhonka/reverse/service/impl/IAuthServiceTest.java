package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.configuration.AppConfiguration;
import by.parakhonka.reverse.service.IAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class IAuthServiceTest {
    @Autowired
    WebApplicationContext wac;
    @Autowired
    IAuthService mAuthService;

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void mytest1() throws Exception {
        MockMvc mockMvc = webAppContextSetup(wac).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

        assert mAuthService.getUserName().equals("user1");
    }
}
