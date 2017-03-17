package com.chen.blog.service;

import com.chen.blog.entity.Blogger;

/**
 * 博主Service接口
 * @author 
 *
 */
public interface BloggerService {

	/**
	 * 查询博主信息
	 * @return
	 */
	public Blogger find();
	
	/**
	 *通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName);
	
	/**
	 *更新博主信息
	 * @param blogger
	 * @return
	 */
	public Integer update(Blogger blogger);
}
