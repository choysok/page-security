package com.page.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.page.domain.User;
import com.page.domain.UserQueryCondition;
import com.page.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {



    @GetMapping("/me")
    public Object getCurrentUser(){

        return SecurityContextHolder.getContext().getAuthentication();
    }


    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println("ID:"+id);
    }



    @PutMapping("/{id:\\d+}")
    //参数 User user 用来接收前端的数据，如果是json格式的数据需要加上@RequestBody才能解析。
    public User update(@Valid @RequestBody User user, BindingResult errors){

        if(errors.hasErrors()){

            errors.getAllErrors().stream().forEach(error -> {

//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + "\t"+error.getDefaultMessage();
//                System.out.println(message);

                       //可以自己定义message的值在POJO
                System.out.println(error.getDefaultMessage());
            });

        }

        System.out.println(user);

        user.setId("1");
        // User{id='1', username='tom', password='null', birthday=Sun May 03 15:38:09 CST 2020}
        System.out.println(user);

        return user;

    }



     @PostMapping
     //参数 User user 用来接收前端的数据，如果是json格式的数据需要加上@RequestBody才能解析。
     public User create(@Valid @RequestBody User user, BindingResult errors){

         if(errors.hasErrors()){
             errors.getAllErrors().stream().forEach(error->System.out.println(error.getDefaultMessage()));
         }

         System.out.println(user);

         user.setId("1");
         // User{id='1', username='tom', password='null', birthday=Sun May 03 15:38:09 CST 2020}
         System.out.println(user);

         return user;

     }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "查询所有用户的服务")//用来描述方法
    //UserQueryCondition 用来封装请求的参数的值
    public List<User> query(UserQueryCondition condition){ // @PageableDefault(page = 2,size = 15 ,sort ="age,asc")Pageable pageable(使用Spring Data 可以使用此参数)

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());

        System.out.println("调用了query（）方法。。。。。。。。。。");

        return users;
    }

    @GetMapping(value = "/{id:\\d+}") //   （\\d+ 表示只能接受数字）
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户的ID") @PathVariable(required = true) String id){// (String id)名称必须和{id}请求路径上参数名称一致
         System.out.println("后端——————getInfo（）方法。。。。。。。。。。");
          User user = new User();
          user.setUsername("tom");
        System.out.println("后端——————getInfo（）方法。。。。。。。结束。。。");
          return user;
//           throw  new  UserNotExistException(id);


    }
}
