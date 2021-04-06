package com.building.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * springboot全局事务配置(代替单个注解配置起来很繁琐)
 *
 * @author yinjiahui
 * @create 2021-04-05 11:10
 */
@Configuration
public class TransactionAdviceConfig {

    private final static int TX_METHOD_TIME_OUT = 5;//事务超时时间,单位秒,表示执行超过时间,直接回滚 -1表示永不超时

    /**
     *
     * 事务切点
     * 1.execution(): 			表达式主体(类似正则)
     * 2.第1个*号:  				方法返回类型
     * 3.com.fh.mall.service	表示切入点的包名
     * 4.第2个*号:				表示所有子包
     * 5.第3个*号:				表示所有方法名称
     * 6.(..)					表示所有方法返回值
     */
    private static final String POITCUT_EXPRESSION = "execution(* com.fh.service.impl.*.*(..))";//切点表达式

    @Autowired
    private TransactionManager transactionManager;//由于springboot中TransactionAutoConfiguration会自动装配事务管理器,所以我们可以直接注入事务管理器

    @Bean
    public TransactionInterceptor txadvice() {//配置通知

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();//配置事务管理规则

        Map<String, TransactionAttribute> nameMap = new HashMap<>(16);

        RuleBasedTransactionAttribute readOnlyRule = new RuleBasedTransactionAttribute();//只读事物,只查询
        readOnlyRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);//传播特性,support表示有事务则加入事务运行,没有事务可以不在事务中运行
        readOnlyRule.setReadOnly(true);//只读


        RuleBasedTransactionAttribute requireRule = new RuleBasedTransactionAttribute();//增、删、改操作的事务
        requireRule.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));//回滚策略,exception只要是异常都会回滚
        requireRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);//传播特性,required 有事务加入事务运行,没有事务则开启一个新事务

        requireRule.setTimeout(TX_METHOD_TIME_OUT);//设置事务失效时间，超过10秒,可根据hytrix，则回滚事务

        nameMap.put("add*", requireRule);
        nameMap.put("insert*", requireRule);
        nameMap.put("save*", requireRule);
        nameMap.put("update*", requireRule);
        nameMap.put("delete*", requireRule);
        nameMap.put("del*", requireRule);
        nameMap.put("remove*", requireRule);
        nameMap.put("betch*", requireRule);


        nameMap.put("get*", readOnlyRule);
        nameMap.put("query*", readOnlyRule);
        nameMap.put("find*", readOnlyRule);
        nameMap.put("select*", readOnlyRule);
        nameMap.put("count*", readOnlyRule);


        source.setNameMap(nameMap);

        return new TransactionInterceptor(transactionManager, source);//返回事务管理拦截器

    }

    /**
     * 设置配置切面(aspect)
     * 切点pointcut + 通知advice = 切面
     */
    @Bean
    public Advisor txAdviceAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

        pointcut.setExpression(POITCUT_EXPRESSION);//切点表达式

        return new DefaultPointcutAdvisor(pointcut, txadvice());//切点+通知

    }
}
