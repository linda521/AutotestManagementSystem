package com.crc.controller;

import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.crc.bean.JsonResult;
import com.crc.bean.User;
import com.crc.service.UserService;

@RestController
@ComponentScan({"com.crc.service"})
@MapperScan("com.crc.mapper")
public class UserController {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
  
  @Autowired
  private UserService userService;
  
  @RequestMapping("/user")
  public String index() {
      return "Hello Tester";
  }

  @RequestMapping(value = "/user/{id}",method=RequestMethod.GET)
  public ResponseEntity<JsonResult> getUserById(@PathVariable(value = "id") Integer id){
    JsonResult r = new JsonResult();
    try {
      User user = userService.getUserById(id);
      r.setResult(user);
      r.setStatus("ok");
    }catch(Exception e) {
      r.setResult(e.getClass().getName() + ":" + e.getMessage());
      r.setStatus("error");
      e.printStackTrace();
    }
    return ResponseEntity.ok(r);
  }
  
  @RequestMapping(value="/user/alluser", method=RequestMethod.GET)
  public ResponseEntity<JsonResult> getUserList(){
    JsonResult r = new JsonResult();
    try {
      List<User> users = userService.getUserList();
      r.setResult(users);
      r.setStatus("ok");
    }catch(Exception e){
      r.setResult(e.getClass().getName() + ":"+e.getMessage());
      r.setStatus("error");
      e.printStackTrace();
    }
    return ResponseEntity.ok(r);
  }
  
  @RequestMapping(value="/user/add", method=RequestMethod.POST)
  public ResponseEntity<JsonResult> add(@RequestBody User user){
    JsonResult r = new JsonResult();
    try {
      int orderId = userService.add(user);
      if(orderId <0) {
        r.setResult(orderId);
        r.setStatus("fail");
      }else {
        r.setResult(orderId);
        r.setStatus("ok");
      }
    }catch(Exception e) {
      r.setResult(e.getClass().getName() + ":"+e.getMessage());
      r.setStatus("error");
      e.printStackTrace();
    }
    return ResponseEntity.ok(r);
  }
  
  @RequestMapping(value = "/user/delete/{id}",method=RequestMethod.GET)
  public ResponseEntity<JsonResult> delete(@PathVariable(value="id") Integer id){
    JsonResult r = new JsonResult();
    try {
      int orderId = userService.delete(id);
      if(orderId <0) {
        r.setResult(orderId);
        r.setStatus("fail");
      }else {
        r.setResult(orderId);
        r.setStatus("ok");
      }
     
    }catch(Exception e) {
      r.setResult(e.getClass().getName() + ":"+e.getMessage());
      r.setStatus("error");
      e.printStackTrace();
    }
    return ResponseEntity.ok(r);
  }
  
  @RequestMapping(value="/user/update/{id}", method=RequestMethod.POST)
  public ResponseEntity<JsonResult> update(@PathVariable(value="id") Integer id,@RequestBody User user){
    JsonResult r = new JsonResult();
    try {
      int orderId = userService.update(id, user);
      if(orderId < 0) {
        r.setResult(orderId);
        r.setStatus("fail");
      }else {
        r.setResult(orderId);
        r.setStatus("ok");
      }
    }catch(Exception e) {
      r.setResult(e.getClass().getName() + ":" + e.getMessage());
      r.setStatus("error");
    }
    return ResponseEntity.ok(r);
  }
  
}
