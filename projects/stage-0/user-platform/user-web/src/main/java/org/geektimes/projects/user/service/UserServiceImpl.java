package org.geektimes.projects.user.service;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl() {
        final DataSource dataSource;
        DBConnectionManager dbConnectionManager = null;
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/UserPlatformDB");
            dbConnectionManager = new DBConnectionManager(dataSource);
        } catch (Exception e){
            e.printStackTrace();
        }
        userRepository = new DatabaseUserRepository(dbConnectionManager);
    }

    @Override
    public boolean register(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return userRepository.getByNameAndPassword(name, password);
    }
}
