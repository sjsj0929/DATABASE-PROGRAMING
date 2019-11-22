package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Lm;
import model.LmManage;

public class LmManageDAO {
	private JDBCUtil jdbcUtil = null;
	
	public LmManageDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	public int create(LmManage lmManage) throws SQLException {
		String sql = "INSERT INTO littemeeting_manage VALUES (?, ?, ?)";		
		Object[] param = new Object[] {lmManage.getLittlemeeting_no(), lmManage.getCustomer_no(),
			lmManage.getJoin_check()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {    
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}
	
	//��û - join_check = 1
	public int updateApply(int littlemeeting_no) throws SQLException {	//join_check �� 1�� ����, count + 1 
		String sql = "INSERT INTO littemeeting_manage VALUES (?, ?, ?)";		
		Object[] param = new Object[] {littlemeeting_no, 9, 1};				
		jdbcUtil.setSqlAndParameters(sql, param);
		
		
		/*String sql = "UPDATE littemeeting_manage "
					+ "SET join_check = 1 "
					+ "WHERE littlemeeting_no = ? ";		
		Object[] param = new Object[] {littlemeeting_no};	*/			
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {    
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
	
		return 0;
		
	}
	
	//��û ��� - join_check = 0
	public int updateCancel(int littlemeeting_no) throws SQLException {
		String sql = "UPDATE littemeeting_manage "
					+ "SET join_check = 0 "
					+ "WHERE littlemeeting_no = ? ";		
		Object[] param = new Object[] {littlemeeting_no};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {    
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
	
		return 0;
		
	}
	
	//�Ҹ��� �ο� count
	public int getCountLM(int littlemeeting_id) {
		String sql = "SELECT COUNT(join_check) FROM littlemeeting_manage "
     				+ "WHERE littlemeeting_id = ? and join_check = 1";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {littlemeeting_id});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			rs.next();										
			return rs.getInt(1);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return 0;
	}
	
	public List<LmManage> findLmManageList() throws SQLException {
        String sql = "SELECT littlemeeting_no, customer_no, join_check " 
        		   + "FROM littlemeeting_manage "
        		   + "ORDER BY littlemeeting_no";
	
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<LmManage> lmManageList = new ArrayList<LmManage>();	
			while (rs.next()) {
				LmManage lmManage = new LmManage(	
					rs.getInt("littlemeeting_no"),
					rs.getInt("customer_no"),
					rs.getInt("join_check"));
				lmManageList.add(lmManage);				
			}		
			return lmManageList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	
}
