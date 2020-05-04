package com.page.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {
     @Autowired
     private MockQueue mockQueue;
     @Autowired
     private DeferredResultHolder deferredResultHolder;

     private Logger logger  = LoggerFactory.getLogger(getClass());
    //这是同步的处理方式

//    @RequestMapping("/order")
//    public String order() throws InterruptedException {
//        logger.info("主线程开始");
//
//        Thread.sleep(1000);
//
//        logger.info("主线程返回");
//
//        return "success";
//    }



//    @RequestMapping("/order")
//    public Callable<String> order() throws InterruptedException {
//         logger.info("主线程开始");
//
//        //Callable实际上是单开一个线程，Runnable是Spring框架本身的一个简单的异步线程池来处理的，它不是一个真正的池，每次调用都会重开一个线程。
//        Callable<String>  result  =  new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                logger.info("副线程----开始");
//                Thread.sleep(1000);
//                logger.info("副线程----结束");
//                return "success";
//            }
//        };
//
//        logger.info("主线程返回");
//        return result;
//    }


    @RequestMapping("/order")
    public DeferredResult<String> order() throws Exception {
        logger.info("主线程开始mainStart");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        //DeferredResult<>异步处理REST服务
        DeferredResult<String> result  = new DeferredResult<>();

        deferredResultHolder.getMap().put(orderNumber,result);

        logger.info("主线程返回mainFinish");

        return result;
    }


}
