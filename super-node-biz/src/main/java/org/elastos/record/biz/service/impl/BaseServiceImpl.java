package org.elastos.record.biz.service.impl;

import lombok.Getter;

import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;

import org.elastos.record.biz.dao.BaseDao;
import org.elastos.record.biz.service.BaseService;
import org.elastos.record.biz.util.BeanUtils;
import org.elastos.record.biz.util.GenericsUtils;
import org.elastos.record.utility.entity.CrudEntity;

import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

//@Slf4j
@Service
@Transactional
@Getter
@SuppressWarnings({"unchecked", "unused"})
public abstract class BaseServiceImpl<T extends CrudEntity, D extends BaseDao<T>> implements BaseService<T>, ApplicationContextAware {
    private String tableName;
    private T entity;
    private D entityDao;
    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private T getEntity() {
        if (entity != null) {
            return entity;
        }
        // 获取定义的第n个实例变量类型
        Class<T> entityType = GenericsUtils.getSuperClassGenricType(getClass(), 0);
        try {
            entity = entityType.newInstance();
            Table annotation = entity.getClass().getAnnotation(Table.class);
            tableName = annotation.name();
            return entity;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String getDefinedEntityTableName() {
        if (!StringUtils.isEmpty(tableName)) {
            return tableName;
        }
        T entity = getEntity();
        Class<?> aClass = entity.getClass();
        String name = aClass.getAnnotation(Table.class).name();
        return StringUtils.isEmpty(name) ? aClass.getSimpleName() : name;
    }


    protected D getEntityDao() {
        try {
            if (entityDao != null) {
                return entityDao;
            }
            // 获取定义的第n个实例变量类型
            Class<D> daoType = GenericsUtils.getSuperClassGenricType(getClass(), 1);
            List<Field> fields = BeanUtils.getFieldsByType(this, daoType);
            if (fields != null && fields.size() > 0) {
                entityDao = (D) BeanUtils.getDeclaredProperty(this, fields.get(0).getName());
            } else {
                entityDao = applicationContext.getBean(daoType);
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return entityDao;
    }

    @Override
    public T save(T t) throws InsertNewInstanceException {
        checkNewInstance(t);
        if (t.getCreateTime() == null) {
            t.setCreateTime(new Date());
        }
        t.setDeleted(false);
        KeyHolder keyHolder = getEntityDao().insertReturnKey(t);
        long aLong = keyHolder.getLong();
        t.setId(aLong);
        return t;
    }

    @Override
    public void saveTemplate(T t) throws InsertNewInstanceException {
        checkNewInstance(t);
        if (t.getCreateTime() == null) {
            t.setCreateTime(new Date());
        }
        t.setDeleted(false);
        getEntityDao().insertTemplate(t);
    }

    @Override
    public void saveBatch(List<T> list) throws InsertNewInstanceException {
        for (T t : list) {
            checkNewInstance(t);
            if (t.getCreateTime() == null) {
                t.setCreateTime(new Date());
            }
            t.setId(null);
            t.setDeleted(false);
        }
        getEntityDao().insertBatch(list);
    }



    @Override
    public int updateById(T t) {
        if (t.getUpdateTime() == null) {
            t.setUpdateTime(new Date());
        }
        return getEntityDao().updateById(t);
    }

    @Override
    public int updateTemplateById(T t) {
        if (t.getUpdateTime() == null) {
            t.setUpdateTime(new Date());
        }
        return getEntityDao().updateTemplateById(t);
    }


    @Override
    public int deleteById(Long id) {
        T single = this.single(id);
        single.setDeleted(true);
        return this.getEntityDao().updateTemplateById(single);
    }

    @Override
    public int deleteByIds(List<Long> key) {
        return getEntityDao().deleteByIds(key.toArray());
    }

    @Override
    public int forceDeleteById(Long id) {
        return getEntityDao().deleteById(id);
    }



    @Override
    public T lock(T o) {
        return getEntityDao().lock(o);
    }


    @Override
    public List<T> all() {
        T entity = getEntity();
        resetQuery(entity);
        return getEntityDao().template(entity);
    }



    @Override
    public List<T> template(T t) {
        resetQuery(t);
        return getEntityDao().template(t);
    }

    @Override
    public T templateOne(T t) {
        if(t.getId()!=null){
            return getEntityDao().single(t.getId());
        }
        resetQuery(t);
        return getEntityDao().templateOne(t);
    }

    @Override
    public List<T> template(T t, int i, int i1) {
        resetQuery(t);
        return getEntityDao().template(t, i, i1);
    }

    @Override
    public void templatePage(PageQuery<T> pageQuery) {
        if (pageQuery.getPageNumber() < 1) {
            pageQuery.setPageNumber(1);
        }
        resetQuery(pageQuery);
        getEntityDao().templatePage(pageQuery);
    }


    @Override
    public long templateCount(T t) {
        resetQuery(t);
        return getEntityDao().templateCount(t);
    }

    @Override
    public List<T> sample(T t) {
        resetQuery(t);
        return getEntityDao().sample(t);
    }

    @Override
    public List<T> batchUpdate(List<T> list) {
        for (T t : list) {
            this.updateTemplateById(t);
        }
        return list;
    }

    private void resetQuery(T t) {
        if (t == null) {
            return;
        }
        t.setCreateTime(null);
        t.setUpdateTime(null);
        if (t.getDeleted()==null){
            t.setDeleted(false);
        }

    }

    private void resetQuery(PageQuery<T> pageQuery) {
        T paras = (T) pageQuery.getParas();
        paras.setDeleted(false);
    }

    @Override
    public T single(Long id) {
        return getEntityDao().single(id);
    }

}
