package com.example.security;

import java.io.FilePermission;
import java.io.IOException;
import java.net.SocketPermission;
import java.nio.file.Files;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.util.PropertyPermission;

/**
 * @author liurui
 * @date 2021/1/25
 */
public class PermissionDemo {


    public static void main(String[] args) throws IOException, PrivilegedActionException {

        SecurityManager securityManager = System.getSecurityManager();
        System.out.println(securityManager == null);

        System.getProperties().forEach((x, y) -> {
            System.out.println(x + " = " + y);
        });

        String property = System.getProperty("java.security.manager");
        System.out.println("property " + property);

        SecurityManager manager = new SecurityManager();
        System.setSecurityManager(manager);


        System.out.println(System.getSecurityManager() == null);

        AccessController.doPrivileged((PrivilegedAction<String>) () -> {
            FilePermission filePermission=new FilePermission("C:\\Users\\HP\\AppData\\Local\\Temp","read");//允许在/tmp/*下进行读写操作
            AccessController.checkPermission(filePermission);
            return "null";
        });
        String property222 = System.getProperty("java.security.manager");
        System.out.println("property22 " + property222);

        PropertyPermission read = new PropertyPermission("java.vm.version", "read");
        AccessController.checkPermission(read);



        String test = Files.createTempDirectory("test").toString();
        System.out.println(test);
        FilePermission filePermission=new FilePermission("C:\\Users\\HP\\AppData\\Local\\Temp","read");//允许在/tmp/*下进行读写操作
        AccessController.checkPermission(filePermission);


        FilePermission filePermission2=new FilePermission("C:\\Programs\\Java\\jdk1.8.0_251\\*","read,write");//允许在/tmp/*下进行读写操作
        AccessController.checkPermission(filePermission2);

        SocketPermission socketPermission=new SocketPermission("192.168.2.108:8080","listen");//监听192.168.0.1机器8080端口
        AccessController.checkPermission(socketPermission);
    }

}
