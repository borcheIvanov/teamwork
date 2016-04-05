package mk.polarcape.repository;

import mk.polarcape.model.Login;

public interface LoginRepository {

	Login login(String username, String pass);
}
