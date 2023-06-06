import React, { Component } from "react";
import "../../assets/styles/user-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import CartTableRow from "./cartTableRow";

class CartTable extends Component {
  render() {
    return (
      <div class="table-responsive custom-table-responsive">
        <table class="table custom-table">
          <thead>
            <tr>
              <th scope="col">Image</th>
              <th scope="col">Name</th>
              <th scope="col">Categories</th>
              <th scope="col">Price</th>
              <th scope="col">Provider ID</th>
              <th scope="col">Rating</th>
              <th scope="col">In Stock</th>
              <th scope="col">In Cart</th>
            </tr>
          </thead>
          {!!this.props.cartCommodities.length && (
            <tbody>
              {this.props.cartCommodities.map((item) => (
                <CartTableRow
                  item={item}
                  updateItemCount={this.props.updateItemCount}
                  initialOrderCount={item.quantity || 1}
                />
              ))}
            </tbody>
          )}
        </table>
        {!this.props.cartCommodities.length && (
          <p className="empty-cart">your cart is empty</p>
        )}
      </div>
    );
  }
}

export default CartTable;
