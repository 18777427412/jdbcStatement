package jdbc_test1;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Test_1 {
	private Connection connection=null;
	
	private void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata?useSSl=true","root","root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private Statement stmt;
	int count=0;
	private void sqladd() {
		init();
		String sql="insert into stu values(9,'李云龙','24',18777452365)";
		try {
			stmt=(Statement) connection.createStatement();
			count=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				if(stmt!=null)stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			
		
			
		}
		if(count>0)System.out.println("操作成功");else{
			System.out.println("操作失败");
		}
	}
	private void sqldelete() {
		init();
		String sql="delete from stu where id=9";
		try {
			stmt=(Statement) connection.createStatement();
			if(stmt.executeUpdate(sql)>0) System.out.println("操作成功");else System.out.println("操作失败");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void sqlupdate() {
		init();
		String sql="update stu set name='王大锤' where id=4";
		try {
			stmt=(Statement) connection.createStatement();
			if(stmt.executeUpdate(sql)>0) System.out.println("操作成功");else System.out.println("操作失败");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sqlQuery() {
		init();
		String sql="select * from stu";
		ResultSet res = null;
		try {
			stmt=(Statement) connection.createStatement();
			res=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(res.next()) {
				int id=res.getInt("id");
				String str=res.getString("name");
				int cid=res.getInt("cid");
				String phone=res.getString("phone");
				System.out.println(id+"\t"+str+"\t"+cid+"\t"+phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			try {
				if(stmt!=null)stmt.close();
			}catch (SQLException e) {
				// TODO: handle exception
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	//使用perparedStatement进行增删改查
		private void peradd() {
			init();
			PreparedStatement pstmt=null;
			int count=0;
			String sql="insert into stu values(?,?,?,?)";
			try {
				pstmt=connection.prepareStatement(sql);//预编译
				pstmt.setInt(1,10);
				pstmt.setString(2, "杨幂");
				pstmt.setInt(3, 30);
				pstmt.setString(4, "1561921615");
				count=pstmt.executeUpdate();
				System.out.println(count);
				//if(count>0)System.out.println("操作成功"); 
				
				
			} catch (SQLException e) {
			//	e.printStackTrace();
				System.out.println("error");
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
				}catch (SQLException e2) {
					// TODO: handle exception
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
			
		}
		private void perdelete() {
			init();
			PreparedStatement pstmt=null;
			int count=0;
			String sql="delete from stu where id=?";
			try {
				pstmt=connection.prepareStatement(sql);//预编译
				pstmt.setInt(1,9);
				count=pstmt.executeUpdate();
				System.out.println(count);
				if(count>0)System.out.println("操作成功"); 
				
				
			} catch (SQLException e) {
			//	e.printStackTrace();
				//System.out.println("error");
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
				}catch (SQLException e2) {
					// TODO: handle exception
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		}
		private void perupdate() {
			init();
			PreparedStatement pstmt=null;
			int count=0;
			String sql="update stu set name=? where id=?";
			try {
				pstmt=connection.prepareStatement(sql);//预编译
				pstmt.setString(1,"王二狗");
				pstmt.setInt(2,4);
				count=pstmt.executeUpdate();
				System.out.println(count);
				if(count>0)System.out.println("操作成功"); 
			} catch (SQLException e) {
				e.printStackTrace();
				//System.out.println("error");
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
				}catch (SQLException e2) {
					// TODO: handle exception
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		}
		private void perQuery() {
			init();
			PreparedStatement pstmt=null;
			ResultSet res=null;
			
			int count=0;
			String sql="select * from stu";
			try {
				pstmt=connection.prepareStatement(sql);//预编译
				res=pstmt.executeQuery();
				while(res.next()) {
					int id=res.getInt("id");
					String name=res.getString("name");
					int cid=res.getInt("cid");
					String phone=res.getString("phone");
					System.out.println(id+"\t"+name+"\t"+cid+"\t"+phone);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				//System.out.println("error");
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
				}catch (SQLException e2) {
					// TODO: handle exception
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		}
	public static void main(String[] args) {
		new Test_1().perQuery();
		
	}

}
