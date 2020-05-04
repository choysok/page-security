package com.page.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener  implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    Logger logger  = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
               // 需要放在 独立的线程中 ，因为是无限循环，因为onApplicationEvent在服务器启动就执行。
                new Thread(()->{

                    while (true){

                        if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                            String orderNum = mockQueue.getCompleteOrder();
                            logger.info("返回订单处理结果："+orderNum);
                            deferredResultHolder.getMap().get(orderNum).setResult("place order success");
                            //清空Complete的值，不然会死循环
                            mockQueue.setCompleteOrder(null);
                        }else {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }).start();




    }

}
