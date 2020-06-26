package cn.org.atool.fluent.mybatis.demo.generate.entity;

import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.annotation.TableName;
import cn.org.atool.fluent.mybatis.base.IEntity;


import cn.org.atool.fluent.mybatis.demo.generate.helper.NoPrimaryEntityHelper;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author generate code
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("no_primary")
public class NoPrimaryEntity implements IEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableField(value = "column_1")
    private Integer column1;
    /**
     * 
     */
    @TableField(value = "column_2")
    private String column2;

    @Override
    public Serializable findPk() {
        return null;
    }

    @Override
    public Map<String, Object> toMap() {
        return NoPrimaryEntityHelper.map(this);
    }

    @Override
    public Map<String, Object> columnMap() {
        return NoPrimaryEntityHelper.columnMap(this);
    }
}