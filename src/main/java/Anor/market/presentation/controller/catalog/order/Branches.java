package Anor.market.presentation.controller.catalog.order;

import Anor.market.application.dto.catalog.order.branch.create.BranchesCreateDTO;
import Anor.market.application.dto.catalog.order.branch.dto.BranchesDTO;
import Anor.market.application.service.catalog.order.BranchServiceImpl;
import Anor.market.presentation.response.AppResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class Branches {

    @Autowired
    private BranchServiceImpl branchService;

    @PostMapping
    public ResponseEntity<AppResponse<BranchesDTO>> createBranch(
            @Valid
            @RequestBody BranchesCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(branchService.createBranch(createDTO), "success", new Date()));
    }

    @GetMapping("/list")
    public ResponseEntity<AppResponse<List<BranchesDTO>>> getAllBranches() {
        return ResponseEntity.ok().body(new AppResponse<>(branchService.getAllBranches(), "success", new Date()));
    }

    @PutMapping("/{branchesId}")
    public ResponseEntity<AppResponse<BranchesDTO>> updateBranch(
            @Valid
            @PathVariable("branchesId") String branchesId,
            @RequestBody BranchesCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(branchService.updateBranch(branchesId, createDTO), "success", new Date()));
    }

    @DeleteMapping("/{branchesId}")
    public ResponseEntity<AppResponse<String>> deleteBranchById(
            @Valid
            @PathVariable("branchesId") String branchesId) {
        return ResponseEntity.ok().body(new AppResponse<>(branchService.deleteBranchById(branchesId), "success", new Date()));
    }

}
