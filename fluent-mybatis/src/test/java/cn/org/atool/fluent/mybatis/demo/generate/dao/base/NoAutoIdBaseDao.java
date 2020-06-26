package cn.org.atool.fluent.mybatis.demo.generate.dao.base;

import cn.org.atool.fluent.mybatis.base.impl.BaseDaoImpl;
import cn.org.atool.fluent.mybatis.demo.generate.entity.NoAutoIdEntity;
import cn.org.atool.fluent.mybatis.demo.generate.mapper.NoAutoIdMapper;
import cn.org.atool.fluent.mybatis.demo.generate.helper.NoAutoIdMapping;
import cn.org.atool.fluent.mybatis.demo.generate.wrapper.NoAutoIdQuery;
import cn.org.atool.fluent.mybatis.demo.generate.wrapper.NoAutoIdUpdate;
import org.springframework.beans.factory.annotation.Autowired;


/**
* NoAutoIdEntity数据库操作服务类
 *
 * @author generate code
*/
public abstract class NoAutoIdBaseDao extends BaseDaoImpl<NoAutoIdEntity, NoAutoIdQuery, NoAutoIdUpdate>
        implements NoAutoIdMapping {

    @Autowired
    protected NoAutoIdMapper mapper;

    @Override
    public NoAutoIdMapper mapper() {
        return mapper;
    }

    @Override
    public NoAutoIdQuery query(){
        return new NoAutoIdQuery();
    }

    @Override
    public NoAutoIdUpdate updater(){
        return new NoAutoIdUpdate();
    }

    @Override
    public String findPkColumn() {
        return NoAutoIdMapping.id.column;
    }
}