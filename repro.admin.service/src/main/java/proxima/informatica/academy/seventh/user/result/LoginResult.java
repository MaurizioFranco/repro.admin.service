package proxima.informatica.academy.seventh.user.result;

public class LoginResult {

	private boolean loginResponse;
	private String loginMessage;
	
	public LoginResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResult(boolean loginResponse, String loginMessage) {
		super();
		this.loginResponse = loginResponse;
		this.loginMessage = loginMessage;
	}
	public boolean isLoginResponse() {
		return loginResponse;
	}
	public void setLoginResponse(boolean loginResponse) {
		this.loginResponse = loginResponse;
	}
	public String getLoginMessage() {
		return loginMessage;
	}
	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}
	@Override
	public String toString() {
		return "LoginResult [loginResponse=" + loginResponse + ", loginMessage=" + loginMessage + "]";
	}
	
	
}
