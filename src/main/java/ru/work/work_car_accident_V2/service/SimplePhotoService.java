package ru.work.work_car_accident_V2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.work.work_car_accident_V2.model.Accident;
import ru.work.work_car_accident_V2.model.Photo;
import ru.work.work_car_accident_V2.repository.JpaPhotoRepository;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class SimplePhotoService {
    private final JpaPhotoRepository photoRepository;

    public Collection<Photo> findAll() {
        return (Collection<Photo>) photoRepository.findAll();
    }

    public Photo findById(int id) {
        return photoRepository.findById(id).get();
    }

    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Photo updatePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public boolean updatePhotoAcc(Accident accident, List<Photo> photos) {
        boolean rsl = false;
        if (photos.size() != 0) {
            deletePhotosByAccident_Id(accident.getId());
            for (Photo ph : photos) {
                savePhoto(ph);
            }
            rsl = true;
        }
        return rsl;
    }

    public boolean delPhoto(int id) {
        var rsl = findById(id);
        photoRepository.delete(rsl);
        return true;
    }

    public boolean deletePhotosByAccident_Id(int idAcc) {
        List<Photo> photos = photoRepository.findPhotosByAccident_Id(idAcc);
        for (Photo ph:photos) {
            photoRepository.delete(ph);
        }
        return true;
    }

    public List<Photo> findPhotosByAccident_Id(int idAcc) {
        List<Photo> photos = photoRepository.findPhotosByAccident_Id(idAcc);
        return photos;
    }


}
