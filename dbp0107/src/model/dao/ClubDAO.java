package model.dao;

import java.sql.*;
import java.util.*;

import model.Club;

public class ClubDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ClubDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	/* 새로운 Club 생성 */
	public Club create(Club club) throws SQLException {
		String sql = "INSERT INTO CLUB (club_no, deptartment_no, club_name, title, contents, createtime)"
				+ "VALUES (club_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
		Object[] param = new Object[] {club.getDepartment_no(), club.getClub_name(), club.getTitle(), club.getContents()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"club_no"};		// PK 컬럼의 이름     
		
		try {
			jdbcUtil.executeUpdate(key);  // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				club.setClub_no(generatedKey);
			}
			return club;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}
	
	/* 이건 generate key 안하는 create
	 public int create(Club club) throws SQLException {
		String sql = "INSERT INTO Club VALUES (?, ?, ?, ?, SYSDATE)";		
		Object[] param = new Object[] {club.getClub_no(), club.getClub_name(), 
					club.getTitle(), club.getContents(),
						(club.getClub_no()!=0) ? club.getClub_no() : null };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	 */
	
	
	/* 기존 Club 수정 */
	public int update(Club club) throws SQLException {
		String sql = "UPDATE CLUB "
					+ "SET deptartment_no=?, club_name=?, title=?, contents=?"
					+ "WHERE club_no=?";
		Object[] param = new Object[] {club.getDepartment_no(), club.getClub_name(), club.getTitle(), 
						club.getContents(), club.getClub_no()};
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
		
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	/* 기존 Club 삭제 */
	public int remove(int club_no) throws SQLException {
		String sql = "DELETE FROM CLUB WHERE club_no=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {club_no});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	/* DB에 저장된 club 목록들을 불러옴  */
	public List<Club> clubList() throws SQLException {
        String sql = "SELECT club_no, club_name, dept_name"
        		   + "FROM CLUB c LEFT OUTER JOIN DEPARTMENT d ON c.department_no = d.department_no "
        		   + "ORDER BY club_no";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Club> clubList = new ArrayList<Club>();	// Community들의 리스트 생성
			while (rs.next()) {
				Club club = new Club(			// Community 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("club_no"),
						rs.getString("club_name"),
						rs.getString("dept_name"));
				clubList.add(club);				// List에 Community 객체 저장
			}		
			return clubList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/* clubNo로 해당 게시글의 상세 내용을 불러옴   Club 도메인 클래스에 저장해서 반환 */
	public Club showDetail(int club_no) {
		String sql = "SELECT deptartment_no, club_no, club_name, title, contents, createtime FROM CLUB "
     				+ "WHERE clubNo = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {club_no});	// JDBCUtil에 query문과 매개 변수 설정
		Club club = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if(rs.next()) {
				club = new Club(
						rs.getInt("deptartment_no"),
						club_no,
						rs.getString("club_name"),
						rs.getString("title"),
						rs.getString("contents"),
						rs.getDate("createtime"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return club;
	}
	
	
	/* userID를 기반으로  학과 관련 추천 동아리를 불러옴    Club 도메인 클래스에 저장해서 반환 */
	public Club showRecommend(int customer_no) {
		String sql = "SELECT club_name, title, contents, createtime "
					+ "FROM CLUB c LEFT OUTER JOIN CUSTOMER c1 ON c.deptartment_no = c1.deptartment_no "
     				+ "WHERE id = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {customer_no});	// JDBCUtil에 query문과 매개 변수 설정
		Club club = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if(rs.next()) {
				club = new Club(
						rs.getInt("club_no"),
						rs.getInt("deptartment_no"),
						rs.getString("club_name"),
						rs.getString("title"),
						rs.getString("contents"),
						rs.getDate("createtime"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return club;
	}
	
	public boolean existingClub(int club_no) throws SQLException {
		String sql = "SELECT count(*) FROM CLUB WHERE club_no=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {club_no});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
	
	public Club findClub(int club_no) throws SQLException {
        String sql = "SELECT dept_no, deptartment_no, club_name "
        			+ "FROM CLUB "
        			+ "WHERE club_no=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {club_no});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						
				Club club = new Club(		
					club_no,
					rs.getInt("deptartment_no"),
					rs.getString("club_name"),
					null,
					null,
					null);
				return club;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<Club> findClubList() throws SQLException {
		String sql = "SELECT club_no, deptartment_no, club_name"
     		   + "FROM CLUB "
     		   + "ORDER BY club_no";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Club> clubList = new ArrayList<Club>();	// Community들의 리스트 생성
			while (rs.next()) {
				Club club = new Club(			// Community 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("club_no"),
						rs.getInt("deptartment_no"),
						rs.getString("club_name"),
						null,
						null,
						null);
				clubList.add(club);				// List에 Community 객체 저장
			}		
			return clubList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
	public List<Club> findClubList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT club_no, deptartment_no, club_name " 
        		   + "FROM CLUB "
        		   + "ORDER BY club_no";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<Club> clubList = new ArrayList<Club>();
				do {
					Club club = new Club(			// Community 객체를 생성하여 현재 행의 정보를 저장
							rs.getInt("club_no"),
							rs.getInt("deptartment_no"),
							rs.getString("club_name"),
							null,
							null,
							null);
					clubList.add(club);								// 리스트에 User 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return clubList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
	
	
}