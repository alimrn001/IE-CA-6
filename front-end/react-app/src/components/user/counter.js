import "../../assets/styles/user-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";

class Counter extends Component {
  state = {};
  render() {
    return (
        <div class="counter-btn d-flex justify-content-around">
            <div><button class="counter-btn-item">-</button></div>
            <div><button class="counter-btn-item">1</button></div>
            <div><button class="counter-btn-item">+</button></div>
        </div>
    );
  }
}

export default Counter;
