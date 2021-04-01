package example.jdk.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 重写类加载器
 * @author lr
 * @date 2020/10/29
 * https://blog.csdn.net/javazejian/article/details/73413292/
 */
public class ClassLoaderDemo extends ClassLoader {

    //指定路径
    private String path ;


    public ClassLoaderDemo(String classPath){
        path=classPath;
    }

    /**
     * 重写findClass方法
     * @param name 是我们这个类的全路径
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class log = null;
        // 获取该class文件字节码数组
        byte[] classData = getData();

        if (classData != null) {
            // 将class的字节码数组转换成Class类的实例
            log = defineClass(name, classData, 0, classData.length);
        }
        return log;
    }

    /**
     * 将class文件转化为字节码数组
     * @return
     */
    private byte[] getData() {

        File file = new File(path);
        if (file.exists()){
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            return out.toByteArray();
        }else{
            return null;
        }


    }

    public static void main(String[] args) throws ClassNotFoundException {



        // 类全名不能以java开头  Prohibited package name: java.lang.ref
        String path = "C:\\IDEAWorkspace\\ideaworkspace\\java-demo\\java-se-demo\\target\\classes\\java\\lang\\ref\\Reference1.class";
        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo(path);

        Class<?> aClass1 = classLoaderDemo.loadClass("java.lang.Integer");
        System.out.println(aClass1);
        System.out.println(aClass1.getClassLoader());

        Class<?> aClass = classLoaderDemo.loadClass("java.lang.ref.Reference1");
        System.out.println(aClass);
        System.out.println(aClass.getName());
    }


}
