package it.esdebitamiretake.retake_ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ticket.collection.Setting;
import it.esdebitamiretake.retake_ticket.controller.ResourceNotFoundException;
import it.esdebitamiretake.retake_ticket.repo.SettingRepository;


@Service
public class SettingService {

	@Autowired
	SettingRepository settingRepository;
	
	public Setting saveSetting(Setting setting) {
		return this.settingRepository.save(setting);
	}
	
	public Setting findSetting(String id) throws ResourceNotFoundException{
		return settingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("setting",id));
	}

	public List<Setting> findAllSettings() throws ResourceNotFoundException{
		return settingRepository.findAll();
	}
	
	public void delete(String id) throws ResourceNotFoundException {
		settingRepository.delete(findSetting(id));
	}

}