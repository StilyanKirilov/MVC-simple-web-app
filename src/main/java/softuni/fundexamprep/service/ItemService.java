package softuni.fundexamprep.service;

import softuni.fundexamprep.model.service.ItemServiceModel;
import softuni.fundexamprep.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {

    ItemServiceModel addItem(ItemServiceModel itemServiceModele);

    List<ItemViewModel> findAllItems();

    ItemViewModel findById(String id);

    void deleteItemById(String id);

}
