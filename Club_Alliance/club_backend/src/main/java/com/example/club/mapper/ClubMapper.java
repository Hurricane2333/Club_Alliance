package com.example.club.mapper;

import com.example.club.entity.Club;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClubMapper {
    Club selectById(int id);
}