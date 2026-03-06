package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;


//ユーザー情報　controller
@Controller
public class UserController {
	/*
	 * ユーザー新規登録画面を表示
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String displayAdd(Model model) {
		model.addAttribute("userRequest",new UserRequest());
		return "user/add";
	}
	
	/*
	 * ユーザー新規登録
	 ** @param userRequest リクエストデータ
    * @param result BindingResultl
    * @param model Model
    * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/user/create",method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute UserRequest userRequest,BindingResult result,Model model) {
		if (result.hasErrors()) {
			List<String>errorList = new ArrayList<String>();
			for(ObjectError error: result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "user/add";
		}
		// ここでユーザー情報を登録する
        // 登録処理は省略
		return "user/add";
	}
}
