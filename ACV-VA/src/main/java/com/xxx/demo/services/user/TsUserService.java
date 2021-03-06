package com.xxx.demo.services.user;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.sys.SysUser;
import com.xxx.demo.models.sys.TsUser;

/**
 * 用户信息
 *
 * @author leo
 */
public interface TsUserService {

    /**
     * 根据id查找用户
     *
     * @param id 用户主键
     * @return
     */
    TsUser findById(String id);

    /**
     * 根据用户电话号查找用户
     *
     * @param phoneNum 用户电话号
     * @return
     */
    TsUser findEffctiveByPhoneNum(String phoneNum);

    /**
     * 保存用户
     *
     * @param bean 用户bean
     */
    void save(TsUser bean);

    /**
     * 删除用户
     *
     * @param userIds 用户主键数组
     */
    void delete(String[] userIds);

    /**
     * 更新用户（密码不用更新）
     *
     * @param bean 用户bean
     */
  //  void update(TsUser bean);
//
//    /**
//     * 修改用户密码
//     *
//     * @param userId      用户主键
//     * @param oldPassword 旧密码
//     * @param newPassword 新密码
//     */
//    void updatePassword(String userId, String oldPassword, String newPassword);

    /**
     * 修改用户密码
     *
     * @param phoneNum     用户电话号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(String phoneNum, String oldPassword, String newPassword);

//    /**
//     * 重置用户密码
//     *
//     * @param userId
//     * @param newPassword
//     */
//    void reSetPassword(String userId, String newPassword);

    /**
     * 重置用户密码
     *
     * @param phoneNum
     * @param newPassword
     */
    void reSetPassword(String phoneNum, String newPassword);


    /**
     * 注册用户
     * @param phoneNum    用户名
     * @param password    密码
     * @param token       备用字段（与Kamereon系统集成时使用）
     * @return 返回信息
     */
    JSONObject registeredUser(String phoneNum,String password, String token);

    /**
     * 更新用户信息
     *
     * @param userId      用户主键
     * @param userName    用户名
     * @param realName    真实姓名
     * @param cellphone   手机号
     * @param emodelId    区域id
     * @param email       邮箱
     * @param description 描述
     * @return 返回信息
     */
    //JSONObject modifyUserInfo(String userId, String userName, String realName, String cellphone, String emodelId, String email, String description);

    /**
     * 更新用户密码
     *
     * @param userId      用户主键
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 返回信息
     */
    //JSONObject modifyUserPassword(String userId, String oldPassword, String newPassword);

    /**
     * 重置密码
     *
     * @param phoneNum    用户电话号
     * @param newPassword 新密码
     * @return 返回信息
     */
    JSONObject resetUserPassword(String phoneNum, String newPassword);
}

