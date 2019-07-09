package com.controller;

import com.entity.Student;
import com.google.gson.Gson;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class HelloAPI extends HttpServlet {

    static {
            ObjectifyService.register(Student.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().println(new Gson().toJson(ofy().load().type(Student.class).list()));

//        ArrayList<Student> studentArrayList = new ArrayList<Student>();
//        Student student = new Student();
//        student.setRollNumber(00623);
//        student.setName("Tuans");
//        student.setEmail("tuanlcd00623@fot.edu.vn");
//        studentArrayList.add(student);
//
//        Gson gson = new Gson();
//        String studentJson = gson.toJson(studentArrayList);
//        System.out.println(studentJson);
//        resp.getWriter().println(studentJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = StringUtil.convertInputStreamToString(req.getInputStream());

        Student student = new Gson().fromJson(content, Student.class);
        Key<Student> studentKey = ofy().save().entity(student).now();
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().print(new Gson().toJson(studentKey));
//        System.out.println(student.getRollNumber());
//        System.out.println(student.getName());

    }
}
