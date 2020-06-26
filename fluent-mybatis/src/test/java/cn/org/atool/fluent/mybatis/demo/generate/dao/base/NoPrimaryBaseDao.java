package cn.org.atool.fluent.mybatis.demo.generate.dao.base;

import cn.org.atool.fluent.mybatis.base.impl.BaseDaoImpl;
import cn.org.atool.fluent.mybatis.demo.generate.entity.NoPrimaryEntity;
import cn.org.atool.fluent.mybatis.demo.generate.mapper.NoPrimaryMapper;
import cn.org.atool.fluent.mybatis.demo.generate.helper.NoPrimaryMapping;
import cn.org.atool.fluent.mybatis.demo.generate.wrapper.NoPrimaryQuery;
import cn.org.atool.fluent.mybatis.demo.generate.wrapper.NoPrimaryUpdate;
import org.springframework.beans.factory.annotation.Autowired;


/**
* NoPrimaryEntity数据库操作服务类
 *
 * @author generate code
*/
public abstract class NoPrimaryBaseDao extends BaseDaoImpl<NoPrimaryEntity, NoPrimaryQuery, NoPrimaryUpdate>
        implements NoPrimaryMapping {

    @Autowired
    protected NoPrimaryMapper mapper;

    @Override
    public NoPrimaryMapper mapper() {
        return mapper;
    }

    @Override
    public NoPrimaryQuery query(){
        return new NoPrimaryQuery();
    }

    @Override
    public NoPrimaryUpdate updater(){
        return new NoPrimaryUpdate();
    }

    @Override
    public String findPkColumn() {
        throw new RuntimeException("undefine primary key");
    }
}