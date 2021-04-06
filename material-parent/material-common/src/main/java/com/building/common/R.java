package com.building.common;

import com.building.enums.REnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * ResultMassage  返回值
 *
 * @author yinjiahui
 * @create 2021-04-05 11:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {

    private Integer code;//返回值

    private String msg;//提示

    private Object data;//返回数据


    /**
     * 返回的没有参数  只有code和提示信息
     * @param rEnum
     * @return
     */
    public static R result (REnum rEnum){
        return new R(rEnum.getCode(),rEnum.getMsg(),null);
    }

    /**
     * 全都返回
     * @param rEnum
     * @param data
     * @return
     */
    public static R result (REnum rEnum,Object data){
        return new R(rEnum.getCode(),rEnum.getMsg(),data);
    }

    /**
     * 返回错误信息
     * @param msg
     * @return
     */
    public static R error (String msg){

        return new R(1,msg,null);
    }
    /**
     * 返回成功信息
     * @param msg
     * @return
     */
    public static R success (String msg){

        return new R(0,msg,null);
    }
}
