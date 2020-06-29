package cn.tedu.mybatis;

import cn.tedu.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 完成mybatis的快速入门
 * mybatis占位符
 *
 * */
public class TestMybatis02 {
    static SqlSession session=null;
    static {
        //为session进行初始化
        try {
            //1.读取mybatis的核心配置文件(mybatis-config.xml)
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            //2.通过配置获取SqlSessionFactory对象
            SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
            //3.读取SqlSession对象(打开与数据库的连接) true表示自动提交，false为手动提交
            session = fac.openSession();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void findAll() throws IOException {
        //4.执行SQL语句，并返回执行结果(EmpMapper.xml)
        List<Emp> list = session.selectList("EmpMapper.findAll");
        //5.输出结果
        for (Emp e : list) {
            System.out.println(e);
        }
    }

    /*
     * 练习2：新增员工信息：赵云 保安 6000
     * */
    @Test
    public void testInsert() throws IOException {

        //执行sql语句, 返回执行结果
        int rows = session.update("EmpMapper.insert");
        //提交事务
        session.commit();
        System.out.println("影响行数：" + rows);
    }

    /*
     * 练习3：修改员工信息 将赵云的job改为‘保镖’，salary改为20000
     * */
    @Test
    public void testUpdate() throws IOException {
        //执行sql语句, 返回执行结果
        int rows = session.update("EmpMapper.update");
        //提交事务
        session.commit();
        System.out.println("影响行数：" + rows);
    }
    /**
     *  练习4: 删除name为'赵云'的记录
     *  */
    @Test
    public void testDelete() throws IOException {
        //执行sql语句, 返回执行结果
        int rows = session.update("EmpMapper.delete");
        //提交事务
        session.commit();
        System.out.println("影响的行数：" + rows);
    }
    //==================mybatis占位符================================
    /**
     * 练习5: 查询emp表中指定id的员工信息
     * */
    @Test
    public void testFindById(){
        Emp emp = session.selectOne("EmpMapper.findById", 1);
        System.out.println(emp);
    }
    /**
     * 练习6: 新增员工信息: 张飞 Java开发工程师 15000
     * */
    @Test
        public void testInsert02(){
        //将要传输的参数封装到map集合中
        Map map = new HashMap();
        map.put("name", "张飞");
        map.put("job", "Java开发工程师");
        map.put("salary", 15000);
        int rows = session.insert("EmpMapper.insert2", map);
        session.commit();
        System.out.println("影响行数："+rows);
        }
    /**
     * 练习7: 修改员工信息: 张飞 架构师 25000
     * */
    @Test
    public void testUpdate02(){
        Emp emp = new Emp(9, "真布", "魔法使", 45000.0);
        emp.setName("张飞");
        emp.setJob("架构师");
        emp.setSalary(25000.0);
        int rows = session.update("EmpMapper.update2", emp);
        session.commit();
        System.out.println("影响行数："+rows);
    }
    /*  练习8：删除emp表中指定id的员工信息 */
    public void testDelete2() throws IOException{
        //执行SQL语句
        int rows = session.delete("EmpMapper.delete2", 1);
        //提交事务
        session.commit();
        System.out.println("影响行数:"+rows);
    }
    /*
    *练习9: 动态指定要查询的列
     */
    @Test
    public void testFindAll02(){
        Map map = new HashMap();
        map.put("cols","id,name,job,salary");
        List<Emp> list = session.selectList("EmpMapper.findAll2", map);

        for (Emp e:list){
            System.out.println(e);
        }
    }
    /**
     * 练习10: 根据name模糊查询emp表
     * '%王%' '%刘%'
     */
    @Test
    public void testFindAll3(){
        Map map = new HashMap();
        map.put("name","涛");
        List<Emp> list = session.selectList("EmpMapper.findAll3", map);
        for (Emp emp:list){
            System.out.println(emp);
        }
    }
    /**
     * 练习11: 根据name模糊查询emp表
     * '%王%' '%刘%'
     */
    @Test
    public void testFindAll4() {
        //将参数封装到map集合中
        Map map = new HashMap();
        map.put("name", "%刘%");
        //执行sql, 返回结果
        List<Emp> list = session.selectList("EmpMapper.findAll4", map);
        //输出结果
        for (Emp emp : list) {
            System.out.println( emp );
        }
    }
    /* 练习12: 根据薪资查询员工信息
     * 如果没有参数, 则不执行where子句, 默认查询所有员工：
     * 	select * from emp
     * 如果参数中只有minSal(即minSal不为null), 则：
     * 	... where salary > minSal
     * 如果参数中只有maxSal(即maxSal不为null), 则：
     * 	... where salary < maxSal
     * 如果参数有 minSal、maxSal(即minSal、maxSal不为null), 则：
     *	... where salary > minSal and salary < maxSal  */
    @Test
    public void TestFindBySal(){
        HashMap<String, Object> map = new HashMap<>();
        List<Emp> list = session.selectList("EmpMapper.findAllBySal", map);
        for(Emp emp:list){
            System.out.println(emp);
        }
    }
    /**
     * 练习13: 根据薪资查询员工信息
     * 如果没有参数, 则不执行where子句, 默认查询所有员工：
     * 	select * from emp
     * 如果参数中只有minSal(即minSal不为null), 则：
     * 	... where salary > minSal
     * 如果参数中只有maxSal(即maxSal不为null), 则：
     * 	... where salary < maxSal
     * 如果参数有 minSal、maxSal(即minSal、maxSal不为null), 则：
     *	... where salary > minSal and salary < maxSal  */
    @Test
    public void TestFindBySal2(){
        Map map = new HashMap();
        map.put("minSal",3000.0);
        map.put("maxSal",4000.0);
        List<Emp> list = session.selectList("EmpMapper.findALLBySal2",map);
        for(Emp emp:list){
            System.out.println(emp);
        }
    }
    /*
        练习14: 根据员工的id批量删除员工信息
    */
    @Test
    public void testDeleteByIds(){
        Integer[] ids={1,3,5,7};
        int rows = session.delete("EmpMapper.deleteByIds", ids);
        System.out.println("影响行数："+rows);
    }
    /* 练习15: 根据员工的id批量更新员工信息
     * 将id为 2、4、6、8的员工的薪资在原有基础上增加1000
     */
    @Test
    public void TestUpdateByIds(){
        Integer[] ids={2,4,6,8};
        Double sal=1000.0;
        Map<String, Object> map = new HashMap<>();
        map.put("arrId",ids);
        map.put("sal",sal);
    }
}
