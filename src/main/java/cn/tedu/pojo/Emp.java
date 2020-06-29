package cn.tedu.pojo;
/*
* pojo类：用于封装员工信息
* */
public class Emp {
   /* 声明id、name、job、salary四个属性，分别用于封装emp
   * 表中的id、name、job、salary
   * 注意:如果能让Emp类中的变量和emp表中的列名保持一致的话，
   * 尽量保持一致(在不违反Java命名规范的前提下)
   * */
    private Integer id;
    private String name;
    private String job;
    private Double salary;

    public Emp() {
    }

    public Emp(int i, String 真布, String 魔法使, double v) {
    }

    //getter、Setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
//toString方法
    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                '}';
    }
}
