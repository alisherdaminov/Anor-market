package Anor.market.presentation.controller.home;

import Anor.market.application.dto.home.create.HomeCreateDTO;
import Anor.market.application.dto.home.create.HomeTitleCreateDTO;
import Anor.market.application.dto.home.dto.HomeDTO;
import Anor.market.application.dto.home.home_product.HomeProductsDTO;
import Anor.market.application.service.home.HomeServiceImpl;
import Anor.market.presentation.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class Home {

    @Autowired
    private HomeServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AppResponse<HomeDTO>> createHome(@RequestBody HomeCreateDTO homeCreateDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.createHome(homeCreateDTO), "success", new Date()));
    }

    @GetMapping("/{homeTitleId}")
    public ResponseEntity<AppResponse<List<HomeProductsDTO>>> getAllProductsById(@PathVariable("homeTitleId") String homeTitleId,
                                                                                 @RequestBody HomeTitleCreateDTO homeTitleCreateDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.getAllProductsById(homeTitleId, homeTitleCreateDTO), "success", new Date()));
    }

    @GetMapping("/list")
    public ResponseEntity<AppResponse<List<HomeDTO>>> getAllHome() {
        return ResponseEntity.ok().body(new AppResponse<>(service.getAllHome(), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{homeTitleId}")
    public ResponseEntity<AppResponse<HomeDTO>> updateHome(@PathVariable("homeTitleId") String homeTitleId,
                                                           @RequestBody HomeCreateDTO homeCreateDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.updateHome(homeTitleId, homeCreateDTO), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{homeId}")
    public ResponseEntity<AppResponse<String>> deleteHomeById(@PathVariable("homeId") String homeId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteHomeById(homeId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{homeProductId}")
    public ResponseEntity<AppResponse<String>> deleteHomeProductById(@PathVariable("homeProductId") String homeProductId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteHomeProductById(homeProductId), "success", new Date()));
    }
}
