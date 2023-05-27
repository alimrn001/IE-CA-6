import "../../assets/styles/product-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Baloot from "../../assets/img/Baloot.svg";
import React, { Component } from "react";

class Nav extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand-lg navbar-light bg-white navbar-top fixed-top shadow-sm rounded">
          <div className="container-fluid">
            <a className="navbar-brand" href="/">
              <img
                src={Baloot}
                alt=""
                width="45"
                height="45"
                className="d-inline-block align-text"
              />
            </a>
            <a className="app-name-navbar" href="/">
              Baloot
            </a>
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <p className="username">#{this.props.username}</p>
                {/* <!-- <a class="btn btn-primary btn-register-nv" href="register.html" role="button">#Marshal</a> --> */}
              </li>
            </ul>
            <ul className="navbar-nav ">
              <li className="nav-item">
                <a href="../../user" className="user-cart-reference">
                  <div className="d-flex justify-content-between btn btn-primary btn-cart-nv">
                    <div>Cart</div>
                    <div>{this.props.cartItemsCount}</div>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    );
  }
}

export default Nav;
