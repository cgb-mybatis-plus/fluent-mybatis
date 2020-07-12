package cn.org.atool.fluent.mybatis.demo.generate.wrapper;

import cn.org.atool.fluent.mybatis.base.impl.BaseQuery;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.segment.model.ParameterPair;

import cn.org.atool.fluent.mybatis.demo.generate.entity.UserEntity;
import cn.org.atool.fluent.mybatis.demo.generate.helper.UserMapping;
import cn.org.atool.fluent.mybatis.demo.generate.helper.UserWrapperHelper.*;
import cn.org.atool.fluent.mybatis.exception.FluentMybatisException;

import java.util.function.Function;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.isNotBlank;

/**
 * @author generate code
 * @ClassName UserQuery
 * @Description UserEntity查询（删除）条件
 */
public class UserQuery extends BaseQuery<UserEntity, UserQuery> {
    public final Selector select = new Selector(this);
    /**
     * 分组：GROUP BY 字段, ...
     * 例: groupBy("id", "name")
     */
    public final GroupBy groupBy = new GroupBy(this);
    /**
     * 分组条件设置 having...
     */
    public final Having having = new Having(this);
    /**
     * 排序设置 order by ...
     */
    public final QueryOrderBy orderBy = new QueryOrderBy(this);
    /**
     * 条件设置 where ...
     */
    public final QueryWhere where = new QueryWhere(this);

    public UserQuery() {
        super(UserMapping.Table_Name, UserEntity.class, UserQuery.class);
    }

    public UserQuery(ParameterPair parameters) {
        super(UserMapping.Table_Name, parameters, UserEntity.class, UserQuery.class);
    }

    @Override
    public UserQuery selectId() {
        return this.select(UserMapping.id.column);
    }

    /**
     * 查询字段设置
     *
     * @param by      查询字段设置器
     * @param columns 查询字段列表
     * @return 查询器UserQuery
     */
    public UserQuery select(Function<Selector, Selector> by, String... columns) {
        by.apply(this.select);
        this.select(columns);
        return this;
    }

    /**
     * 查询字段设置
     *
     * @param by      查询字段设置器
     * @param columns 查询字段列表
     * @return 查询器UserQuery
     */
    public UserQuery select(Function<Selector, Selector> by, FieldMapping column, FieldMapping... columns) {
        by.apply(this.select);
        this.select(column, columns);
        return this;
    }

    @Override
    public QueryWhere where() {
        return this.where;
    }

    @Override
    protected boolean hasPrimary() {
        return true;
    }

    @Override
    protected void validateColumn(String column) throws FluentMybatisException {
        if (isNotBlank(column) && !UserMapping.ALL_COLUMNS.contains(column)) {
            throw new FluentMybatisException("the column[" + column + "] was not found in table[" + UserMapping.Table_Name + "].");
        }
    }
}