package com.example.demo.service.impl;

import com.example.demo.entity.Goodsorder;
import com.example.demo.entity.vo.ShowMain;
import com.example.demo.mapper.GoodsorderMapper;
import com.example.demo.service.GoodsorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjy
 * @since 2023-04-02
 */
@Service
public class GoodsorderServiceImpl extends ServiceImpl<GoodsorderMapper, Goodsorder> implements GoodsorderService {
    @Autowired
    private GoodsorderMapper goodsorderMapper;
    @Override
    public List<ShowMain> mainShow(){
        goodsorderMapper.createTmpTable("showMainTable");
        List<ShowMain> showMouthMain = goodsorderMapper.selectMainMouthShow();
        goodsorderMapper.dropTmpTable("showMainTable");
        List<ShowMain> showTodayMain =  goodsorderMapper.selectMainTodayShow();
        for(int i = 0;i < showMouthMain.size();i++) {
            for(int j = 0;j < showTodayMain.size();j++){
                if(showMouthMain.get(j).getTradeName().equals(showTodayMain.get(i).getTradeName())) {
                    showTodayMain.get(i).setMouthPrice(showMouthMain.get(j).getMouthPrice());
                }
            }
        }
        return showTodayMain;
    }
    @Override
    public Integer selectNum() {
        return goodsorderMapper.selectNum();
    }
    @Override
    public Integer selectNoNum() {
        return goodsorderMapper.selectNoNum();
    }
    @Override
    public Integer selectMouthNum() {
        return goodsorderMapper.selectMouthNum();
    }
    @Override
    public Integer selectNoMouthNum() {
        return goodsorderMapper.selectNoMouthNum();
    }
    @Override
    public List<String> selectEcharts() {
        return goodsorderMapper.selectEcharts();
    }
    @Override
    public List<String> selectEchartsTwo() {
        return goodsorderMapper.selectEchartsTwo();
    }
}
