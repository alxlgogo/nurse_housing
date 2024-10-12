package com.jing.controller;

import com.jing.pojo.Permission;
import com.jing.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/selectpermission")
    public String selectPromission(Model model) {
        List<Permission> permissions = permissionService.selectList();
        model.addAttribute("permissions", permissions);
        System.out.println("selectpermission....");
        return "pages/permissionList";
    }
}
