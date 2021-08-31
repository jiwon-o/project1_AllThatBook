package allthatbook.mvc.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	public void setAttribute(String name, Object value) {//cart , List<Book> ---> cart, Cart��ü
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
		StringBuilder builder = new StringBuilder();
		
		builder.append("*** '");
		builder.append(sessionId);
		builder.append("' �� ���� �� ***]\n");
		builder.append("[");
		Set<String> keySet = attributes.keySet();
		for (Object key : keySet) {
			builder.append(attributes.get(key));
		}
		return builder.toString();
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
	
	
}
