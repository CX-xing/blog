package com.cx.blog;

import org.junit.*;
import org.junit.jupiter.api.Test;
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

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
    }

    @BeforeClass
    public static void beforeClassTest() {
        System.out.println("before class test");
    }

    @Before
    public void beforeTest() {
        System.out.println("before test");
    }

    /**
     * Assert
     * 上面代码中，我们使用了Assert类提供的assert口方法，下面列出了一些常用的assert方法：
     * <p>
     * assertEquals("message",A,B)，判断A对象和B对象是否相等，这个判断在比较两个对象时调用了equals()方法。
     * <p>
     * assertSame("message",A,B)，判断A对象与B对象是否相同，使用的是==操作符。
     * <p>
     * assertTrue("message",A)，判断A条件是否为真。
     * <p>
     * assertFalse("message",A)，判断A条件是否不为真。
     * <p>
     * assertNotNull("message",A)，判断A对象是否不为null。
     * <p>
     * assertArrayEquals("message",A,B)，判断A数组与B数组是否相等。
     */
    @Test
    public void Test1() {
        System.out.println("test 1+1=2");
        Assert.assertEquals(2, 1 + 1);
    }

    @Test
    public void Test2() {
        System.out.println("test 2+2=4");
        Assert.assertEquals(4, 2 + 2);
    }

    @After
    public void afterTest() {
        System.out.println("after test");
    }

    @AfterClass
    public static void afterClassTest() {
        System.out.println("after class test");
    }

}
