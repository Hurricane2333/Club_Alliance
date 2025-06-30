package com.example.club;

import com.example.club.entity.Club;
import com.example.club.enums.ClubStatus;
import com.example.club.mapper.ClubMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ClubBackendApplicationTests {
    @Autowired  // 自动注入Mapper实例
    private ClubMapper mapper;

    @Test
    void testSelectRecommendedClubs() {
        List<Club> clubs = mapper.selectRecommendedClubs( 2,ClubStatus.APPROVED);  // 调用Mapper方法
        System.out.println("查询结果：" + clubs);  // 打印结果
    }
    @Test
    void contextLoads() {
    }

}
