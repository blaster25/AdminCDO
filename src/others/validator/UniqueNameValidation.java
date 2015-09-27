package others.validator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.service.MunicipalService;

import com.example.admincdo.AdmincdoUI;
import com.vaadin.data.Validator;

public class UniqueNameValidation implements Validator {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	private EntityManager em = emf.createEntityManager();
	private String message;
	private String table;
	
	public UniqueNameValidation(String table, String message) {
		this.table = table;
		this.message = message;
	}
	
	@Override
	public void validate(Object value) throws InvalidValueException {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT t FROM " + table + " t WHERE t.name = :search");
		query.setParameter("search", (String)value);
		if(! query.getResultList().isEmpty() && (String)value != null) {
			throw new InvalidValueException(message);
		}
	}

}
