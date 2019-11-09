package cn.org.atool.fluent.mybatis.test.method;

import cn.org.atool.fluent.mybatis.demo.generate.ITable;
import cn.org.atool.fluent.mybatis.demo.generate.datamap.TM;
import cn.org.atool.fluent.mybatis.demo.generate.entity.UserEntity;
import cn.org.atool.fluent.mybatis.demo.generate.mapper.UserMapper;
import cn.org.atool.fluent.mybatis.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class InsertWithPkTest extends BaseTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void testInsertWithPk() {
        db.table(ITable.t_user).clean();
        UserEntity user = new UserEntity()
                .setId(34L)
                .setUserName("user name")
                .setAge(25);
        mapper.insertWithPk(user);
        db.table(ITable.t_user)
                .query()
                .eqDataMap(TM.user.create(1)
                        .id.values(34L)
                        .user_name.values("user name")
                        .age.values(25)
                );
    }
}
