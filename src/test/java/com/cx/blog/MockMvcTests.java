package com.cx.blog;

import com.cx.blog.model.UserVo;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author :  C__xing
 * @Date :  2019/11/19
 */
@SpringBootTest
public class MockMvcTests {
    /**
     * MockMvc
     * 下文中，对Controller的测试需要用到MockMvc技术。MockMvc，从字面上来看指的是模拟的MVC，即其可以模拟一个MVC环境，向Controller发送请求然后得到响应。
     * <p>
     * 在单元测试中，使用MockMvc前需要进行初始化，如下所示：
     */
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //模拟一个get请求：
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}", "mrbird"));
        //模拟一个post请求：
        mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));
        //模拟文件上传：

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/fileupload").file("file", "文件内容".getBytes("utf-8")));
        //模拟请求参数：

// 模拟发送一个message参数，值为hello
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("message", "hello"));
// 模拟提交一个checkbox值，name为hobby，值为sleep和eat
        mockMvc.perform(MockMvcRequestBuilders.get("/saveHobby").param("hobby", "sleep", "eat"));
        //也可以直接使用MultiValueMap构建参数：

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name", "mrbird");
        params.add("hobby", "sleep");
        params.add("hobby", "eat");
        mockMvc.perform(MockMvcRequestBuilders.get("/hobby/save").params(params));
        //模拟发送JSON参数：

        String jsonStr = "{\"username\":\"Dopa\",\"passwd\":\"ac3af72d9f95161a502fd326865c2f15\",\"status\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));
        //实际测试中，要手动编写这么长的JSON格式字符串很繁琐也很容易出错，可以借助Spring Boot自带的Jackson技术来序列化一个Java对象（可参考Spring Boot中的JSON技术），如下所示：

        UserVo user = new UserVo();
        user.setUsername("Dopa");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        user.setStatus("1");

//        String userJson = mapper.writeValueAsString(user);
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(userJson.getBytes()));
        //其中，mapper为com.fasterxml.jackson.databind.ObjectMapper对象。

        //模拟Session和Cookie：

//        mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr(name, value));
//        mockMvc.perform(MockMvcRequestBuilders.get("/index").cookie(new Cookie(name, value)));
        //设置请求的Content - Type：

        mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(MediaType.APPLICATION_JSON_UTF8));
        //设置返回格式为JSON：

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON));
        //模拟HTTP请求头：

//        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).header(name, values));
        //MockMvc处理返回结果

        //期望成功调用，即HTTP Status为200：

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //期望返回内容是application / json：

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        //检查返回JSON数据中某个值的内容：

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("mrbird"));
        //这里使用到了jsonPath，$代表了JSON的根节点。更多关于jsonPath的介绍可参考 https://github.com/json-path/JsonPath。

        //判断Controller方法是否返回某视图：

        mockMvc.perform(MockMvcRequestBuilders.post("/index"))
                .andExpect(MockMvcResultMatchers.view().name("index.html"));
        //比较Model：

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("password"))
                .andExpect(MockMvcResultMatchers.model().attribute("username", "mrbird"));
        //比较forward或者redirect：

        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index.html"));
// 或者
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("index.html"));
        //比较返回内容，使用content()：

// 返回内容为hello
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.content().string("hello"));

// 返回内容是XML，并且与xmlCotent一样
//        mockMvc.perform(MockMvcRequestBuilders.get("/index")).andExpect(MockMvcResultMatchers.content().xml(xmlContent));

// 返回内容是JSON ，并且与jsonContent一样
//        mockMvc.perform(MockMvcRequestBuilders.get("/index")).andExpect(MockMvcResultMatchers.content().json(jsonContent));
        //输出响应结果：

        mockMvc.perform(MockMvcRequestBuilders.get("/index")).andDo(MockMvcResultHandlers.print());

    }
}
