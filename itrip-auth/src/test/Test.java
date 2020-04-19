import cn.bdqn.itrip.controller.UserContoller;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;




public class Test {

    @org.junit.Test
    public void test(){
        //spring 初始化的过程
        AnnotationConfigApplicationContext  an=
                new AnnotationConfigApplicationContext(Muxiaojian.class);
        UserContoller a =(UserContoller) an.getBean("Muxiaojian");
        System.out.println(a);

    }
}
