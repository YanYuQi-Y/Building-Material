package com.building.config;

import com.building.common.R;
import com.building.enums.REnum;
import com.building.exception.MaterialException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * springMvc统一异常处理
 *
 * @author yinjiahui
 * @create 2021-04-05 11:12
 */
@RestControllerAdvice
@Slf4j
public class SpringMvcAdviceException {
    /**
     * 全局异常处理
     * @param e
     */
    @ExceptionHandler(Exception.class)
    public R exception(Exception e){
        log.error("最大的异常统一处理",e);
        return R.result(REnum.ERROR_SYS);
    }

    /**
     * 自定义异常处理
     * @param foodException
     */
    @ExceptionHandler(MaterialException.class)
    public R FoodException(MaterialException foodException){
        log.info(foodException.getMessage());

        System.out.println("自定义异常统一处理");
        return R.error(foodException.getMessage());
    }

    /**
     * jsr303校验异常处理
     * @param BindException
     * @return
     */
    @ExceptionHandler(BindException.class)
    public String BindException(BindException BindException){
        return BindException.getBindingResult().getFieldError().getDefaultMessage();
    }
}
