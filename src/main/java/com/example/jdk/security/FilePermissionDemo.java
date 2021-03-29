package com.example.jdk.security;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;

/**
 * @author lr
 * @date 2021/1/26
 */
public class FilePermissionDemo {


    public static void main(String[] args) throws MalformedURLException {

        System.setProperty("java.security.policy", "policy/demo2.policy");
        System.setSecurityManager(new SecurityManager());


        fileAction("C:/boc");
        System.out.println("**************");
        fileAction("C:/abcsys");
        System.out.println("********");
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            FileUtilDemo demo = new FileUtilDemo();
            demo.check("C:/data");
            return null;
        });
        List<String> strings = FileUtil.listFileNames("C:/boc");
        System.out.println(strings);
        System.out.println("*************************");
        FileUtilDemo demo = new FileUtilDemo();
        demo.check("C:/abcsys");





//        SecurityManager securityManager = System.getSecurityManager();
//        System.out.println(securityManager == null);
//
//        AccessControlContext context = (AccessControlContext)securityManager.getSecurityContext();
//
//
////        fileAction("C:/boc");
////        fileAction("C:/abcsys");
//
//
//
//        doPrivilegedAction("C:/boc", context);
//        Policy policy = Policy.getPolicy();
//        doPrivilegedAction("C:/abcsys", context);
    }


    static class FileUtilDemo {
        public void check(String path) {
            File file = new File(path);
            String[] list = file.list();
            assert list != null;
            for (String s : list) {
                System.out.println(s);
            }
        }
    }


    public static void fileAction(String filePath) {
        try {
            File file = new File(filePath);
            String[] list = file.list();
            for (String s : list) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doPrivilegedAction(String filePath, AccessControlContext context) {
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            fileAction(filePath);
            return null;
        }, context);

    }



}
