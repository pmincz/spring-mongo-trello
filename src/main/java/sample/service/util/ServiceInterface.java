package sample.service.util;

import java.util.List;

public interface ServiceInterface<T, U> {

	public List<T> getAll();
	public T getById(Long id);
	public T create(U entry);
	public void delete(Long id);
	public T update(Long id, U entry);

}
