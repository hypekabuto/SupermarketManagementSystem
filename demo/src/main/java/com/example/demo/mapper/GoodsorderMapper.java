package com.example.demo.mapper;

import com.example.demo.entity.Goodsorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.vo.ShowMain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjy
 * @since 2023-04-02
 */
@Mapper
public interface GoodsorderMapper extends BaseMapper<Goodsorder> {

    void createTmpTable(@Param("showMainTable") String showMainTable);
    void dropTmpTable(@Param("showMainTable") String showMainTable);
    List<ShowMain> selectMainMouthShow();
    List<ShowMain> selectMainTodayShow();
    Integer selectNum();
    Integer selectNoNum();
    Integer selectMouthNum();
    Integer selectNoMouthNum();
    List selectEcharts();
    List selectEchartsTwo();
}
