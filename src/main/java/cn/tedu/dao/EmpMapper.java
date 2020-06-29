package cn.tedu.dao;

import cn.tedu.pojo.Emp;

import java.util.List;
import java.util.Map;

public interface EmpMapper {


    //练习1
    public List<Emp> findAll();

    public int insert06(Map map);

    public int update07(Emp emp);

    public int deleteById08(int id);
}
