package cn.tedu.mybatis;

import cn.tedu.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*
 * 完成mybatis的快速入门
 * 对yonghedb.emp表中的数据进行增删改查的所有功能
 *
 * */
public class TestMybatis01 {
/*
* 练习1:查询emp表中的所有员工信息
* */
    @Test
    public void findAll() throws IOException {
        //1.读取mybatis的核心配置文件(mybatis-config.xml)
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.通过配置获取SqlSessionFactory对象
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
        //3.读取SqlSession对象(打开与数据库的连接) true表示自动提交，false为手动提交
        SqlSession session = fac.openSession();
        //4.执行SQL语句，并返回执行结果(EmpMapper.xml)
        List<Emp> list = session.selectList("EmpMapper.findAll");
        //5.输出结果
        for (Emp e:list){
            System.out.println(e);
        }
    }
    /*
    * 练习2：新增员工信息：赵云 保安 6000
    * */
    @Test
    public void testInsert() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.通过配置获取SqlSessionFactory对象
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
        //3.读取SqlSession对象(打开与数据库的连接) true表示自动提交，false为手动提交
        SqlSession session = fac.openSession();
        int rows = session.update("EmpMapper.insert");
        session.commit();
        System.out.println("影响行数："+rows);
    }
    /*
    * 练习3：修改员工信息 将赵云的job改为‘保镖’，salary改为20000
    * */
    @Test
    public void testUpdate() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.通过配置获取SqlSessionFactory对象
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
        //3.读取SqlSession对象(打开与数据库的连接) true表示自动提交，false为手动提交
        SqlSession session = fac.openSession();
        int rows = session.update("EmpMapper.update");
        session.commit();
        System.out.println("影响行数："+rows);
    }
    @Test
    public void testDelete() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.通过配置获取SqlSessionFactory对象
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
        //3.读取SqlSession对象(打开与数据库的连接) true表示自动提交，false为手动提交
        SqlSession session = fac.openSession();
        int rows = session.update("EmpMapper.delete");
        System.out.println("影响的行数："+rows);
    }
}
