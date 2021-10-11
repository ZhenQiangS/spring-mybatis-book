package com.ay.test;

import com.ay.dao.AyUserDao;
import com.ay.model.AyUser;
import com.ay.service.AyUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：用户DAO 测试类
 *
 * @author ZQS
 * @create 2020/2/29
 */
public class AyUserDaoTest extends BaseJunit4Test {

    @Resource
    private AyUserDao ayUserDao;


    @Test
    public void testFindAll2() {
        List<AyUser> userList = ayUserDao.findAll(new RowBounds(0, 5));

        for (AyUser ayUser : userList) {
            System.out.println(userList.size());
        }
    }

    @Test
    public void testPageHelper() {

        //startPage(第几页，多少条)
        PageHelper.startPage(0, 1);

        //查询所有用户
        List<AyUser> userList = ayUserDao.findAll();

        //用pageInfo 对结果进行包装
        PageInfo pageInfo = new PageInfo(userList);
    }


    @Resource
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    //  使用一级缓存： 在参数和SQL完全一样时，使用同一个sqlSession 对象调用用一个Mapper方法，往往只会执行一次SQL
    //  看日志 Cache Hit Ratio [com.ay.dao.AyUserDao] 表示为缓存中的数据
    @Test
    public void testSessionCache() throws Exception {
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AyUserDao ayUserDao = sqlSession.getMapper(AyUserDao.class);

        AyUser ayUser = ayUserDao.findById("5");
        System.out.println("name：" + ayUser.getName() + " password：" + ayUser.getPassword());

        AyUser ayUser1 = ayUserDao.findById("5");
        System.out.println("name：" + ayUser1.getName() + " password：" + ayUser1.getPassword());

        AyUser ayUser2 = ayUserDao.findById("8");
        System.out.println("name：" + ayUser2.getName() + " password：" + ayUser2.getPassword());
        sqlSession.close();
    }

    @Resource
    private AyUserService ayUserService;

    // 普通查询
    @Test
    public void testSessionCache2() throws Exception {

        AyUser ayUser = ayUserDao.findById("5");
        System.out.println("name：" + ayUser.getName() + " password：" + ayUser.getPassword());

        AyUser ayUser1 = ayUserDao.findById("5");
        System.out.println("name：" + ayUser1.getName() + " password：" + ayUser1.getPassword());


    }

    //使用一级缓存： 看日志：在中间执行了commit 操作（更新，删除或插入）时,一级缓存会被刷新
    //Cache Hit Ratio [com.ay.dao.AyUserDao] 表示为缓存中的数据
    @Test
    public void testSessionCache3() throws Exception {
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AyUserDao ayUserDao = sqlSession.getMapper(AyUserDao.class);

        AyUser ayUser = ayUserDao.findById("5");
        System.out.println("name：" + ayUser.getName() + " password：" + ayUser.getPassword());

        AyUser user = new AyUser();
        user.setName("测试3");
        user.setPassword("1234");
        ayUserDao.insert(user);

        AyUser ayUser1 = ayUserDao.findById("5");
        System.out.println("name：" + ayUser1.getName() + " password：" + ayUser1.getPassword());
        sqlSession.close();
    }

    // 二级缓存 只对 namespace 中的范围 有效、实时。
    // 二级缓存。 Cache Hit Ratio [com.ay.dao.AyUserDao] 表示为缓存中的数据
    // 设置禁用二级缓存：在 xml 中的select 标签中设置 useCache=“false"
    @Test
    public void testSecondCache() throws Exception {
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AyUserDao ayUserDao = sqlSession.getMapper(AyUserDao.class);

        AyUser ayUser = ayUserDao.findById("5");
        System.out.println("name：" + ayUser.getName() + " password：" + ayUser.getPassword());

        AyUser model = new AyUser();
        model.setName("小红");
        model.setPassword("123456");
        model.setAge(10);
        ayUserDao.insert(model);

        AyUser ayUser2 = ayUserDao.findById("1");
        System.out.println("name：" + ayUser2.getName() + " password：" + ayUser2.getPassword());

    }

    @Test
    @GetMapping("/xml")
    public String xml() {
        return "index";
    }
}
