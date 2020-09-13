package softuni.fundexamprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.fundexamprep.model.entity.Category;
import softuni.fundexamprep.model.entity.Item;
import softuni.fundexamprep.model.service.CategoryServiceModel;
import softuni.fundexamprep.model.service.ItemServiceModel;
import softuni.fundexamprep.model.view.ItemViewModel;
import softuni.fundexamprep.repository.ItemRepository;
import softuni.fundexamprep.service.CategoryService;
import softuni.fundexamprep.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public ItemServiceModel addItem(ItemServiceModel itemServiceModel) {
        CategoryServiceModel category = this.categoryService
                .getCategoryByCategoryName(itemServiceModel.getCategory().getCategoryName());
        itemServiceModel.setCategory(category);
        Item item = this.modelMapper.map(itemServiceModel, Item.class);
        return this.modelMapper.map(this.itemRepository.saveAndFlush(item), ItemServiceModel.class);
    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return this.itemRepository
                .findAll()
                .stream()
                .map(item -> {
                    ItemViewModel itemViewModel = this.modelMapper
                            .map(item, ItemViewModel.class);
                    itemViewModel.setImgUrl(String
                            .format("/img/%s-%s.jpg", item.getGender(),
                                    item.getCategory().getCategoryName().name()));
                    return itemViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel findById(String id) {

        return this.itemRepository.findById(id)
                .map(item -> {
                    ItemViewModel itemViewModel = this.modelMapper
                            .map(item, ItemViewModel.class);

                    itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg", item.getGender(),
                            item.getCategory().getCategoryName().name()));
                    return itemViewModel;
                }).orElse(null);
    }

    @Override
    public void deleteItemById(String id) {
        this.itemRepository.deleteById(id);
    }
}
