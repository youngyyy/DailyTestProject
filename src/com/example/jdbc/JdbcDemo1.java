package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
public class JdbcDemo1 {
     public static void main(String[] args){
    	// List<Integer> oldMemberList = new ArrayList<Integer>();
    	 //System.out.println("oldMemberList.size(): " + oldMemberList.size());
    	//  method4();
    	//method5(); 
    	// method6();
    	 //method7();
    	 method8();
     }
     //添加角色权限
     public static void method1(){
    	 Connection conn;
  		Statement sql;
  		ResultSet rs;
  		try{
  		   Class.forName("oracle.jdbc.driver.OracleDriver");
  		}
  		catch(ClassNotFoundException e){
  		   System.out.println("驱动程序加载错误");
  		}
  		
  		try{
  		  conn = DriverManager.getConnection("jdbc:oracle:thin:@//param_ip:param_port/codb","username","password");
  		  sql = conn.createStatement();
  		  int i=0;
  		  
  		  
  		  //查询部门管理员权限
  		  rs=sql.executeQuery("select distinct authority_id from pub_roles_authorities where role_id=2");
  		  //输出结果集
  		 // System.out.println("deptAdmin rs size " + rs.getFetchSize());
  		  int deptAdminAuthSize = rs.getFetchSize();
  		  List<Integer> deptAdminList = new ArrayList<Integer>();
  		  
  		  i=0;
  		  while(rs.next()){
  			  
  			 Integer temp = rs.getInt(1) ;
  			deptAdminList.add(temp);
  			 i++;
  		  }
  		   
  		  System.out.println("dept Admin i: " + i +"; deptAdminList: " + deptAdminList.size());
  		  
  		   
  		  
  		  
  		  //查询作业队长权限
  		  //输出结果集
  		  rs=sql.executeQuery("select distinct authority_id from pub_roles_authorities where role_id=3");
  		
 		  //System.out.println("rs teamLeader size " + rs.getFetchSize());
 		  int teamLeaderAuthSize = rs.getFetchSize();
 		  List<Integer> teamLeaderList = new ArrayList<Integer>();
 		  i=0;
 		  while(rs.next()){
 			  
 			 Integer temp = rs.getInt(1) ;
 			 teamLeaderList.add(temp);
 			 i++;
 		  }
 		   
 		  System.out.println("teamLeader i: " + i +"; teamLeaderList: " + teamLeaderList.size());
 		  
 		  //查找出部门管理员有 作业队长没有的权限
 		  List<Integer> addTLList = new ArrayList<Integer>();
 		  
 		  for(int k=0; k<deptAdminList.size(); k++){
 			  Integer elem = deptAdminList.get(k);
 			  if(!teamLeaderList.contains(elem)){
 				  System.out.print(elem+",");
 				  addTLList.add(elem);
 			  }
 		  }
 		  System.out.print("\n");
 		  System.out.println("addTLList.size(): " + addTLList.size());
 		  //插入
 		  for(int j=0; j<addTLList.size(); j++){
 			  String newSql = "insert into PUB_ROLES_AUTHORITIES values(SEQ_PUB_ROLES_AUTHORITIES.nextval"
 		  +","+ 3 +"," + addTLList.get(j)+")";
 			  sql.addBatch(newSql);
 		  }
 		  int[] resultTL = sql.executeBatch();
 		  System.out.println("resultTL length: " + resultTL.length);
 		  conn.commit();
 		  
 		  //查询车队主管权限
  		  //输出结果集
 		  rs=sql.executeQuery("select distinct authority_id from pub_roles_authorities where role_id=8");
 		  int carAdminAuthSize = rs.getFetchSize();
 		  List<Integer> carAdminList = new ArrayList<Integer>();
 		  i=0;
 		  while(rs.next()){
 			  
 			 Integer temp = rs.getInt(1) ;
 			 carAdminList.add(temp);
 			 i++;
 		  }
 		   
 		  System.out.println("carAdmin i: " + i +"; carAdminList: " + carAdminList.size());
 		//查找出部门管理员有 车队主管没有的权限
 		  List<Integer> addCAList = new ArrayList<Integer>();
 		  
 		  for(int k=0; k<deptAdminList.size(); k++){
 			  Integer elem = deptAdminList.get(k);
 			  if(!carAdminList.contains(elem)){
 				  System.out.print(elem+",");
 				  addCAList.add(elem);
 			  }
 		  }
 		  System.out.print("\n");
 		  System.out.println("addCAList.size(): " + addCAList.size());
 		  //插入
 		  for(int j=0; j<addCAList.size(); j++){
 			  String newSql = "insert into PUB_ROLES_AUTHORITIES values(SEQ_PUB_ROLES_AUTHORITIES.nextval"
 		  +","+ 8 +"," + addCAList.get(j)+")";
 			  sql.addBatch(newSql);
 		  }
 		  int[] resultCA = sql.executeBatch();
 		  System.out.println("resultCA length: " + resultCA.length);
 		  conn.commit();
 		  
 		  
 		  conn.close();
  		}catch(SQLException el){
  			System.out.println(el);
  			System.out.println("查询错误");
  		}
     	 
     }
     
     //将pub_users中的队长队员关系移植到pub_relationship表中
     public static void method2(){
    	Connection conn;
   		Statement sql;
   		ResultSet rs;
   		try{
   		   Class.forName("oracle.jdbc.driver.OracleDriver");
   		}
   		catch(ClassNotFoundException e){
   		   System.out.println("驱动程序加载错误");
   		}
   		
   		try{
   		  conn = DriverManager.getConnection("jdbc:oracle:thin:@//param_ip:param_port/codb","username","password");
   		  sql = conn.createStatement();
   		  //查询人员id，team_leader_id,role_id
   		  rs=sql.executeQuery("select distinct pu.id,pu.team_leader_id,pur.role_id "
   		  		+ "from pub_users pu left join pub_users_roles pur on pu.id = pur.user_id");
   		  Integer count = 0;
   		  List<Relation> resultList = new ArrayList<Relation>();
   		  while(rs.next()){
			    Relation temp = new Relation();
			    temp.setEmp_id(rs.getInt(1));
			    Integer leader_id = (rs.getInt(2)==0?null:rs.getInt(2));
			    temp.setLeader_id(leader_id);
			    temp.setType(rs.getInt(3));
			    System.out.println(rs.getInt(1) +" " +(rs.getInt(2)==0?null:rs.getInt(2)) + " " +rs.getInt(3) );
			    
			    resultList.add(temp);
			    count++;
		   }
   		  System.out.println("总个数count: " + count);
   		  
   		  //将查询得到的人员队长信息插入到pub_relationship表中
   		  for(int j=0; j<resultList.size(); j++){
 			  String newSql = "insert into PUB_RELATIONSHIP values(SEQ_PUB_RELATIONSHIP_ID.nextval"
 		  +","+ resultList.get(j).getEmp_id() +"," + resultList.get(j).getLeader_id()+","
 		 + resultList.get(j).getType() +")";
 			  
 			  sql.addBatch(newSql);
 		  }
 		  int[] resultCA = sql.executeBatch();
 		  System.out.println("resultCA length: " + resultCA.length);
 		  conn.commit();
 		  
 		  
 		  conn.close();
 		  
   		}catch(SQLException el){
  			System.out.println(el);
  			System.out.println("查询错误");
  		}
     }
     
     //为驾驶员添加和作业队员一样的权限，为安全队长添加和作业队长一样的权限
     public static void method3(){
    	 Connection conn;
   		Statement sql;
   		ResultSet rs;
   		try{
   		   Class.forName("oracle.jdbc.driver.OracleDriver");
   		}
   		catch(ClassNotFoundException e){
   		   System.out.println("驱动程序加载错误");
   		}
   		
   		try{
   		  conn = DriverManager.getConnection("jdbc:oracle:thin:@//param_ip:param_port/codb","username","password");
   		  sql = conn.createStatement();
   		  int i=0;
   		  
   		  
   		  //查询作业队员权限
   		  rs=sql.executeQuery("select distinct authority_id from pub_roles_authorities where role_id=4");
   		  //输出结果集
   		 // System.out.println("deptAdmin rs size " + rs.getFetchSize());
   		  
   		  List<Integer> memberList = new ArrayList<Integer>();
   		  
   		  i=0;
   		  while(rs.next()){
   			  
   			 Integer temp = rs.getInt(1) ;
   			memberList.add(temp);
   			 i++;
   		  }
   		   
   		  System.out.println("memberList end i: " + i +"; memberList size: " + memberList.size());
   		  
   		   
   		  
   		  
   		  //查询作业队长权限
   		  //输出结果集
   		  rs=sql.executeQuery("select distinct authority_id from pub_roles_authorities where role_id=3");
   		
  		  //System.out.println("rs teamLeader size " + rs.getFetchSize());
  	//	  int teamLeaderAuthSize = rs.getFetchSize();
  		  List<Integer> teamLeaderList = new ArrayList<Integer>();
  		  i=0;
  		  while(rs.next()){
  			  
  			 Integer temp = rs.getInt(1) ;
  			 teamLeaderList.add(temp);
  			 i++;
  		  }
  		   
  		  System.out.println("teamLeader end i: " + i +"; teamLeaderList: " + teamLeaderList.size());
  		  
  		  
  		  //为驾驶员添加权限
  		  for(int j=0; j<memberList.size(); j++){
  			  String newSql = "insert into PUB_ROLES_AUTHORITIES values(SEQ_PUB_ROLES_AUTHORITIES.nextval"
  		  +","+ 10 +"," + memberList.get(j)+")";
  			  sql.addBatch(newSql);
  			  
  		  }
  		  int[] resultDriver = sql.executeBatch();
  		  System.out.println("resultDriver length: " + resultDriver.length);
  		  conn.commit();
  		  
  		  sql.clearBatch();
  		  //为安全队长添加权限
  		  for(int j=0; j<teamLeaderList.size(); j++){
  			  String newSql = "insert into PUB_ROLES_AUTHORITIES values(SEQ_PUB_ROLES_AUTHORITIES.nextval"
  		  +","+ 9 +"," + teamLeaderList.get(j)+")";
  			  sql.addBatch(newSql);
  		  }
  		  int[] resultLeader = sql.executeBatch();
  		  System.out.println("resultLeader length: " + resultLeader.length);
  		  conn.commit();
  		  
  		  conn.close();
   		}catch(SQLException el){
   			System.out.println(el);
   			System.out.println("查询错误");
   		}
      	 
     }
     
     //20150710  将高快用户密码TBL_USER_NEW加密存储
     public static void  method4(){
    	    Connection conn;
    		Statement sql;
    		ResultSet rs;
    		try{
    		   Class.forName("oracle.jdbc.driver.OracleDriver");
    		}
    		catch(ClassNotFoundException e){
    		   System.out.println("驱动程序加载错误");
    		}
    		
    		try{
    		  conn = DriverManager.getConnection("jdbc:oracle:thin:@//param_ip:param_port/amap","username","password");
    		  sql = conn.createStatement();
    		  int i=0;
    		  
    		  
    		  //查询所有人员信息
    		  rs=sql.executeQuery("select userid,username,password from TBL_USER_NEW where userid<4522 AND userid>150 order by userid");
    		  //输出结果集
    		 // System.out.println("deptAdmin rs size " + rs.getFetchSize());
    		  
    		  List<Integer> memberList = new ArrayList<Integer>();
    		  
    		  i=0;
    		  while(rs.next()){
    			  
    			 Integer userId = rs.getInt(1) ;
    			 String userPassword = DigestUtils.shaHex(rs.getString(3));
    			 
    			 //System.out.println("userPassword: " + userPassword);
    			 
    			 
    			 String newSql = "update TBL_USER_NEW SET password='"
    			   		  + userPassword +"' WHERE userid=" + userId;
    			
    			 System.out.println("i: " + i + "; newSql: " + newSql);
    			 
    			 sql.addBatch(newSql);
    			
    			/* if(i== 100){
    			    break;
    			 }*/
    			 i++;
    		  }
    		   
    		  System.out.println("rs count: " + i);
    		 
	   		  int[] resultLeader = sql.executeBatch();
	   		  System.out.println("result length: " + resultLeader.length);
	   		  conn.commit();
	   		  
	   		  conn.close();
    		}catch(SQLException el){
    			System.out.println(el);
    			System.out.println("查询错误");
    		}
     }
     
     //20150721  为合理化建议中的用户添加角色
     public static void  method5(){
 	    Connection conn;
 		Statement sql;
 		ResultSet rs;
 		try{
 		   Class.forName("oracle.jdbc.driver.OracleDriver");
 		}
 		catch(ClassNotFoundException e){
 		   System.out.println("驱动程序加载错误");
 		}
 		
 		try{
 		  conn = DriverManager.getConnection("jdbc:oracle:thin:@//param_ip:param_port/orcl","username","password");
 		  
 		  
 		  sql = conn.createStatement();
 		  int i=0;
 		  
 		  
 		  //查询所有人员信息
 		  rs=sql.executeQuery("select id from PUB_USER where enabled = 1 AND id>1 order by id");
 		  //输出结果集
 		 // System.out.println("deptAdmin rs size " + rs.getFetchSize());
 		  
 		  List<Integer> memberList = new ArrayList<Integer>();
 		  
 		  i=0;
 		  while(rs.next()){
 			  
 			 Integer userId = rs.getInt(1) ;
 			
 			 String newSql = "INSERT INTO PUB_USER_ROLE(id,user_id,role_id) VALUES(SEQ_PUB_USER_ROLE_ID.nextval,"
 			   		  + userId +",2)";
 			
 			 System.out.println("i: " + i + "; newSql: " + newSql);
 			 
 			 sql.addBatch(newSql);
 			
 			/* if(i== 100){
 			    break;
 			 }*/
 			 i++;
 		  }
 		   
 		  System.out.println("rs count: " + i);
 		 
	   		  int[] resultLeader = sql.executeBatch();
	   		  System.out.println("result length: " + resultLeader.length);
	   		  conn.commit();
	   		  
	   		  conn.close();
 		}catch(SQLException el){
 			System.out.println(el);
 			System.out.println("查询错误");
 		}
  }//method5
     
     //20150818  为容量预测系统的角色添加资源
     public static void  method6(){
 	    Connection conn;
 		Statement sql;
 		ResultSet rs;
 		try{
 		   Class.forName("com.mysql.jdbc.Driver");
 		}
 		catch(ClassNotFoundException e){
 		   System.out.println("驱动程序加载错误");
 		}
 		
 		try{
 		 // Class.forName("com.mysql.jdbc.Driver");
 	      String url = "jdbc:mysql://param_ip:param_port/autonavi?user=user&password=pass&useUnicode=true&characterEncoding=utf-8";
 		  conn = DriverManager.getConnection(url);
 		  
 		  conn.setAutoCommit(false);//关闭mysql的自动提交
 		  
 		  sql = conn.createStatement();
 		  int i=0;
 		  
 		  
 		  //查询所有人员信息
 		  rs=sql.executeQuery("select resource_id from sysresources_table where enabled = 1 order by resource_id");
 		  //输出结果集
 		 // System.out.println("deptAdmin rs size " + rs.getFetchSize());
 		  
 		  List<Integer> memberList = new ArrayList<Integer>();
 		  
 		  i=0;
 		  while(rs.next()){
 			  
 			 Integer resourceId = rs.getInt(1) ;
 			
 			/* if(!resourceId.equals(15) && !resourceId.equals(17) && !resourceId.equals(18)
 					 && !resourceId.equals(11) && !resourceId.equals(13)){ //项目经理*/
 			/*if(!resourceId.equals(7) && !resourceId.equals(8) && !resourceId.equals(9)
					 && !resourceId.equals(11) && !resourceId.equals(13)){ //数据管理员 */	
 			if(!resourceId.equals(15) && !resourceId.equals(17) && !resourceId.equals(18)
 					&& !resourceId.equals(7) && !resourceId.equals(8) && !resourceId.equals(9)
					 && !resourceId.equals(11) && !resourceId.equals(13)){//普通用户
 			     String newSql = "INSERT INTO sys_role_resource(role_id,resource_id) VALUES(4,"
	 			   		  + resourceId +")";
	 			
	 			 System.out.println("i: " + i + "; newSql: " + newSql);
	 			 
	 			 sql.addBatch(newSql);
 			 }
 			 i++;
 		  }
 		   
 		  System.out.println("rs count: " + i);
 		 
	   	   int[] resultLeader = sql.executeBatch();
	   	  System.out.println("result length: " + resultLeader.length);
	   	  conn.commit();
	   		  
	   	  conn.close();
 		}catch(SQLException el){
 			System.out.println(el);
 			System.out.println("查询错误");
 		}
  }//method6
     
     //20151016   检测项目管理评价系统数据库连接
     public static void  method7(){
 	    Connection conn;
 		Statement sql;
 		ResultSet rs;
 		try{
 		   Class.forName("com.mysql.jdbc.Driver");
 		}
 		catch(ClassNotFoundException e){
 		   System.out.println("驱动程序加载错误");
 		}
 		
 		try{
 		 // Class.forName("com.mysql.jdbc.Driver");
 	      String url = "jdbc:mysql://param_ip:param_port/alidata_autonavi_1?user=user&password=pass&useUnicode=true&characterEncoding=utf-8";
 		  conn = DriverManager.getConnection(url);
 		  
 		  conn.setAutoCommit(false);//关闭mysql的自动提交
 		  
 		  sql = conn.createStatement();
 		  int i=0;
 		  
 		  
 		  //查询所有人员信息
 		  rs=sql.executeQuery("select * from demand_score_item where enabled = 1");
 		
 		  i=0;
 		  while(rs.next()){
 			  
 			 Integer resourceId = rs.getInt(1) ;
 			
 			i++;
 		  }
 		   System.out.println("count: " + i);
	   	  conn.commit();
	   		  
	   	  conn.close();
 		}catch(SQLException el){
 			System.out.println(el);
 			System.out.println("查询错误");
 		}
  }//method7
     
     //20151030  查询需要的客户类型列表
     public static void method8(){
	    	Connection conn;
	   		Statement sql;
	   		ResultSet rs;
	   		try{
	   		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   		}
	   		catch(ClassNotFoundException e){
	   		   System.out.println("驱动程序加载错误");
	   		}
	   		
	   		try{
	   		  conn = DriverManager.getConnection("jdbc:oracle:thin:@//param_ip:param_port/amap","user","pass");
	   		  sql = conn.createStatement();
	   		  int i=0;
	   		  
	   		  
	   		  //查询需要处理的用户类型
	   		  rs=sql.executeQuery("select distinct orgid,orgname from TBL_ORGANIZE WHERE orgname not in('Snowman','Amap','amap',"
	   		  		+ "'高频无果数据','车联网用户行为分析','用户行为分析','道路报错','Autonavi','高德员工','高快')");
	   		  //输出结果集
	   		 // System.out.println("deptAdmin rs size " + rs.getFetchSize());
	   		 
	   		  List<Integer> orgIDList = new ArrayList<Integer>();
	   		  
	   		  i=0;
	   		  while(rs.next()){
	   			  
	   			 Integer temp = rs.getInt(1) ;
	   			 orgIDList.add(temp);
	   			 i++;
	   		  }
	   		   
	   		  System.out.println("orgIDList count: " + i +"; orgIDList: " + orgIDList.size());
	   		  
	   		 //20151030 插入用户组织关系
	 		  for(int j=0; j<orgIDList.size(); j++){
	 			  String newSql = "insert into USER_ORGANIZATION_RELATIONSHIP values(SEQ_USER_ORG_RELATIONSHIP_ID.nextval"
	 		  +","+ 3541 +"," + orgIDList.get(j) + 2 +")";  //testuser
	 			  sql.addBatch(newSql);
	 		  }
	 		  int[] resultCA = sql.executeBatch();
	 		  System.out.println("resultCA length: " + resultCA.length);
	 		  conn.commit();
	 		  
	 		  
	  		  
	  		  conn.close();
	   		}catch(SQLException el){
	   			System.out.println(el);
	   			System.out.println("查询错误");
	   		}
      	 
     }
  
}
class Relation{
	 private Integer id;
	 private Integer emp_id;
	 private Integer leader_id;
	 private Integer type;
	 public Relation(){}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLeader_id() {
		return leader_id;
	}
	public void setLeader_id(Integer leader_id) {
		this.leader_id = leader_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
};
