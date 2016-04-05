package mk.polarcape.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.polarcape.model.Login;
import mk.polarcape.repository.LoginRepository;
import mk.polarcape.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;

		public Login login(String username, String pass) {
			return loginRepository.login(username, pass);
		}
	

}
