package com.tmall.service.impl;

import com.tmall.domain.Sort;
import com.tmall.repository.SortJpa;
import com.tmall.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private SortJpa sortJpa;

    @Override
    public List<Sort> findByParentId(int parent_id) {
        Sort sort = new Sort();
        sort.setParent_id(parent_id);
        Example<Sort> example = Example.of(sort);
        return sortJpa.findAll(example);
    }

    @Override
    public List<Sort> findAll() {
        return sortJpa.findAll();
    }

    @Override
    public void add(Sort sort) {
        sortJpa.saveAndFlush(sort);
    }

    @Override
    public Sort findById(int id) {
        return sortJpa.findById(id).get();
    }

    @Override
    public void update(Sort sort) {
        sortJpa.saveAndFlush(sort);
    }

    @Override
    public void deleteById(int id) {
        sortJpa.deleteById(id);
    }

}
