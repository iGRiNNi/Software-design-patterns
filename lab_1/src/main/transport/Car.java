package main.transport;

import main.exception.DuplicateModelNameException;
import main.exception.ModelPriceOutOfBoundsException;
import main.exception.NoSuchModelNameException;

import java.io.Serializable;
import java.util.Arrays;
public class Car implements Transportable {
    private class Model implements Serializable, Cloneable {
        String nameModel;           //Название модели
        double priceModel;          //Цена модели

        private Model() {
        }

        private Model(String nameModel, Double priceModel) {
            this.nameModel = nameModel;
            this.priceModel = priceModel;
        }

        public void setNameModel(String nameModel) {
            this.nameModel = nameModel;
        }

        public String getNameModel() {
            return nameModel;
        }

        public double getPriceModel() {
            return priceModel;
        }

        public void setPriceModel(Double priceModel) {
            this.priceModel = priceModel;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    private String brand;       //Марка авто

    private Model[] models;     //массив моделей

    public Car(){
        models = new Model[0];
    }


    public Car(String brand, int sizeModelArray) {
        this.brand = brand;
        models = new Model[sizeModelArray];
        for (int i = 0; i < sizeModelArray; i++) {
            models[i] = new Model(Integer.toString(i), Math.random() * 100000);
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] getModelsNames() {                            //Метод, возвращающий массив названий всех моделей
        String names[] = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            names[i] = models[i].nameModel;
        }
        return names;
    }

    public double modelPriceByName(String nameModel) throws NoSuchModelNameException {       //метод для получения значения цены модели по её названию
        boolean flag = true;
        int i = 0;
        double price = 0;
        while (flag && i < models.length) {
            if (!(nameModel.equals(models[i].nameModel))) {
                i++;
            } else {
                flag = false;
                price = models[i].getPriceModel();
            }
        }
        if (flag) {
            throw new NoSuchModelNameException(nameModel);
        }
        return price;
    }

    public void modelModifPriceByName(double price, String name) throws NoSuchModelNameException {        //метод для модификации значения цены модели по её названию
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        } else {
            boolean flag = true;
            int i = 0;
            while (flag && i < models.length) {
                if (!(name.equals(models[i].getNameModel()))) {
                    i++;
                } else {
                    flag = false;
                }
            }
            if (flag) {
                throw new NoSuchModelNameException(name);
            } else {
                models[i].setPriceModel(price);
            }
        }
    }

    public double[] getModelsPrice() {           //метод, возвращающий массив значений цен моделей
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].priceModel;
        }
        return prices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {                    //метод добавления названия модели и её цены (путем создания нового массива Моделей)
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        } else {
            boolean flag = false;
            for (int i = 0; i < models.length; i++) {
                if (name.equals(models[i].nameModel)) {
                    flag = true;
                }
            }
            if (flag) {
                throw new DuplicateModelNameException(name);
            } else {
                models = Arrays.copyOf(models, models.length + 1);
                models[models.length - 1] = new Model(name, price);
            }
        }
    }

    public void deleteModelByName(String name) throws NoSuchModelNameException {                    //метод удаления модели по заданному имени

        int x;
        boolean flag = false;
        for (int i = 0; i < models.length; i++) {
            if (name.equals(models[i].nameModel)) {
                Model[] y = new Model[models.length - 1];
                if (i != 0 && i != models.length - 1) {
                    System.arraycopy(models, 0, y, 0, i);
                    System.arraycopy(models, i + 1, y, i, models.length - i - 1);
                    flag = true;
                }
                if (i == 0) {
                    System.arraycopy(models, 1, y, 0, models.length - 1);
                    flag = true;
                }
                if (i == models.length - 1) {
                    y = Arrays.copyOf(models, models.length - 1);
                    flag = true;
                }
                models = y;
            } else {
                if (i == models.length - 1 && !flag) {
                    throw new NoSuchModelNameException(name);
                }
            }
        }
    }

    public void printCarArray() {                //метод для вывода массива в консоль
        for (int i = 0; i < models.length; i++) {
            System.out.println(models[i].nameModel + " " + models[i].priceModel);
        }
    }

    public int getModelArraySize() {             //метод для получения размера массива Моделей
        int count = 0;
        for (Model i : models) {
            count++;
        }
        return count;
    }

    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {          //Модификация значения названия модели.
        boolean flag = true;
        int x = -1;
        for (int i = 0; i < models.length; i++) {
            if (newName.equals(models[i].nameModel)) {
                throw new DuplicateModelNameException(newName);
            }
            if (name.equals(models[i].nameModel)) {
                flag = false;
                x = i;
                // неправильно потому что может быть ситуация когда старое имя в массиве встречается
                // раньше нового. Тогда вы поменяете имя, а только потом уже выкинете ошибку. В массиве будут дубликаты, что неверно.
                // А ошибку можно будет перехватить и тогда в массиве будут неверные данные.
                // Можжно и через один, но устанавливать значнение уже вне цикла
                // Исправил.
            }
        }
        if (flag) {
            throw new NoSuchModelNameException(name);
        }
        else{
            models[x].nameModel = newName;
        }
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(brand).append("\n");
        for (Model value : models) {
            strBuilder.append(value.nameModel).append(" ");
        }
        strBuilder.append("\n");
        for (Model model : models) {
            strBuilder.append(model.priceModel).append(" ");
        }
        return strBuilder.toString();           // Есть ли тут разница между использованием toString() и String.ValueOf()???
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Car)) return false;
        Car objCar = (Car) obj;
        if (!(this.brand.equals(objCar.getBrand()))) return false;
        if (this.getModelArraySize() != objCar.getModelArraySize()) return false;
        if (Arrays.equals(getModelsNames(), objCar.getModelsNames()) && Arrays.equals(getModelsPrice(), objCar.getModelsPrice())) return true;
        return false;


        /*
        if (obj instanceof Transportable) {
            Car objCar = (Car) obj;
            if (this.brand.equals(objCar.brand)) {
                if (this.getModelArraySize() == objCar.getModelArraySize()) {
                    for (int i = 0; i < this.getModelArraySize(); i++) {
                        if (this.models[i].nameModel.equals(objCar.models[i].nameModel) && this.models[i].priceModel == objCar.models[i].priceModel) {
                            return true;
                        }
                        else return false;
                    }
                }
                else return false;
            }
            else return false;
        }
        else return false;
         */


//        if (this == obj) return true;
////        if (!(obj instanceof Car)) return false;                        // Сравнивает только Автомобиль и автомобиль.
////        Car objCar = (Car) obj;
////        if (!(this.brand.equals(objCar.getBrand()))) return false;
////        if (this.getModelArraySize() != objCar.getModelArraySize()) return false;
//////        for (int i = 0; i < this.getModelArraySize(); i++) {
//////            if (!(this.models[i].nameModel.equals(objCar.models[i].nameModel) && this.models[i].priceModel == objCar.models[i].priceModel)) return false;
//////        }
////        if (Arrays.equals(getModelsNames(), objCar.getModelsNames()) && Arrays.equals(getModelsPrice(), objCar.getModelsPrice())) return true;
////        return false;

    }

    @Override
    public int hashCode() {
        int result = brand == null ? 0 : brand.hashCode();
        result = 31 * result + this.getModelArraySize();
        for ( int i = 0; i < this.getModelArraySize(); i++) {
            result = 31 * result + models[i].nameModel.hashCode();
            result = (int) (31 * result + models[i].priceModel);
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car result = null;
        result = (Car) super.clone();
        result.models = (Model[]) models.clone();
        for (int i = 0; i < models.length; i++) {
            result.models[i] = (Model) models[i].clone();
        }
        return result;
    }
}
