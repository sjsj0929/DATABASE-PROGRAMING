package model;
/* littlemeeting_manage 테이블과 대응 */

public class LmManage {
	private int littlemeeting_no;
	private int customer_no;
	private int join_check; //신청시 1 , 취소하면 0
	
	
	public LmManage(int littlemeeting_no, int customer_no, int join_check) {
		this.littlemeeting_no = littlemeeting_no;
		this.customer_no = customer_no;
		this.join_check = join_check;
	}
	
	public int getLittlemeeting_no() {
		return littlemeeting_no;
	}
	public void setLittlemeeting_no(int littlemeeting_no) {
		this.littlemeeting_no = littlemeeting_no;
	}
	public int getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(int customer_no) {
		this.customer_no = customer_no;
	}
	public int getJoin_check() {
		return join_check;
	}
	public void setJoin_check(int join_check) {
		this.join_check = join_check;
	}

	@Override
	public String toString() {
		return "littlemeeting_manage [littlemeeting_no=" + littlemeeting_no + ", customer_no=" + customer_no + ", join_check=" + join_check + "]";
	}
	
}
