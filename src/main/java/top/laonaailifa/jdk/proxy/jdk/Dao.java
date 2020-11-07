package top.laonaailifa.jdk.proxy.jdk;

public interface Dao {
    @Select("Select * from *")
    public String query();
}
