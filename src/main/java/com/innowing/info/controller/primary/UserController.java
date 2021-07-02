package com.innowing.info.controller.primary;

import com.alibaba.fastjson.JSONObject;
import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.User;
import com.innowing.info.service.primary.RoleService;
import com.innowing.info.service.primary.UserService;
import com.innowing.info.util.JwtTokenUtils;
import com.innowing.info.vo.LoginBodyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 22:59 2020/3/5
 * @ Description：userController
 * @ Modified By：
 * @Version: 1.0
 */
@RestController
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private RoleService roleService;
    @Resource
    private JwtTokenUtils jwtToken;

    /**
     * user register
     * @param user
     * @return
     */
    @PostMapping("create")
    @ResponseBody
    ServerResponse userRegister(@RequestBody User user){
        return userService.userRegister(user);
    }


    @PostMapping("login")
    ServerResponse userLogin(@RequestBody LoginBodyVo loginBodyVo){
        try {
            User currentUser = userService.validateUser(loginBodyVo);
            if (currentUser != null) {
                String token = jwtToken.generateToken(currentUser);
                return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_SUCCESS).data("token", token).code(20000);
            } else {
                return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_FAILED);
            }
        } catch (Exception e) {
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ACCOUNT_NOT_LOGIN);
        }
    }

    @GetMapping("info")
    public ServerResponse getUserInfo(@RequestParam String token){
//        String token = request.getParameter("token");
//        return userService.getUserInfoByToken(token);
        String username = jwtToken.getUsernameFromToken(token);
        User currentUser = userService.selectUserByUserName(username);
        //List<String> roleList = new ArrayList<>();
        if (currentUser != null) {
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).
                    data("roles", "admin").
                    data("name", currentUser.getUsername()).
                    data("avatar", currentUser.getAvatar());
        } else {
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    @ResponseBody
    public ServerResponse logout() {
        return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGOUT_SUCCESS);
    }


    @PostMapping("/info/update")
    ServerResponse updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/info/updatePassword")
    ServerResponse updatePassword(@RequestBody JSONObject jsonObject){
        return userService.updateUserPassword(jsonObject);
    }
}
