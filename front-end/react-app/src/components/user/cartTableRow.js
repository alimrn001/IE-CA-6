import React, { Component } from "react";
import "../../assets/styles/user-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

class CartTableRow extends Component {
  constructor(props) {
    super(props);
    this.state = {
      orderCount: this.props.initialOrderCount,
    };
  }
  componentDidMount() {
    toast.configure({
      rtl: true,
      className: "text-center",
      position: toast.POSITION.TOP_CENTER,
      autoClose: 5000,
      closeOnClick: true,
    });
    this.setState({});
  }
  increaseOrderCount(instock, update, id) {
    if (this.state.orderCount === instock) {
      toast.error(
        "You can't order more than " + instock + " item from this commodity"
      );
      return;
    }
    update(id, this.state.orderCount + 1);
    this.setState({
      orderCount: this.state.orderCount + 1,
    });
  }
  decreaseOrderCount(update, id) {
    if (this.state.orderCount === 1) {
      return;
    }
    update(id, this.state.orderCount - 1);
    this.setState({
      orderCount: this.state.orderCount - 1,
    });
  }
  render() {
    return (
      <React.Fragment>
        <tr class="spacer">
          <td colspan="100"></td>
        </tr>

        <tr>
          <th scope="row">
            <a href="product.html">
              <img src={this.props.item.img} class="cart-product-img" />
            </a>
          </th>
          <td>{this.props.item.productName}</td>
          <td>{this.props.item.categories}</td>
          <td>${this.props.item.price}</td>
          <td>{this.props.item.providerID}</td>
          <td>
            <p class="text-yellow">{this.props.item.rating}</p>
          </td>
          <td>
            <p class="text-red">{this.props.item.inStock}</p>
          </td>
          <td>
            <div class="counter-btn d-flex justify-content-around">
              <div>
                <button
                  class="counter-btn-item"
                  onClick={() =>
                    this.decreaseOrderCount(
                      this.props.updateItemCount,
                      this.props.item.commodityID
                    )
                  }
                >
                  -
                </button>
              </div>
              <div>
                <button class="counter-btn-item">
                  {this.state.orderCount}
                </button>
              </div>
              <div>
                <button
                  class="counter-btn-item"
                  onClick={() =>
                    this.increaseOrderCount(
                      this.props.item.inStock,
                      this.props.updateItemCount,
                      this.props.item.commodityID
                    )
                  }
                >
                  +
                </button>
              </div>
            </div>
            {/* <div class="counter-btn d-flex justify-content-around">
                                <div><button class="counter-btn-item" onClick={() => this.decreaseOrderCount()}>-</button></div>
                                <div><button class="counter-btn-item">{this.props.getItemCount(1234)}</button></div>
                                <div><button class="counter-btn-item" onClick={() => this.increaseOrderCount(this.props.item.inStock)}>+</button></div>
                            </div> */}
            {/* <!-- <input placeholder="1" type="number" value="" min="1" class="form-control"/> --> */}
          </td>
        </tr>
      </React.Fragment>
    );
  }
}

export default CartTableRow;
