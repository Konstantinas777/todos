/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.todos.dao;

import java.util.List;
import lt.bit.todos.db.Todos;
import lt.bit.todos.db.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Kostia
 */
public interface UsersDAO  extends JpaRepository<Todos, Integer> {
    @Query(name ="Users.findByUserName")
    public List<Users> findByName(@Param("userName")String name);
}
