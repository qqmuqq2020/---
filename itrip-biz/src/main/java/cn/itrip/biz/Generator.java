package cn.itrip.biz;


import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Generator {
    private Configuration conf ;
    //执行环境初始化
    public void init() throws Exception{
        //1.完成环境初始化
        //Configuration 实例化
            conf=new Configuration(Configuration.getVersion());
        //设置模板所在路径(怎样获取到类的路径)
        String  path= this.getClass().getClassLoader().getResource("").getPath();
        conf.setDirectoryForTemplateLoading(new File(path));//初始化配置路径
    }
    //生成代码的
    public void process(String tempName,String savepath) throws  Exception{
        //1 获取模板
        Template template = conf.getTemplate(tempName);
        //组装数据
        Map map = new HashMap();
        map.put("name","Tom");
        //初始化保存路径
        FileOutputStream  fileInputStream = new FileOutputStream(savepath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileInputStream);
       // 传参 生成数据
        template.process(map,outputStreamWriter);
    }
    public static void main(String[] args) {
    Generator generator = new Generator();
        try {
            generator.init();
            generator.process("hello.ftl","D://file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
