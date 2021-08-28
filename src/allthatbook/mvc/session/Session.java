package allthatbook.mvc.session;

import java.util.HashMap;
import java.util.Map;

import allthatbook.mvc.model.dto.Book;

/**
 * ����� ��ü
 * �α��ε� ������� ������ �α����� �����Ǵ� ����
 * Session ��ü�� �����ؼ� ������Ų��.
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
	
	//�߰�
	public void setAttribute(String name, Object value) {//cart , List<Book>
		attributes.put(name,value);
	}
	
	//��ȸ(Map�� key�� �ش��ϴ� value ã��)
	public Object getAttribute(String name) {//cart
		return attributes.get(name);
	}
	
	//����(��ٱ��ϸ� ���� ����Ѵ�)
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
	 * ���� ��ü��� ���� hashCode�� ���ƾ��ϰ�,
	 * equlas�� ����� true�����Ѵ�.
	 * 
	 *  hash�ڵ尡 �ٸ��� ������ �ٸ� ��ü
	 *  hash�ڵ尡 ������ ���� ��ü�ϼ���, �ٸ� ��ü�ϼ��� �ִ�.
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
	
	
	//�ڷᱸ�� �Ҷ�
	//Set, Map-key �ߺ� �ȵȴ�.
	//��� �ߺ����� �ƴ����� üũ�ϳ�?
	//hashcode�� ���ϰ� ������ equals�� ���ؼ� true �����ϸ� ����..
	
	
	
	
}
