package cn.tedu.mybatis;



import cn.tedu.dao.EmpMapper;
import cn.tedu.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestMybatis03 {

    static SqlSession session = null;
    static {
        //为session进行初始化
        try {
//            1.读取mybatis的核心配置文件(mybatis-config.xml)
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
//            2.通过配置获取SQLSessionFactory工厂对象
            SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
            session = fac.openSession(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   /* 1、创建一个接口，接口的全路径名和mapper文件的namespace值要相同
    2、mapper文件中每条要执行的SQL语句，在接口中要添加一个对应的方法，并且接口中的方法名和SQL标签上的id值相同
    3、Mapper接口中方法接收的参数类型，和mapper.xml中定义的sql的接收的参数类型要相同
    4、接口中方法的返回值类型和SQL标签上的resultType即返回值类型相同(如果方法返回值是集合,resultType只需要指定集合中的泛型)
    */
    @Test
    public void testFindAll16(){
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        List<Emp> list = mapper.findAll();
        /* * finaAll方法内部内部,会根据当前接口的全限定接口名+当前方法名
         * 到mapper文件中定位要执行的sql语句,找到并执行sql,返回处理后的结果
         * 接口的全限定接口名+方法名=namespace值+id值
         **/
        for (Emp emp:list) {
            System.out.println(emp);
        }
    }
    //新增
    @Test
    public void testInsert17(){
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        Map map = new HashMap();
        map.put("name","PaPi酱");
        map.put("job","CEO");
        map.put("salary",15000);
        session.commit();
        int rows = mapper.insert06(map);
        System.out.println(rows);
    }

    @Test
    public void TestUpdate18(){
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        Emp emp = new Emp(13,"PaPi酱","CEO",150000);
        //调用EmpMapper接口子类的方法
        session.commit();
        int rows = mapper.update07( emp );
        System.out.println(rows);
    }
    @Test
    public void TestDelete19(){
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        //调用deleteById09方法
        session.commit();
        int i = mapper.deleteById08(13);
        System.out.println(i);
    }

}