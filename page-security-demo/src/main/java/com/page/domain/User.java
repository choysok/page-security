package com.page.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.page.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.Past;
import java.util.Date;

public class User {
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};
    private String id;
    @MyConstraint(message = "这是自己定义的注解")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
