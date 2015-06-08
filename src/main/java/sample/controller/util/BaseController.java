package sample.controller.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.service.util.ServiceInterface;

public abstract class BaseController<T, U> {

	@Autowired
	private ServiceInterface<T, U> service;
	
	/**
	 * Get all
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public List<T> getAll() {
		return service.getAll();
	}
	
	/**
	 * Get by id
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public T getBoard(@PathVariable Long id) {
		return service.getById(id);
	}
	
	/**
	 * Create by entry
	 * @param entry
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<T> create(@RequestBody U entry) {
		return new ResponseEntity<T>(service.create(entry), HttpStatus.CREATED);
	}

	/**
	 * Delete by id
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	/**
	 * Update by id
	 * @param id
	 * @param entry
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public T update(@PathVariable Long id, @RequestBody U entry) {
		return service.update(id, entry);
	}

}
