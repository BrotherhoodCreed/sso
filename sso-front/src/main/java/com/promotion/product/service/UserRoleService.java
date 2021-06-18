package com.promotion.product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.promotion.product.dao.dataobject.FineUserDo;
import com.promotion.product.dao.dataobject.UserRoleDo;
import com.promotion.product.dao.mysql.UserRoleDao;
import com.promotion.product.dao.mysql2.FineUserDao;
import com.promotion.product.entity.BasePageResponse;
import com.promotion.product.entity.PermissionDTO;
import com.promotion.product.entity.UserRoleDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private FineUserDao fineUserDao;


    /**
     * 根据用户手机号查询用户权限
     */
    public List<UserRoleDto> queryListByUserMobile(String mobile) {
        List<UserRoleDo> userRoleDoList = userRoleDao.queryList(mobile, null);
        return CollectionUtils.emptyIfNull(userRoleDoList).stream().map(i -> {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setRoleCode(i.getRoleCode());
            userRoleDto.setUserName(i.getUserName());
            userRoleDto.setUserMobile(i.getUserMobile());
            userRoleDto.setRoleDesc(i.getRoleDesc());
            return userRoleDto;
        }).collect(Collectors.toList());
    }


    /**
     * 用户权限列表查询
     */
    public BasePageResponse<PermissionDTO> queryByUserMobile(String mobile, int pageSize, int pageIndex) {
        BasePageResponse<PermissionDTO> basePageResponse = BasePageResponse.success(BasePageResponse.class);
        List<PermissionDTO> list = new ArrayList<>();
        PageHelper.startPage(pageIndex, pageSize);
        List<FineUserDo> fineUserDo =new ArrayList<>();
        if(StringUtils.isNotBlank(mobile)){
            fineUserDo = fineUserDao.queryListWhere(mobile);
        }
        else {
            fineUserDo=fineUserDao.queryList();
        }
        PageInfo<FineUserDo> page = new PageInfo<FineUserDo>(fineUserDo);
        List<String> roleMobileList = CollectionUtils.emptyIfNull(fineUserDo).stream().map(FineUserDo::getMobile).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(roleMobileList)){
            return basePageResponse;
        }
        List<UserRoleDo> userRoleDoList = userRoleDao.queryMobileList(roleMobileList);
        if(CollectionUtils.isNotEmpty(fineUserDo)){
            for (FineUserDo userDo : fineUserDo) {
                PermissionDTO userRoleDto = new PermissionDTO();
                userRoleDto.setMobile(userDo.getMobile());
                userRoleDto.setName(userDo.getUserName());
                UserRoleDo userRoleDo = CollectionUtils.emptyIfNull(userRoleDoList).stream().filter(t -> StringUtils.equals(t.getUserMobile(), userDo.getMobile())).findFirst().orElse(null);
                if(userRoleDo!=null){
                    userRoleDto.setId(userRoleDo.getId());
                    if(StringUtils.isNotBlank(userRoleDo.getRoleCode())){
                        userRoleDto.setRoleCodes(Arrays.asList( userRoleDo.getRoleCode().split(",")));
                    }
                    userRoleDto.setRoledesc(userRoleDo.getRoleDesc());
                }
                list.add(userRoleDto);
                }
        }
//        List<UserRoleDo> userRoleDoList = userRoleDao.queryList(mobile, null);
//        PageInfo<UserRoleDo> page = new PageInfo<UserRoleDo>(userRoleDoList);
//        for (UserRoleDo userRoleDo : CollectionUtils.emptyIfNull(userRoleDoList)) {
//            PermissionDTO userRoleDto = new PermissionDTO();
//            userRoleDto.setId(userRoleDo.getId());
//            userRoleDto.setMobile(userRoleDo.getUserMobile());
//            if(StringUtils.isNotBlank(userRoleDo.getRoleCode())){
//                userRoleDto.setRoleCodes(Arrays.asList( userRoleDo.getRoleCode().split(",")));
//            }
//            userRoleDto.setName(userRoleDo.getUserName());
//            userRoleDto.setRoledesc(userRoleDo.getRoleDesc());
//            list.add(userRoleDto);
//        }
        basePageResponse.setData(list);
        basePageResponse.setTotal(page.getTotal());
        basePageResponse.setPages(pageIndex);
        return basePageResponse;
    }


    /**
     * 添加用户权限
     */
    @Transactional
    public Boolean add(String name, String mobile,String permission,String roleDesc) {
        List<UserRoleDo> userRoleDoList = userRoleDao.queryList(mobile, name);
        if (CollectionUtils.isNotEmpty(userRoleDoList)) {
          return  false;
        }
        UserRoleDo userRoleDo = new UserRoleDo();
        userRoleDo.setRoleCode(permission);
        userRoleDo.setUserName(name);
        userRoleDo.setUserMobile(mobile);
        userRoleDo.setRoleDesc(roleDesc);
        userRoleDao.add(userRoleDo);
        return true;
    }

    /**
     * 修改用户权限
     */
    @Transactional
    public Boolean update(Integer id, String permission,String roleDesc) {
        UserRoleDo userRoleDo = userRoleDao.queryById(id,null);
        if (Objects.isNull(userRoleDo)) {
            return false;
        }
        userRoleDao.updateById(userRoleDo.getId(),permission,roleDesc);
        return true;
    }

    public UserRoleDo queryById(Integer id) {
        List<UserRoleDo> userRoleDoList = userRoleDao.queryByIds(id);
        UserRoleDo userRoleDo = CollectionUtils.emptyIfNull(userRoleDoList).stream().findFirst().orElse(null);
        return userRoleDo;
    }

    public UserRoleDo queryByMobile(String mobile) {
        UserRoleDo userRoleDo = userRoleDao.queryById(null,mobile);
        return userRoleDo;
    }


}
