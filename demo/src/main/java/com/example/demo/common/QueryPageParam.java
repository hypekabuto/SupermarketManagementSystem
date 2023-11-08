package com.example.demo.common;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Data
public class QueryPageParam {
    private static int PAGE_SIZE=8;
    private static int PAGE_NUM=1;
    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;
    private HashMap param;
}
