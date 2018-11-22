package com.zidane.springboot.stepbystep;

import com.zidane.springboot.stepbystep.model.AyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepbystepApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(StepbystepApplicationTests.class);

    @Resource // springboot会自动实例化一个JdbcTemplate对象装载进来
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
    }


    @Test
    public void mySqlTest() {
        String sql = "select id,name,password from test.ay_user"; // sql要么全大写要么全小写
        List<AyUser> userList = (List<AyUser>) jdbcTemplate.query(sql, new RowMapper<AyUser>() {

            @Override
            public AyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                AyUser user = new AyUser();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });

        logger.info("查询成功: ");
        for (AyUser user : userList) {
            logger.info("[id]: {}; [name]: {}", user.getId(), user.getName());
        }
    }


}
