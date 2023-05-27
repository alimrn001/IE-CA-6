import React, { Component } from "react";
import "../../assets/styles/user-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";


class HistoryTableRow extends Component {

  render() {
    return (
        <React.Fragment>
            <tr class="spacer">
                <td colspan="100"></td>
            </tr>
            <tr>
                <th scope="row"><a href="product.html"><img src={this.props.item.img} class="cart-product-img" /></a>
                </th>
                <td>
                <p>{this.props.item.productName}</p>
                </td>
                <td>
                <p>{this.props.item.categories}</p>
                </td>
                <td>
                <p>${this.props.item.price}</p>
                </td>
                <td>
                <p>{this.props.item.providerID}</p>
                </td>
                <td>
                <p class="text-yellow">{this.props.item.rating}</p>
                </td>
                <td>
                <p class="text-red">{this.props.item.inStock}</p>
                </td>
                <td>
                <p>{this.props.item.quantity}</p>
                </td>
            </tr>
        </React.Fragment>
    );
  }
}

export default HistoryTableRow;
