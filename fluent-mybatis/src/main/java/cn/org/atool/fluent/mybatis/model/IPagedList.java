package cn.org.atool.fluent.mybatis.model;

import java.util.List;

/**
 * 分页查询结果
 *
 * @param <E>
 */
public interface IPagedList<E> {
    /**
     * 是否标准分页
     *
     * @return
     */
    default boolean isStdPaged() {
        return false;
    }

    /**
     * 是否tag分页
     *
     * @return
     */
    default boolean isTagPaged() {
        return false;
    }

    /**
     * 分页查询返回数据
     *
     * @return
     */
    List<E> getData();

    /**
     * @return
     */
    default E getNext() {
        return null;
    }

    /**
     * 数据总数, -1表示没有计算总数
     *
     * @return
     */
    default int getTotal() {
        return -1;
    }
}