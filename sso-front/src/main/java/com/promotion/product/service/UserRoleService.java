package com.promotion.product.service;

import com.promotion.product.dao.dataobject.UserRoleDo;
import com.promotion.product.dao.mysql.UserRoleDao;
import com.promotion.product.entity.UserRoleDto;
import lombok.var;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;


    public List<UserRoleDto> queryByUserMobile(String mobile){
        List<UserRoleDto> list =new ArrayList<>();
        List<UserRoleDo> userRoleDos = userRoleDao.queryTemplate(mobile);
        for (UserRoleDo userRoleDo : CollectionUtils.emptyIfNull(userRoleDos)) {
            UserRoleDto userRoleDto =new UserRoleDto();
            BeanUtils.copyProperties(userRoleDo,userRoleDto);
            list.add(userRoleDto);
        }
        return list;
    }

    public Boolean add(UserRoleDto userRoleDto){
       // userRoleDao.add();
        return  false;
    }

}
