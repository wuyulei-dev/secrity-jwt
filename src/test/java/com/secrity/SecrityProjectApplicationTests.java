package com.secrity;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.secrity.entity.SysUser;
import com.secrity.mapper.MenuMapper;
import com.secrity.mapper.UserMapper;


@SpringBootTest
class SecrityProjectApplicationTests {

    @Autowired
    private MenuMapper menuMapper;
    
    @Autowired
    private UserMapper userMapper;
    
	@Test
	void contextLoads() {
	    List<String> permissions = menuMapper.selectPermsByUserid("1");
	    System.out.println(permissions);
	    System.out.println("asdfsdf");
	}
	
	@Test
    void test2() {
	    List<SysUser> users = userMapper.selectList(null);
        System.out.println(users);
        
    }

}
