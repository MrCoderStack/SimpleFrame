package com.mrcoder.frameservice.controller;

import com.mrcoder.framecommon.auth.AuthCheck;
import com.mrcoder.framecommon.model.QueryBase;
import com.mrcoder.framecommon.model.ResponseInfo;
import com.mrcoder.frameservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "测试模块")
@RequestMapping("user")
public class Controller {

    @Autowired
    private UserService userService;

    @ApiOperation("单条")
    @ApiParam(name = "query对象", value = "传入json格式", required = true)
    @AuthCheck(loginRequired = true)
    @GetMapping("/queryUserById/{userId}")
    public ResponseInfo queryUserById(@PathVariable Long userId) {
        return ResponseInfo.success(userService.queryUserById(userId));
    }

    @ApiOperation("列表")
    @ApiParam(name = "query对象", value = "传入json格式", required = true)
    @AuthCheck(loginRequired = true)
    @PostMapping("/queryUserList")
    public ResponseInfo queryUserList(@RequestBody QueryBase queryBase) {
        return ResponseInfo.success(userService.queryUserList(queryBase));
    }


}
