package com.lxp.community.controller;

import com.lxp.community.service.AlphaService;
import com.lxp.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")

public class AlphaController {
    @Autowired
    private AlphaService alphaService;


    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello spring boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));

        response.setContentType("text/html;charset=utf-8");
        try(PrintWriter writer = response.getWriter();){
            writer.write("<h1>李西萍</h1>");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //GET请求---方式一
    //  /students?current=1&limit=20
    //初次访问，没有默认值，则使用RequestParam参数，required=false 可以不传参
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
            ){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    //GET 方式二（查询一个）
    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }
    //POST
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, String age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应html数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "李西萍");
        modelAndView.addObject("age",18);
        //view.html
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    //响应html数据 方式二
    //简洁！
    //model数据装进参数
    //view直接返回
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    //model是dispatchservlet 实例化的
    //方法内部可以给model存数据
    public String getSchool(Model model){
        model.addAttribute("name", "北大");
        model.addAttribute("age",80);
        //return view的路径
        //model数据不能传
        return "/demo/view";
    }

    //响应json数据（异步请求）
    //Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","lll");
        emp.put("age", 23);
        emp.put("salary", 8000.0);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object> >getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name","lll");
        emp.put("age", 23);
        emp.put("salary", 8000.0);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","ddd");
        emp.put("age", 25);
        emp.put("salary", 8003.0);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","cj");
        emp.put("age", 33);
        emp.put("salary", 3300.0);
        list.add(emp);
        return list;
    }

    //cookie示例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        // 创建Cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的范围(请求头 什么路径下有效）
        cookie.setPath("/community/alpha");
        // 默认存入浏览器内存，设置生存时间则放入硬盘里
        // 10minutes
        cookie.setMaxAge(60 * 10);
        // 发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }

    //session 示例
    //Spring MVC注入
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "Test");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
}
