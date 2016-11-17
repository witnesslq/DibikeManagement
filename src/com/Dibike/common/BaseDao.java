package com.Dibike.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.Dibike.entity.Manage_role;


public interface BaseDao<T> {

	/**
	 * ����һ������
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * ɾ��һ������
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * ɾ�����
	 * 
	 * @param hql
	 * @return Object
	 */
	public void delete(String hql);

	/**
	 * ����һ������
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * �������¶���
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * ��ѯ
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * ��ѯ����
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * ��ѯ����
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * ��ѯ����(���ҳ)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            ��ѯ�ڼ�ҳ
	 * @param rows
	 *            ÿҳ��ʾ������¼
	 * @return
	 */
	public List<T> find(String hql, Integer page, Integer rows);

	/**
	 * ��ѯ����(���ҳ)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows);

	/**
	 * ���һ������
	 * 
	 * @param c
	 *            ��������
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * ���һ������
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * ���һ������
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @return ��Ӧ��Ŀ
	 */
	public Integer executeHql(String hql);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @param param
	 * @return ��Ӧ��Ŀ
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);


	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @param param
	 */
	public void into(String hql, String param);

	/***
	 * ���������ѯ
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria);

	/***
	 * ��ҳ��ѯ
	 * 
	 * @param detachedCriteria
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria,
			final int pageNum, final int pageSize);

}
