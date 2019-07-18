package lt.bit.todos.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import lt.bit.todos.dao.TodosDAO;
import lt.bit.todos.dao.UsersDAO;
import lt.bit.todos.db.Todos;
import lt.bit.todos.db.Users;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractAuditable_.createdDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kostia
 */
@Controller
@RequestMapping("/")

public class TodosController {

    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private TodosDAO todosDAO;

    @GetMapping
    @PostMapping
    public ModelAndView index(Principal principal) {

        ModelAndView mav = new ModelAndView("index");
        List<Users> users = usersDAO.findByName(principal.getName());
        if (users.size() > 0) {
            Users user = users.get(0);
            user.getTodosList();
            mav.addObject("todos", user.getTodosList());
        } else {
            mav.addObject("todos", new ArrayList());
        }
        return mav;
    }

    @Transactional
    @PostMapping("savetodo")
    public String savetodo(Principal principal,
            @RequestParam("todoText") String todoText) {
        List<Users> users = usersDAO.findByName(principal.getName());
        Users user = users.get(0);
        Todos todo = new Todos();
        todo.setUserId(user);
        todo.setTodoText(todoText);
        todo.setCreatedDate(new Date());
        todosDAO.save(todo);

        return "redirect:/";
    }

    @Transactional
    @GetMapping("deletetodo")
    public String deletetodo(Principal principal,
            @RequestParam("id") Integer id) {
        List<Users> users = usersDAO.findByName(principal.getName());
        Users user = users.get(0);
        try {

            Todos todo = todosDAO.getOne(id);
            if (user.getId().equals(todo.getUserId().getId())) {
                todosDAO.delete(todo);
            }
        } catch (Exception ex) {
        }
        return "redirect:/";
    }

    @Transactional
    @PostMapping("edittodo")
    @RequestMapping(method = RequestMethod.GET, path = "edittodo")
    public ModelAndView edit(@RequestParam(required = false) Integer id) {
        ModelAndView maw = new ModelAndView("editTodo");
        if (id != null) {
            maw.addObject("todos", todosDAO.getOne(id));
        }
        return maw;
    }

    @RequestMapping(method = RequestMethod.POST, path = "save")

    public String save(Principal principal, @RequestParam(required = false) Integer id, @RequestParam Integer UserId, @RequestParam String Text,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date findate) {
        List<Users> users = usersDAO.findByName(principal.getName());
        Users user = users.get(0);
        Todos todo = null;
        if (id != null) {
            todo = todosDAO.getOne(id);
            if (todo == null) {
                return "redirect:/";
            }
        } else {
            todo = new Todos();
        }
        todo.setTodoText(Text);
        todo.setCreatedDate(date);
        todo.setFinishedDate(findate);
        todo.setUserId(user);
        todo.setId(id);
        todosDAO.save(todo);
        return "redirect:/";
    }

    @Transactional
    @GetMapping("complete")
    public String complete(Principal principal,
            @RequestParam("id") Integer id) {
        List<Users> users = usersDAO.findByName(principal.getName());
        Users user = users.get(0);
        try {

            Todos todo = todosDAO.getOne(id);

            if (user.getId().equals(todo.getUserId().getId())) {

                todo.setFinishedDate(new Date());
                todosDAO.save(todo);
            }
        } catch (Exception ex) {
        }

        return "redirect:/";
    }
//    
//        @Transactional
//    @PostMapping("edittodo")
//    public String edittodo (Principal principal,
//            @RequestParam("todoText") String todoText) {
//        List<Users> users = usersDAO.findByName(principal.getName());
//        Users user = users.get(0);
//        Todos todo = new Todos();
//        todo.setUserId(user);
//        todo.setTodoText(todoText);
//        todo.setCreatedDate(new Date());
//        todosDAO.save(todo);
//
//        return "redirect:/";
}

//        @Transactional
//    @GetMapping("edittodo")
//    public String edittodo(Principal principal,
//            @RequestParam("id") Integer id) {
//        List<Users> users = usersDAO.findByName(principal.getName());
//        Users user = users.get(0);
//        try{
//            
//       
//        Todos todo = todosDAO.getOne(id);
//        if(user.getId().equals(todo.getUserId().getId()) ){
//        todosDAO.edit(todo);
//    return "todo";
//}
//       }catch(Exception ex){}
//      return "redirected:/";
//    }
//    @RequestMapping(method = RequestMethod.GET, path = "edittodo")
//    public ModelAndView edit(@RequestParam("id") Integer id) {
//        ModelAndView maw = new ModelAndView("edittodo");
//        if (id != null) {
//            maw.addObject("edittodo", usersDAO.getOne(id));
//        }
//        return maw;
//    }

