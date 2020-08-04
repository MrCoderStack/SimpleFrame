package com.mrcoder.frameservice.controller;

import com.mrcoder.framecommon.model.ResponseInfo;
import com.mrcoder.frameservice.model.bo.MyThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.*;


@RestController
@RequestMapping("thread")
@Slf4j
public class ThreadController {

    @GetMapping("/extendThread")
    public ResponseInfo extendThread() throws ExecutionException, InterruptedException {
        Object lock = new Object();
        Thread t = new Thread(new MyThread(lock));
        t.setName("t1");
        t.start();

        Thread t2 = new Thread(new MyThread(lock));
        t2.setName("t2");
        t2.start();



        return ResponseInfo.success();
    }


}
