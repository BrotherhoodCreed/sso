package com.promotion.product.entity;

import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import lombok.Data;

import java.util.List;

@Data
public class UserDao {
    private Boolean active;
    private Boolean admin;
    private String avatar;
    private Boolean boss;
    private List<Long> deptIdList;
    private List<OapiV2UserGetResponse.DeptOrder> deptOrderList;
    private String email;
    private Boolean exclusiveAccount;
    private String exclusiveAccountType;
    private String extension;
    private Boolean hideMobile;
    private Long hiredDate;
    private String jobNumber;
    private List<OapiV2UserGetResponse.DeptLeader> leaderInDept;
    private String loginId;
    private String managerUserid;
    private String mobile;
    private String name;
    private String orgEmail;
    private Boolean realAuthed;
    private String remark;
    private List<OapiV2UserGetResponse.UserRole> roleList;
    private Boolean senior;
    private String stateCode;
    private String telephone;
    private String title;
    private OapiV2UserGetResponse.UnionEmpExt unionEmpExt;
    private String unionid;
    private String userid;
    private String workPlace;
    //用户权限
    private List<Integer> permission;
}
