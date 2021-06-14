package com.promotion.product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.promotion.product.dao.dataobject.UserRoleDo;
import com.promotion.product.dao.mysql.UserRoleDao;
import com.promotion.product.entity.BasePageResponse;
import com.promotion.product.entity.UserRoleDto;
import org.apache.commons.collections4.CollectionUtils;
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
            return userRoleDto;
        }).collect(Collectors.toList());
    }


    /**
     * 用户权限列表查询
     */
    public BasePageResponse<UserRoleDto> queryByUserMobile(String mobile, int pageSize, int pageIndex) {
        BasePageResponse<UserRoleDto> basePageResponse = BasePageResponse.success(BasePageResponse.class);
        List<UserRoleDto> list = new ArrayList<>();
        PageHelper.startPage(pageIndex, pageSize);
        List<UserRoleDo> userRoleDoList = userRoleDao.queryList(mobile, null);
        PageInfo<UserRoleDo> page = new PageInfo<UserRoleDo>(userRoleDoList);
        for (UserRoleDo userRoleDo : CollectionUtils.emptyIfNull(userRoleDoList)) {
            UserRoleDto userRoleDto = new UserRoleDto();
            BeanUtils.copyProperties(userRoleDo, userRoleDto);
            list.add(userRoleDto);
        }
        basePageResponse.setData(list);
        basePageResponse.setTotal(page.getTotal());
        basePageResponse.setPages(pageIndex);
        return basePageResponse;
    }


    /**
     * 添加用户权限
     */
    @Transactional
    public Boolean add(String name, String mobile,String permission) {
        List<UserRoleDo> userRoleDoList = userRoleDao.queryList(mobile, name);
        if (CollectionUtils.isNotEmpty(userRoleDoList)) {
            userRoleDao.delete(userRoleDoList.stream().map(UserRoleDo::getId).collect(Collectors.toList()));
        }
        UserRoleDo userRoleDo = new UserRoleDo();
        userRoleDo.setRoleCode(permission);
        userRoleDo.setUserName(name);
        userRoleDo.setUserMobile(mobile);
        userRoleDao.add(userRoleDo);
        return true;
    }

    /**
     * 修改用户权限
     */
    @Transactional
    public Boolean update(Integer id,String name, String mobile, String permission) {
        UserRoleDo userRoleDo = userRoleDao.queryById(id);
        if (Objects.isNull(userRoleDo)) {
            return false;
        }
        userRoleDao.updateById(userRoleDo.getId(),permission);
        return true;
    }

    public UserRoleDo queryById(Integer id) {
        List<UserRoleDo> userRoleDoList = userRoleDao.queryByIds(id);
        UserRoleDo userRoleDo = CollectionUtils.emptyIfNull(userRoleDoList).stream().findFirst().orElse(null);
        return userRoleDo;

    }

}
