package com.yanghui.extend.configure;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.web.ZuulController;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.servlet.ModelAndView;

import com.netflix.zuul.context.RequestContext;

public class MyZuulController extends ZuulController{
	
	private final AsyncTaskExecutor asyncTaskExecutor;
	
	public MyZuulController(AsyncTaskExecutor asyncTaskExecutor) {
		super();
		this.asyncTaskExecutor = asyncTaskExecutor;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("======================调用之前：" + Thread.currentThread().getName() );
		final AsyncContext asyncCtx = request.startAsync();
        this.asyncTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					MyZuulController.this.handleRequestInternal((HttpServletRequest)asyncCtx.getRequest(),
							(HttpServletResponse)asyncCtx.getResponse());
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					asyncCtx.complete();
					RequestContext.getCurrentContext().unset();
				}
			}
		});
        return null;
	}
}
