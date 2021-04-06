package com.building.materialadmin.service.impl.BaseServiceImpl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yinjiahui
 * @create 2021-04-05 11:36
 */
@Service
public abstract class BaseServiceImpl <M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {

}
