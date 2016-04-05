package mk.polarcape.repository;

import mk.polarcape.model.Login;

public interface LoginRepositroy {
	Login login(String username, String pass);
}
