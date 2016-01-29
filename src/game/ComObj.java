package game;

public class ComObj {
	private Msg message;
	private Object object;
	public ComObj(){
		this.message = null;
		this.object = null;
	}
	
	public ComObj(Msg message, Object object) {
		super();
		this.message = message;
		this.object = object;
	}
	public Msg getMessage() {
		return message;
	}
	public void setMessage(Msg message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
