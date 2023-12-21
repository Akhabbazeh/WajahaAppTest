package com.example.WajahaAppTest.data.product_data;

import com.example.WajahaAppTest.data.Status;
import com.example.WajahaAppTest.model.Products;

import java.util.List;

public class ProductResponse {

        private List<Products> product;
        private Throwable error;
        private Status status;

        public ProductResponse(List<Products> product, Throwable error, Status status)
        {
            this.product = product;
            this.error = error;
            this.status = status;
        }

    public List<Products> getProduct() {
        return product;
    }

    public void setProduct(List<Products> product) {
        this.product = product;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
