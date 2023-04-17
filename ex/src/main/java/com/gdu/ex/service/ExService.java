package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;

public interface ExService {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
}
