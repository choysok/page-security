package com.page.web.controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenUploadSuccess() throws Exception { // multipartFile 为参数
      String result  =  mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file").file(new MockMultipartFile("multipartFile","hello.txt","multipart/form-data","hello function,when will you come back to China.".getBytes("UTF-8"))))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                  .andReturn().getResponse().getContentAsString();

                               System.out.println("前台展示的数据"+result);
    }




    @Test
    public void whenQuerySuccess() throws Exception {
      String result =  mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username","jojo")
                .param("age","18")
                .param("ageTo","60")
                .param("xxx","hello")
//                .params("size","15")
//                .params("page","3")
//                .params("sort","age,desc") 降序排序
//                .params("sort","age,asc") 升序排序
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                 .andReturn().getResponse().getContentAsString();

                 System.out.println("前台展示的数据："+result);

    }

    @Test
    public void  whenGetInfoSuccess() throws Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
             //   .andExpect(MockMvcResultMatchers.status().isOk())
            //    .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                  .andReturn().getResponse().getContentAsString();

                  System.out.println("前台接收到后台的数据为："+result);

    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }




    @Test
    public  void whenCreateSuccess() throws Exception {

        //前台传送 时间 给 后台。后台提供时间戳，由前台决定如何展示数据。时间戳转换成日期
        Date date  = new Date();
        System.out.println(date.getTime());
        //模拟前端 需要传送到后台的 数据
        String content = "{\"username\":\"tom\",\"password\":\"123\",\"birthday\":"+date.getTime()+"}";


     String result  =   mockMvc.perform(MockMvcRequestBuilders.post("/user")
                    .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                           .andExpect(MockMvcResultMatchers.status().isOk())
                                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                                      .andReturn().getResponse().getContentAsString();

               //后台的数据格式 {id='1', username='tom', password='null', birthday=Sun May 03 15:38:09 CST 2020}
              // 返回的数据格式 {"id":"1","username":"tom","password":null,"birthday":1588491489322}

                  System.out.println(result);

    }



    @Test
    public  void whenUpdateSuccess() throws Exception {

        //前台传送 时间 给 后台。后台提供时间戳，由前台决定如何展示数据。时间戳转换成日期
        Date date  = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        //模拟前端 需要传送到后台的 数据
        String content = "{\"id\":\"1\",\"username\":\"dyson\",\"password\":null,\"birthday\":"+date.getTime()+"}";

        String result  =   mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        //后台的数据格式 {id='1', username='tom', password='null', birthday=Sun May 03 15:38:09 CST 2020}
        // 返回的数据格式 {"id":"1","username":"tom","password":null,"birthday":1588491489322}

        System.out.println(result);

    }


    @Test
    public void  whenDeleteSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                      .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

