package ru.work.work_car_accident_V2.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.work.work_car_accident_V2.model.Accident;
import ru.work.work_car_accident_V2.model.Photo;
import ru.work.work_car_accident_V2.service.SimpleAccidentService;
import ru.work.work_car_accident_V2.service.SimplePhotoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/photo")
public class PhotoController {

    private final SimplePhotoService photoService;
    private final SimpleAccidentService accidentService;

    @GetMapping("/getPhoto/{idPhoto}")
    public ResponseEntity<ByteArrayResource> getPhotoId(@PathVariable("idPhoto") int idPhoto) {
        byte[] photo = photoService.findById(idPhoto).getPhoto();
        var rsl1 = ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(photo.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(photo));
        return rsl1;
    }

    @PostMapping("/save")
    public String savePhoto(@ModelAttribute Accident accident,
                            @RequestParam(value = "files") MultipartFile[] files) throws IOException {
        Optional<Accident> accidentFind = accidentService.findById(accident.getId());
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            Photo ph = Photo.of(file.getBytes(), accidentFind.get());
            photoService.savePhoto(ph);
        }
        return "redirect:/acc/formUpdateAcc?id=" + accident.getId();
    }

    @PostMapping("/delete")
    public String deleteAllPhoto(@ModelAttribute Accident accident) {
        photoService.deletePhotosByAccident_Id(accident.getId());
        return "redirect:/acc/formUpdateAcc?id=" + accident.getId();
    }
}
