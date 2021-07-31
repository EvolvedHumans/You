package y.yj.module_login;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;

public class FlashActivityTest extends TestCase {

    public void testInitWindow() {
        //创建集合
        Collection collection = new ArrayList();
        collection.add("菜单");
        collection.add("西瓜");
        System.out.println(collection.size());
        System.out.println(collection);
    }
}