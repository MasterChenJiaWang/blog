package com.chen.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chen.blog.entity.Blogger;
import com.chen.blog.service.BloggerService;
import com.chen.blog.util.CryptographyUtil;
import com.chen.blog.util.MD5Utils;

/**
 * 博主Controller层
 * @author 
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Resource
	private BloggerService bloggerService;
	
//	private String checkcode;
//	/**
//	 * @return the checkcode
//	 */
//	public String getCheckcode() {
//		return checkcode;
//	}
//	/**
//	 * @param checkcode the checkcode to set
//	 */
//	public void setCheckcode(String checkcode) {
//		this.checkcode = checkcode;
//	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 用户登录
	 * @param blogger
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request,String checkcode){
		
		String key = (String)request.getSession().getAttribute("sRand");
		
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key) ){
			Subject subject=SecurityUtils.getSubject();
			String userName = blogger.getUserName();
			String password = blogger.getPassword();
			password=MD5Utils.md5(password);
			UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
			try{
				subject.login(token); // 登录验证
				request.setAttribute("mainPage", "foreground/blogger/list.jsp");
				return "redirect:/admin/main.jsp";
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("blogger", blogger);
				request.setAttribute("errorInfo", "用户名或密码错误！");
				return "login";
			}
		}else{
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "验证码错误！");
			return "login";
		}
		
		
	}
	
	/**
	 *查找博主信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("blogger",bloggerService.find());
		mav.addObject("mainPage", "foreground/blogger/info.jsp");
		mav.addObject("pageTitle","关于博主");
		mav.setViewName("mainTemp");
		return mav;
	}
}
