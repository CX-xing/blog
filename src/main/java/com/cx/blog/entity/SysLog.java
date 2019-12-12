package com.cx.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author :  C__xing
 * @Date :  2019/12/12
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}
