package com.building.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 *springboot swagger文档生成配置
 *
 * @author yinjiahui
 * @create 2021-04-05 10:51
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     * 使用示例:
     *
     *		@ApiModel(value = "类名",description = "描述")
     * 		public class User {
     * 			@ApiModelProperty(value = "属性名称",required = false,dataType = "Integer")
     * 		    //@ApiModelProperty(hidden = true)//表示这个字段是不需要显示的
     * 			private Integer userId;
     * 		}
     *
     *
     * 		@Api(tags = "控制器名称")
     *		public class UserController {
     *
     *			@RequestMapping("/test")
     *			@ResponseBody
     *			@ApiOperation(value = "方法作用", notes = "方法备注说明")
     *          @ApiImplicitParams({
     *              @ApiImplicitParam(paramType="header",name="username",dataType="String",required=true,value="用户的姓名",defaultValue="这个是默认值，可以不写"),
     *              @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码",defaultValue="这个是默认值，可以不写")
     *          })
     *          @ApiResponses({
     *              @ApiResponse(code=400,message="请求参数没填好"),
     *              @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
     *          })
     *			public void test(User user,Integer a) {
     *
     *			}
     *		}
     *
     */


    /**
     * 创建Rest Api描述
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    /**
     * 接口描述
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("建材测试接口文档").description("这只是一个测试文档").version("1.0").build();
    }

}
