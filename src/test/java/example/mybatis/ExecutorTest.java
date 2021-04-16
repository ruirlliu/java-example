package example.mybatis;

import org.apache.ibatis.executor.SimpleExecutor;

/**
 * @author lr
 * @date 2021/3/19
 */
public class ExecutorTest {

    public static void main(String[] args) {

        Class<?> executorClass = SimpleExecutor.class;

        while (executorClass != null) {
            System.out.println(executorClass);
            for (Class<?> anInterface : executorClass.getInterfaces()) {
                System.out.println(anInterface);
            }
            executorClass = executorClass.getSuperclass();
        }

    }

}
