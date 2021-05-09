package com.mowa.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.mowa.JwtUtil;
import com.mowa.Result;
import com.mowa.user.pojo.UserInfo;
import com.mowa.enums.StatusCodeEnum;
import com.mowa.service.UserInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Api(value = "UserController")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserInfoController {

    @Autowired
    private UserInfoService userService;

    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "User条件分页查询",notes = "分页条件查询User方法详情",tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "User对象",value = "传入JSON数据",required = false) UserInfo user, @PathVariable int page, @PathVariable int size){
        //调用UserService实现分页条件查询User
        PageInfo<UserInfo> pageInfo = userService.findPage(user, page, size);
        return new Result(true, StatusCodeEnum.SUCCESS.getCode(),"查询成功",pageInfo);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "User分页查询",notes = "分页查询User方法详情",tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //调用UserService实现分页查询User
        PageInfo<UserInfo> pageInfo = userService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User条件查询",notes = "条件查询User方法详情",tags = {"UserController"})
    @PostMapping(value = "/search" )
    public Result<List<UserInfo>> findList(@RequestBody(required = false) @ApiParam(name = "User对象",value = "传入JSON数据",required = false) UserInfo user){
        //调用UserService实现条件查询User
        List<UserInfo> list = userService.findList(user);
        return new Result<List<UserInfo>>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID删除",notes = "根据ID删除User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用UserService实现根据主键删除
        userService.delete(id);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"删除成功");
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID修改",notes = "根据ID修改User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "User对象",value = "传入JSON数据",required = false) UserInfo user, @PathVariable Integer id){
        //设置主键值
        user.setId(id);
        //调用UserService实现修改User
        userService.update(user);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"修改成功");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User添加",notes = "添加User方法详情",tags = {"UserController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "User对象",value = "传入JSON数据",required = true) UserInfo user){
        //调用UserService实现添加User
        userService.add(user);
        return new Result(true,StatusCodeEnum.SUCCESS.getCode(),"添加成功");
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID查询",notes = "根据ID查询User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<UserInfo> findById(@PathVariable Integer id){
        //调用UserService实现根据主键查询User
        UserInfo user = userService.findById(id);
        return new Result<UserInfo>(true,StatusCodeEnum.SUCCESS.getCode(),"查询成功",user);
    }

    /***
     * 查询User全部数据
     * @return
     */
    @PreAuthorize( "hasAnyRole('admin','user')" )
    @ApiOperation(value = "查询所有User",notes = "查询所User有方法详情",tags = {"UserController"})
    @GetMapping
    public Result<List<UserInfo>> findAll(){
        //调用UserService实现查询所有User
        List<UserInfo> list = userService.findAll();
        return new Result<List<UserInfo>>(true, StatusCodeEnum.SUCCESS.getCode(),"查询成功",list) ;
    }

    @ApiOperation( value = "用户登录" ,notes = "用户登录",tags = {"UserController"})
    @GetMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
      UserInfo userInfo = userService.findByUserName(username);
        //创建令牌信息
      /*if (BCrypt.checkpw( password,userInfo.getPassword() )){
          Map<String,Object> tokenMap = new HashMap<>();
          tokenMap.put("role","USER");
          tokenMap.put("success","SUCCESS");
          tokenMap.put("username",username);

          String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSONUtil.toJsonStr(tokenMap),null);
          //把令牌信息存入到cookie
          Cookie cookie = new Cookie("Authorization",token);
          cookie.setDomain("localhost");
          cookie.setPath("/");
          response.addCookie(cookie);
          return Result.success( token );
      }*/
      if (password.equals( userInfo.getPassword() )){

          //创建令牌信息
          Map<String,Object> tokenMap = new HashMap<>();
          tokenMap.put("role","USER");
          tokenMap.put("success","SUCCESS");
          tokenMap.put("username",username);

          String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSONUtil.toJsonStr(tokenMap),null);
          //把令牌信息存入到cookie
          Cookie cookie = new Cookie("Authorization",token);
          cookie.setDomain("localhost");
          cookie.setPath("/");
          response.addCookie(cookie);return Result.success( token );
      }
      return Result.error( "用户名或密码错误" );
    }

    @ApiOperation( value = "根据用户名获取用户信息" ,notes = "根据用户名获取用户信息",tags = {"UserController"})
    @GetMapping("/findByUserName")
    public Result<UserInfo> findByUserName(@RequestParam("userName") String userName){
        return Result.success(this.userService.findByUserName(userName));
    }
}
