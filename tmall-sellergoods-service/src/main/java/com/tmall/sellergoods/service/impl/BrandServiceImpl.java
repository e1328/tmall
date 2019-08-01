package com.tmall.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tmall.mapper.BrandMapper;
import com.tmall.pojo.Brand;
import com.tmall.pojo.BrandExample;
import com.tmall.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: tmall-parent
 * @description: BrandServiceImpl
 * @author: wenlongzhou
 * @create: 2019-07-25 12:39
 **/

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Brand> page = (Page<Brand>) brandMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public Brand findOne(int id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(int[] ids) {
        for(int id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void delete(int id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(Brand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        BrandExample example=new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        if(brand!=null){
            if(brand.getName()!=null && brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if(brand.getFirst_char()!=null && brand.getFirst_char().length()>0){
                criteria.andFirst_charEqualTo(brand.getFirst_char());
            }
        }
        Page<Brand> page = (Page<Brand>) brandMapper.selectByExample(example);
        System.out.println(page.getTotal());
        return new PageResult(page.getTotal(),page.getResult());
    }

}
