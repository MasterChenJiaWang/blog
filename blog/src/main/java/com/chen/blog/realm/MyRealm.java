package com.chen.blog.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.chen.blog.entity.Blogger;
import com.chen.blog.service.BloggerService;

/**
 * 自定义Realm
 *
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private BloggerService bloggerService;
	
	/**
	 * 为当限前登录的用户授予角色和权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		String userName=(String)token.getPrincipal();
//		Blogger blogger=bloggerService.getByUserName(userName);
//		if(blogger!=null){
//			SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger); // 当前用户信息存到session中
//			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),"xx");
//			return authcInfo;
//		}else{
//			return null;				
//		}
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String userName=upToken.getUsername();
		Blogger blogger = bloggerService.getByUserName(userName);
		if(blogger!=null){
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),this.getClass().getSimpleName());
			
			return authenticationInfo;
		}else{
			return null;
		}
	}

}