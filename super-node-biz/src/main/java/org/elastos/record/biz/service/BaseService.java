package org.elastos.record.biz.service;

import org.beetl.sql.core.engine.PageQuery;

import org.elastos.record.utility.entity.CrudEntity;
import org.elastos.record.utility.exception.InsertNewInstanceException;

import java.util.List;

public interface BaseService<T extends CrudEntity> {
    T save(T t) throws InsertNewInstanceException;

    void saveTemplate(T t) throws InsertNewInstanceException;

    void saveBatch(List<T> t) throws InsertNewInstanceException;

    void checkNewInstance(T instance) throws InsertNewInstanceException;

    /**
     * 根据主键更新对象，所以属性都参与更新。也可以使用主键ColumnIgnore来控制更新的时候忽略此字段
     *
     * @param entity
     * @return
     */
    int updateById(T entity);

    /**
     * 根据主键更新对象，只有不为null的属性参与更新
     *
     * @param entity
     * @return
     */
    int updateTemplateById(T entity);

    /**
     * 根据主键删除对象，如果对象是复合主键，传入对象本生即可
     *
     * @param key
     * @return
     */
    int deleteById(Long key);

    int deleteByIds(List<Long> key);

    int forceDeleteById(Long t);

    /**
     * 根据主键获取对象，如果在事物中执行会添加数据库行级锁(select * from table where id = ? for update)，如果对象不存在，返回null
     *
     * @param key
     * @return
     */
    T lock(T key);


    /**
     * 返回实体对应的所有数据库记录
     *
     * @return
     */
    List<T> all();


    /**
     * 模板查询，返回符合模板得所有结果。beetlsql将取出非null值（日期类型排除在外），从数据库找出完全匹配的结果集
     *
     * @param entity
     * @return
     */
    List<T> template(T entity);


    /**
     * 模板查询，返回一条结果,如果没有，返回null
     *
     * @param entity
     * @return
     */
    T templateOne(T entity);

    List<T> template(T entity, int start, int size);

    void templatePage(PageQuery<T> query);

    /**
     * 符合模板得个数
     *
     * @param entity
     * @return
     */
    long templateCount(T entity);

    List<T> sample(T t);

    List<T> batchUpdate(List<T> list);

    T single(Long id);
}
