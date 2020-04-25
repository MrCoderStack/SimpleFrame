package com.mrcoder.frameservice.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mrcoder.framecommon.auth.AuthCheck;
import com.mrcoder.framecommon.excel.ExcelModelListener;
import com.mrcoder.framecommon.excel.ExcelUtil;
import com.mrcoder.framecommon.model.ResponseInfo;
import com.mrcoder.frameservice.model.po.User;
import com.mrcoder.frameservice.model.po.UserModel;
import com.mrcoder.frameservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;


@RestController
@Api(tags = "测试模块")
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);


    /*
     * 这是一个导入excel的方式
     *
     * 通用Listener，读取Excel反射任意Model，无需每个Model单独写Listener
     * 不过对于读取的数据进行处理的话，需要再次循环处理
     *
     * */
    @ApiOperation("excel通用导入1")
    @ApiParam(name = "filename", value = "传入json格式", required = true)
    @AuthCheck(loginRequired = true)
    @PostMapping("/importUsers1")
    public ResponseInfo importUsers(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile skuExcel = multipartRequest.getFile("filename");
        InputStream inputStream = skuExcel.getInputStream();
        ExcelModelListener excelModelListener = new ExcelModelListener();
        EasyExcelFactory.read(inputStream, UserModel.class, excelModelListener).sheet(0).doRead();
        //service处理数据 excelModelListener.getDataList()
        return ResponseInfo.success(excelModelListener.getDataList());
    }


    /*
     * 这是另一个导入excel的方式
     *
     * 和上面的区别就是，使用JDK8的lambda表达式， 可以传入一个处理Excel数据的方法
     * 具体使用哪个，看个人需要
     *
     * */
    @ApiOperation("excel通用导入2")
    @ApiParam(name = "filename", value = "传入json格式", required = true)
    @AuthCheck(loginRequired = true)
    @PostMapping("/importUsers2")
    public ResponseInfo importUsers2(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile skuExcel = multipartRequest.getFile("filename");
        InputStream inputStream = skuExcel.getInputStream();
        AnalysisEventListener<User> userAnalysisEventListener = ExcelUtil.getListener(batchInsert());
        EasyExcel.read(inputStream, User.class, userAnalysisEventListener).sheet().doRead();
        return ResponseInfo.success();
    }

    private Consumer<List<User>> batchInsert() {
        //注入对数据的处理方法
        return users -> userService.dealUserList(users);
    }

}
