package com.pzj.core.imgsrv.restful;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pzj.core.imgsrv.constants.LoginConstant;
import com.pzj.core.imgsrv.service.ImgService;
import com.pzj.framework.context.Result;

@Controller
public class ImgLoginController {

	@Autowired
	private ImgService imgService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> upload(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password, HttpServletRequest request) {
		Result<Boolean> result = imgService.login(username, password);
		if (result.isOk()) {
			if (result.getData()) {
				request.getSession().setAttribute(LoginConstant.USER_SESSION_KEY, username);
				return result;
			}
			result.setErrorMsg("用户名或密码错误");
		}
		result.setErrorMsg("系统异常，请联系管理员");
		return result;
	}

}
