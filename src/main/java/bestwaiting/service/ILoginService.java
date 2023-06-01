package bestwaiting.service;

import bestwaiting.model.UserBean;

public interface ILoginService {
	public UserBean Login(String user_name,String user_pwd);
}
