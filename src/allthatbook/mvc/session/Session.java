package allthatbook.mvc.session;

import java.util.HashMap;
import java.util.Map;

import allthatbook.mvc.model.dto.Book;

/**
 * 사용자 객체
 * 로그인된 사용자의 정보를 로그인이 유지되는 동안
 * Session 객체에 저장해서 유지시킨다.
 * */
public class Session {
	private String sessionId;
	private Map<String,Object> attributes; //key: cart, value: book
	
	
	public Session() {}
	public Session(String sessionId) {
		this.sessionId = sessionId;
		attributes = new HashMap<>();
	}
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	//추가
	public void setAttribute(String name, Object value) {//cart , List<Book>
		attributes.put(name,value);
	}
	
	//조회(Map에 key에 해당하는 value 찾기)
	public Object getAttribute(String name) {//cart
		return attributes.get(name);
	}
	
	//제거(장바구니를 비울대 사용한다)
	public void removeAttribute(String name) {//cart
		attributes.remove(name);
	}
		
		
	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", attributes=" + attributes + "]"+"\n";
	}
	
	
	@Override
	public int hashCode() {
		return sessionId.hashCode();
	}
	
	/**
	 * 같은 객체라는 뜻은 hashCode가 같아야하고,
	 * equlas의 결과가 true여야한다.
	 * 
	 *  hash코드가 다르면 무조건 다른 객체
	 *  hash코드가 같으면 같은 객체일수도, 다른 객체일수도 있다.
	 * */
	@Override
	public boolean equals(Object obj) {
		Session other = (Session) obj;
		if(sessionId.equals(other.sessionId)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Session other = (Session) obj;
//		if (sessionId == null) {
//			if (other.sessionId != null)
//				return false;
//		} else if (!sessionId.equals(other.sessionId))
//			return false;
//		return true;
//	}
	
	
	//자료구조 할때
	//Set, Map-key 중복 안된다.
	//어떻게 중복인지 아닌지를 체크하나?
	//hashcode를 비교하고 같으면 equals를 비교해서 true 리턴하면 같다..
	
	
	
	
}
