import "../../assets/styles/home-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";

class Item extends Component {
  render() {
    return (
      <div className="col-lg-3 col-md-6 col-sm-12">
        <div className="p-4 bg-white product-container">
          <a
            href={"../commodities/" + this.props.item.id}
            className="product-refrence"
          >
            <h4 className="text-brown">{this.props.item.name}</h4>
          </a>
          <p className="product-stock-info">
            {this.props.item.inStock} left in stock
          </p>
          <a href={"../commodities/" + this.props.item.id}>
            <img
              src={this.props.item.image}
              alt="product picture"
              className="img-fluid"
              style={{ height: "150px", width: "100%" }}
            />
          </a>
          <div className="row">
            <div className="col-4">
              {!!this.props.item.inStock && (
                <h4 className="p-2 text-brown">{this.props.item.price}$</h4>
              )}
            </div>
            <div className="col-8 p-2">
              {!this.props.item.inStock ? (
                <button
                  className="btn add-cart-button float-end"
                  type="submit"
                  disabled
                >
                  add to cart
                </button>
              ) : (
                <button className="btn add-cart-button float-end" type="submit">
                  add to cart
                </button>
              )}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Item;
