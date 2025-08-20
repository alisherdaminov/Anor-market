package Anor.market.domain.service.catalog.order;

import Anor.market.application.dto.catalog.order.branch.create.BranchesCreateDTO;
import Anor.market.application.dto.catalog.order.branch.dto.BranchesDTO;

import java.util.List;

public interface BranchesService {

    BranchesDTO createBranch(BranchesCreateDTO createDTO);

    List<BranchesDTO> getAllBranches();

    BranchesDTO updateBranch(String branchId, BranchesCreateDTO createDTO);

    String deleteBranchById(String branchId);

}
