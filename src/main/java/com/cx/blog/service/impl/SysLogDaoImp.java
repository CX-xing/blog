package com.cx.blog.service.impl;

import com.cx.blog.dao.jpa.SysLogRepository;
import com.cx.blog.entity.SysLog;
import com.cx.blog.service.SysLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author :  C__xing
 * @Date :  2019/12/12
 */
@Repository
public class SysLogDaoImp implements SysLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SysLogRepository sysLogRepository;

    @Override
    public void saveSysLog(SysLog syslog) {
//        StringBuffer sql = new StringBuffer("insert into sys_log ");
//        sql.append("(id,username,operation,time,method,params,ip,create_time) ");
//        sql.append("values(seq_sys_log.nextval,:username,:operation,:time,:method,");
//        sql.append(":params,:ip,:createTime)");
//
//        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
//        npjt.update(sql.toString(), new BeanPropertySqlParameterSource(syslog));
        sysLogRepository.save(syslog);
    }
}
