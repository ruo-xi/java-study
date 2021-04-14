package top.laonaailifa.framework.springframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.laonaailifa.framework.springframework.config.AppConfig;
import top.laonaailifa.framework.springframework.entity.A;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(annotationConfigApplicationContext.getBean("&a"));
        System.out.println();
    }
}
