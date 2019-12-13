package com.aaa.service;

import com.aaa.dao.UsersDao;
import com.aaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UsersService {

    @Resource
    UsersDao usersDao;

    public Users selectByName(String uname){
        Users u = new Users();
        u.setUname(uname);
        return usersDao.selectOne(u);
    }
}
