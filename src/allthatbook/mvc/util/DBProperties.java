package allthatbook.mvc.util;

/**
 *DB설정 정보를 상수필드로 관리 (새로운 프로젝트할때 값만 바꾸면 재컴파일이 가능하다.)
 */
public interface DBProperties {
	public static final String DRIVER_NAME = "Oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@project-1.cuxjtch2emce.ap-northeast-2.rds.amazonaws.com:1521:ORCL";
	String USER_ID = "admin";
	String USER_PASS = "a12345678";

}
