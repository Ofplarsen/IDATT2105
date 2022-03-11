package edu.ntnu.oflarsen.backendcrud.repository;

import edu.ntnu.oflarsen.backendcrud.model.Tutorial;
import edu.ntnu.oflarsen.backendcrud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcUserRepository implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO tutorials (title, description, published) VALUES(?,?,?)",
                new Object[] { user.getUsername(), user.getPassword() });
    }


    @Override
    public User findById(Long id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                    BeanPropertyRowMapper.newInstance(User.class), username);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from users", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from users");
    }
}
