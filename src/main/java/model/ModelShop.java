package model;
import fpt.com.Product;
import javafx.collections.*;


/**
 * Created by Surya on 01.11.2016.
 */


public class ModelShop extends ModifiableObservableListBase<model.Product> {

    public ProductList delegateProduct;
    public ModelShop(){
        delegateProduct = new ProductList();
    }
    @Override
    protected void doAdd(int index, model.Product element) {
        delegateProduct.add(index, element);
    }
    @Override
    protected model.Product doRemove(int index) {
        return (model.Product) delegateProduct.remove(index);
    }
    @Override
    protected model.Product doSet(int index, model.Product element) {
        return (model.Product) delegateProduct.set(index, element);
    }
    @Override
    public model.Product get(int index) {
        return (model.Product) delegateProduct.get(index);
    }
    @Override
    public int size() {
        return delegateProduct.size();
    }
    public boolean delete(Product product) {
        return delegateProduct.delete(product);
    }
    public Product findProductById(long id) {
        return (model.Product) delegateProduct.findProductById(id);
    }
    public Product findProductByName(String name) {
        return (model.Product) delegateProduct.findProductByName(name);
    }
    public boolean add(Product e) {
        return delegateProduct.add(e);
    }

}
