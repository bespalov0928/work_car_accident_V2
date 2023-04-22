package ru.work.work_car_accident_V2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.work.work_car_accident_V2.model.Photo;

import java.util.List;

public interface JpaPhotoRepository extends CrudRepository<Photo, Integer> {
    List<Photo> findPhotosByAccident_Id(int idAcc);


    void deletePhotosByAccident_Id(int idAcc);
}
