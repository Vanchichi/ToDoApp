package ru.sber.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.entity.Archive;
import ru.sber.entity.Task;
import ru.sber.repository.ArchiveRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArchiveService {

    private final  ArchiveRepository archiveRepository;

    @Autowired
    public ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    public Archive getArchiveById(Long id ){
       return archiveRepository.findById(id).get();
    }

    public Long addToArchive(Task task){
        Archive archive = new Archive(task);
        return archiveRepository.save(archive).getId();
    }

    public boolean deleteInArchive(Long id){
     Archive archive = getArchiveById(id);
     archiveRepository.deleteById(id);

        if (archiveRepository.findById(id).isEmpty()) {
            return true;
        }
        return false;
    }
    //  public List<Archive> findAllTasksByName(String title) {
    //    return archiveRepository.findAllByNameContainingIgnoreCase(title);
    //}
}
