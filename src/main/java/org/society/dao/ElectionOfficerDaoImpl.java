package org.society.dao;

import java.util.List;
import java.util.Optional;

import org.society.entities.ElectionOfficer;
import org.society.exceptions.DuplicateEntityFoundException;
import org.society.exceptions.ElectionOfficerNotFoundException;
import org.society.repository.ElectionOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElectionOfficerDaoImpl implements ElectionOfficerDao {

	@Autowired
	private ElectionOfficerRepository repository;

	@Override
	public ElectionOfficer save(ElectionOfficer officer) {
		if (repository.existsById(officer.getId())) {
			throw new DuplicateEntityFoundException("Save operation", "Duplicate Election Officer can not be saved");
		}
		return repository.save(officer);

	}

	@Override
	public ElectionOfficer update(ElectionOfficer officer) {
		if(repository.existsById(officer.getId())) {

		return repository.save(officer);}
		return null;
	}

	@Override
	public boolean delete(long officerId) {

		if (repository.existsById(officerId)) {
			repository.deleteById(officerId);
			return true;
		}
		return false;

	}

	@Override
	public ElectionOfficer getElectionOfficerById(long officerId) {
		Optional<ElectionOfficer> officer = repository.findById(officerId);
		if (officer.isPresent()) {
			return officer.get();
		}
		return null;
	}

	@Override
	public List<ElectionOfficer> getElectionOfficerList() {
		List<ElectionOfficer> list = (List<ElectionOfficer>) repository.findAll();

		return list;
	}

}
