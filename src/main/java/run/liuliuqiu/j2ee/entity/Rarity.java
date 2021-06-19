package run.liuliuqiu.j2ee.entity;

/**
 * @author ：Tong
 * @date ：Created in 2019/12/26 15:52
 * @description：
 * @version: $
 */
public class Rarity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rarity{" +
                "name='" + name + '\'' +
                '}';
    }
}
