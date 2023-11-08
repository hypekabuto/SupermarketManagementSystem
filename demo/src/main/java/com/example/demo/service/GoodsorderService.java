package com.example.demo.service;

import com.example.demo.entity.Goodsorder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.vo.ShowMain;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjy
 * @since 2023-04-02
 */
public interface GoodsorderService extends IService<Goodsorder> {

    List<ShowMain> mainShow();

    Integer selectNum();
    Integer selectNoNum();
    Integer selectMouthNum();
    Integer selectNoMouthNum();
    List<String> selectEcharts();
    List<String> selectEchartsTwo();
}
