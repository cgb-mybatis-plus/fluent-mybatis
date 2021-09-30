package cn.org.atool.fluent.mybatis.base.entity;

import cn.org.atool.fluent.mybatis.base.RichEntity;
import cn.org.atool.fluent.mybatis.refs.RefKit;

/**
 * IRich: Entity类@RefMethod方法实现定义
 *
 * @author wudarui
 */
public interface IRich {
    /**
     * Entity rich @RefMethod method方法定义接口
     * <p>
     * 具体实现 {@link RichEntity#invoke(String, boolean)}
     * 对应调用 {@link RefKit#invoke(Class, String, Object[])}
     *
     * @param method rich method方法名称
     * @param cache  结果是否缓存
     * @param <T>    ignore
     * @return ignore
     */
    <T> T invoke(String method, boolean cache);
}