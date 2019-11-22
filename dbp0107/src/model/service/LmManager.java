package model.service;

import java.util.List;
import java.sql.SQLException;

import model.Lm;
import model.LmManage;
import model.dao.LmDAO;
import model.dao.LmManageDAO;

public class LmManager {
	private static LmManager lmManager = new LmManager();
	private LmDAO lmDAO;
	private LmManageDAO lmManageDAO;
	
	private LmManager() {
		try {
			lmManageDAO = new LmManageDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static LmManager getInstance() {
		return lmManager;
	}
	
	/*public LmManage updateApply(int littlemeeting) throws SQLException {
		return lmManageDAO.updateApply(littlemeeting);
		
	}*/
	
	/*public int updateApply(int littlemeeting_no) throws SQLException {
		
		
		return lmManageDAO.updateApply(littlemeeting_no);
		
	}*/
	public int updateCancel(int littlemeeting_no) throws SQLException {
		return lmManageDAO.updateCancel(littlemeeting_no);
	}

	//List <Lm> findLittleMeetingList(int currentPage, int countPerPage) 
		//ShowLittleMeetingDetail(int clubNo) 
	
	public List<LmManage> findLmManageList() throws SQLException {
		return lmManageDAO.findLmManageList();
	}
	
	public LmManageDAO getLmManageDAO() {
		return this.lmManageDAO;
	}

	public LmManage findLM(int littlemeeting_no) {
		// TODO Auto-generated method stub
		return null;
	}
}
