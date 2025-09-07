package Anor.market.application.service.catalog.order;

import Anor.market.application.dto.catalog.order.branch.create.BranchesCreateDTO;
import Anor.market.application.dto.catalog.order.branch.dto.BranchesDTO;
import Anor.market.application.mapper.catalog.order.BranchesMapper;
import Anor.market.domain.model.entity.catalog.order.BranchesEntity;
import Anor.market.domain.repository.catalog.order.BranchesRepository;
import Anor.market.domain.service.catalog.order.BranchesService;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BranchServiceImpl implements BranchesService, this is the creation of anor markets branches where the customers are able
 * to accept their product after order in given date, the  customers can select one of the nearest branch in their area!
 *
 */
@Service
public class BranchServiceImpl implements BranchesService {

    @Autowired
    private BranchesRepository branchesRepository;
    @Autowired
    private BranchesMapper branchesMapper;


    /// CREATE A BRANCH
    @Override
    public BranchesDTO createBranch(BranchesCreateDTO createDTO) {
        if (SpringSecurityValid.hasRole(Roles.USER) && SpringSecurityValid.hasRole(Roles.SELLER)) {
            throw new AppBadException("You do not have any permission to make a branch of Anor market!");
        }
        BranchesEntity branches = branchesMapper.toEntity(createDTO);
        branchesRepository.save(branches);
        return branchesMapper.toDTO(branches);
    }

    /// GET ALL BRANCHES DATA
    @Override
    public List<BranchesDTO> getAllBranches() {
        return branchesRepository.findAll(Sort.by(Sort.Direction.DESC, "localDateTime")).stream().map(branchesMapper::toDTO).toList();
    }

    /// UPDATE ALL BRANCHES DATA BY BRANCH ID
    @Override
    public BranchesDTO updateBranch(String branchId, BranchesCreateDTO createDTO) {
        if (SpringSecurityValid.hasRole(Roles.USER) && SpringSecurityValid.hasRole(Roles.SELLER)) {
            throw new AppBadException("You do not have any permission to make a branch of Anor market!");
        }
        BranchesEntity branches = branchesRepository.findByStringId(branchId).orElseThrow(() -> new AppBadException("Branch id is not found!"));
        branches.setBranchTitle(createDTO.getBranchTitle());
        branches.setCityName(createDTO.getCityName());
        branchesRepository.save(branches);
        return branchesMapper.toDTO(branches);
    }

    /// DELETE BRANCH DATA BY BRANCH ID
    @Override
    public String deleteBranchById(String branchId) {
        branchesRepository.deleteByBranchesId(branchId);
        return "Deleted!";
    }
}
