package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class UserBaseInfo {
    private Integer errcode;
    private UserInfo user_info;
    private String errmsg;

    /**
     * 用户信息
    */
    class  UserInfo{
        /**
         * 用户在钉钉上面的昵称。
         */
        private String nick;
        /**
         * 用户在钉钉上面的昵称。
         */
        private String unionid;
        /**
         * 用户在当前开放应用所属企业的唯一标识。
         */
        private String openid;
        /**
         *用户主企业是否达到高级认证级别。
         */
        private String main_org_auth_high_level;

    }
}
