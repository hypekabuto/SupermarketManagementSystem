package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.Goodsorder;
import com.example.demo.entity.User;
import com.example.demo.mapper.GoodsorderMapper;
import com.example.demo.service.GoodsorderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjy
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/goodsOrder")
public class GoodsorderController {
    @Autowired
    private GoodsorderService goodsorderService;
    @Autowired
    private GoodsorderMapper goodsorderMapper;
    @GetMapping("/list")
    public List<Goodsorder> list(){
        return goodsorderService.list();
    }
    @GetMapping("/mainShow")
    public Result mainShow(){
        return Result.success(goodsorderService.mainShow());
    }
    @GetMapping("/selectNum")
    public Result selectNum(){
        return Result.success((goodsorderService.selectNum()));
    }
    @GetMapping("/selectMouthNum")
    public Result selectMouthNum(){
        return Result.success((goodsorderService.selectMouthNum()));
    }
    @GetMapping("/selectNoMouthNum")
    public Result selectNoMouthNum(){
        return Result.success((goodsorderService.selectNoMouthNum()));
    }
    @GetMapping("/selectNoNum")
    public Result selectNoNum(){
        return Result.success((goodsorderService.selectNoNum()));
    }
    @GetMapping("/selectEcharts")
    public Result selectEcharts(){
        return Result.success((goodsorderService.selectEcharts()));
    }
    @GetMapping("/selectEchartsTwo")
    public Result selectEchartsTwo(){
        return Result.success((goodsorderService.selectEchartsTwo()));
    }
    @GetMapping("/selectGoodsOrder")
    public Result selectGoodsOrder(){
        return Result.success(goodsorderMapper.selectList(null));
    }
    @PostMapping("/selectGoodsOrderByPage")
    public IPage<Goodsorder> selectGoodsOrderByPage(@RequestBody QueryPageParam data){
        Page<Goodsorder> page = new Page<>(data.getPageNum(), data.getPageSize());
        if(data.getParam() == null){
            System.out.println("执行了上方");
            return goodsorderMapper.selectPage(page, null);
        }else {
            System.out.println("执行了下方");
            QueryWrapper<Goodsorder> wrapper = new QueryWrapper<>();
            wrapper.allEq(data.getParam());
            return goodsorderMapper.selectPage(page,wrapper);
        }
    }
    @PostMapping("/addGoodsOrder")
    public void addUser(@RequestBody Goodsorder goodsorder){
        goodsorderMapper.insert(goodsorder);
    }
    @PostMapping("/updateGoodsOrder")
    public void updateUser(@RequestBody Goodsorder goodsorder){
        goodsorderMapper.updateById(goodsorder);
    }
    @DeleteMapping("/deleteGoodsOrder")
    public void deleteGoodsOrder(@RequestParam("orderid") String orderid){
        goodsorderMapper.deleteById(Long.parseLong(orderid));
    }
}
