package controller.littlemeeting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.LmManage;
import model.service.LmManager;

public class ApplyLMController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(ApplyLMController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LmManage lmManage = null;
		
		int littlemeeting_no = Integer.parseInt(request.getParameter("littlemeeting_no"));
		
		LmManager manager = LmManager.getInstance();
		//lmManage = manager.updateApply(littlemeeting_no);
		
		return "/lm/complete.jsp";
	}

}
