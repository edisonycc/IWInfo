package com.innowing.info.service;

import com.alibaba.fastjson.JSONObject;
import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.User;
import com.innowing.info.repository.UserRepository;
import com.innowing.info.vo.LoginBodyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @ Author     ：Negen
 * @ Date       ：Created in 21:06 2020/3/5
 * @ Description：用户业务实现类
 * @ Modified By：
 * @Version: 1.0
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ServerResponse userRegister(User user) {
        try {
            if(null == user.getUsername() || null==user.getPassword())
                return  ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            // user exist
            if(null != userRepository.findByUsername(user.getUsername()))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.USERNAME_EXIST);

            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.REGISTER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    // cannot save token in database
//    public ServerResponse getUserInfoByToken(String token) {
//        User user = userRepository.findByToken(token);
//        UserInfoVo userInfoVo = new UserInfoVo();
//        userInfoVo.setId(user.getId());
//        userInfoVo.setName(user.getUserName());
//        userInfoVo.setAvatar(user.getAvatar());
//        //userInfoVo.setIntroduction(user.getIntroduction());
//        userInfoVo.setRoles(new ArrayList());
//        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(userInfoVo).code(20000);
//    }


    public ServerResponse updateUserInfo(User user) {
        try {
            Long id = user.getId();
            //String introduction = user.getIntroduction();
            User cUser = userRepository.getOne(id);
//            if(!StringUtils.isEmpty(introduction)){
//                cUser.setIntroduction(introduction);
                userRepository.save(cUser);
//            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }



    public ServerResponse updateUserPassword(JSONObject object) {
        try {
            Long id = object.getLong("id");
            User cUser = userRepository.getOne(id);

            //旧密码
            String oldP = object.getString("oldP");
            String oldPStr = new BCryptPasswordEncoder().encode(oldP);
            //新密码
            String newP = object.getString("newP");
            String newPStr = new BCryptPasswordEncoder().encode(newP);

            if(! new BCryptPasswordEncoder().matches(oldP, cUser.getPassword())){
                //密码错误
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_FAILED);
            }else{
                cUser.setPassword(newPStr);
                userRepository.save(cUser);
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS).message("密码修改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public User validateUser(LoginBodyVo loginBodyVo) {
//        User user = new User();
//        user.setId(111L);
//        user.setPassword("123456");
//        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//        user.setSalt("abc");
        User user = userRepository.findByUsername(loginBodyVo.getUsername());
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        boolean isPasswordMatches = bcrypt.matches( loginBodyVo.getPassword(), user.getPassword());
        if (isPasswordMatches) return user;
        return null;
    }

    public User selectUserByUserName(String username) {
        User currentUser = userRepository.findByUsername(username);
        return currentUser;
    }
}
