package mk.polarcape.service;

import mk.polarcape.model.Login;

public interface LoginService {
	Login login(String username, String pass);
}
